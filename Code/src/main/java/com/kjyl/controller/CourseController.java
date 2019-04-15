package com.kjyl.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.kjyl.pojo.Course;
import com.kjyl.pojo.Picture;
import com.kjyl.util.CodeInfo;
import com.kjyl.util.DBParam;
import com.kjyl.util.GenerateKey.IdWorker;

import static com.kjyl.util.ResultUtil.sharedInstance;

/**
 * <p> 控制器 Class</p>
 * @author sheryl 自动构建脚本
 */
@Api(value="Course", description="")
@RestController
@RequestMapping("/Course")
public class CourseController extends BaseController {

//    @GetMapping("/searchCoursePage")
	@ApiOperation(value = "获取列表", notes= "", httpMethod = "GET")
	@RequestMapping(value="/searchCoursePage", method=RequestMethod.GET)
//  @ApiImplicitParam(name = "data", value = "data描述", required = true, dataType = "UserInfo", paramType = "query")
//  @ApiImplicitParams({
//    @ApiImplicitParam(name="name",value="用户名",dataType="string", paramType = "query",example="xingguo"),
//	  @ApiImplicitParam(name="id",value="用户id",dataType="long", paramType = "query")
//  })
    public Map<String, Object> searchCoursePage(Integer status, String time, String id, String logicId, int pageNumber, int pageSize, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        mapSearch.put(Course.COLUMN_StartTime, time);
        mapSearch.put(DBParam.sUIdKey, id);//临时征用 代替当前用户id
        mapSearch.put(Course.COLUMN_Status, status);
        mapSearch.put(Course.COLUMN_LogicId, logicId);
        PageInfo<Course> page = this.CourseService.SearchPage(mapSearch, pageNumber, pageSize);
        mapResult.put(CodeInfo.sRowKey, page.getList());
        mapResult.put(CodeInfo.sTotalKey, page.getTotal());
        return sharedInstance().TrueData(mapResult, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @PostMapping("/setCourseStatus")
    @RequestMapping(value="/setCourseStatus", method=RequestMethod.POST)
    @ApiOperation(value = "设置状态")
    public Map<String, Object> setCourseStatus(@RequestBody String data){
        Course temp = JSON.parseObject(data, Course.class);
        String[] ids = temp.getId().split(",");
        for (String Id : ids){
            if(temp.getStatus() == DBParam.RecordStatus.Delete.getCode()){
                CourseService.RecoverBySpecial(Id);
            }else{
                CourseService.RemoveBySpecial(Id);
            }
        }
        return sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @GetMapping("/searchCourse/{id}")
    @RequestMapping(value="/searchCourse/{id}", method=RequestMethod.GET)
    @ApiOperation(value = "根据编号查询内容")
    public Map<String, Object> searchCourse(@PathVariable("id") String Id){
        Course temp = CourseService.SearchBySpecial(Id);
        if(temp != null){
        	return sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    	}else{
    		return sharedInstance().FalseData("获取失败!", CodeInfo.Code.NO.getCode());
		}
    }

//    @PostMapping("/modifyCourse")
    @RequestMapping(value="/modifyCourse", method=RequestMethod.POST)
    @ApiOperation(value = "修改")
    public Map<String, Object> modifyCourse(@RequestBody String data, HttpServletRequest request) {
        Course temp = JSON.parseObject(data, Course.class);
        Course obj = new Course();
        boolean isNew = false;
        if("0".equals(temp.getId())){
            isNew = true;
        }else{
            obj = CourseService.SearchBySpecial(temp.getId());
            if(obj == null){
                isNew = true;
                obj = new Course();
            }
        }
        obj.setUseId(temp.getUseId());
        obj.setLogicId(temp.getLogicId());
        obj.setTitle(temp.getTitle());
        obj.setInfo(temp.getInfo());
        obj.setStartTime(temp.getStartTime());
        obj.setEndTime(temp.getEndTime());
        obj.setApplyTime(temp.getApplyTime());
        obj.setApply(temp.getApply());
        obj.setApplyLimit(temp.getApplyLimit());
        obj.setPrice(temp.getPrice());
        obj.setSite(temp.getSite());
        obj.setSort(temp.getSort());
        obj.setMemo(temp.getMemo());
        obj.setDelete(temp.getDelete());
        obj.setModifyTime(temp.getModifyTime());

        Course tempObj = null;
        if(isNew){
            obj.setId(IdWorker.CreateStringNewId());
            obj.setStatus(DBParam.RecordStatus.Default.getCode());
            tempObj = CourseService.Insert(obj);
        }else{
            tempObj = CourseService.Modify(obj);
        }
        if (temp.getPicture() != null) {
        	if (!isNew) {
				this.PictureService.RemoveBySpecialLogicId(obj.getId());
			}
			List<String> lst = temp.getPicture();
			for (String id : lst) {
				Picture pojo = this.PictureService.SearchBySpecial(id);
				pojo.setLogicId(obj.getId());
				pojo.setDelete(DBParam.RecordStatus.Default.getCode());
				this.PictureService.Modify(pojo);
			}
		}
        if (tempObj != null) {
//        	if (temp.getPicture() != null && !temp.getPicture().isEmpty()) {
//            	if (!isNew) {
//					Map<String, Object> mapSearch = new HashMap<String, Object>();
//					mapSearch.put(Picture.COLUMN_LogicId, tempObj.getId());
//					mapSearch.put(Picture.COLUMN_Delete, DBParam.RecordStatus.Delete.getCode());
//					this.PictureService.RemoveByCondition(mapSearch);
//				}
//            	List<String> lst = temp.getPicture();
//            	for (String Url : lst) {
//        			Picture pjPic = new Picture(); 
//        			pjPic.setUrl(Url);
//        			pjPic = this.PictureService.SearchByModel(pjPic);
//        			pjPic.setLogicId(obj.getId());
//        			pjPic.setDelete(DBParam.RecordStatus.Default.getCode());
//        			this.PictureService.Modify(pjPic);
//        		}
//    		}
			return sharedInstance().TrueData(tempObj, "修改成功!", CodeInfo.Code.OK.getCode());
		} else {
			return sharedInstance().FalseData("修改失败!", CodeInfo.Code.NO.getCode());
		}
    }

}
