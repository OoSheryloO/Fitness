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
import com.kjyl.pojo.Event;
import com.kjyl.pojo.Picture;
import com.kjyl.util.CodeInfo;
import com.kjyl.util.DBParam;
import com.kjyl.util.GenerateKey.IdWorker;

import static com.kjyl.util.ResultUtil.sharedInstance;

/**
 * <p> 控制器 Class</p>
 * @author sheryl 自动构建脚本
 */
@Api(value="Event", description="")
@RestController
@RequestMapping("/Event")
public class EventController extends BaseController {

//    @GetMapping("/searchEventPage")
	@ApiOperation(value = "获取列表", notes= "", httpMethod = "GET")
	@RequestMapping(value="/searchEventPage", method=RequestMethod.GET)
//  @ApiImplicitParam(name = "data", value = "data描述", required = true, dataType = "UserInfo", paramType = "query")
//  @ApiImplicitParams({
//    @ApiImplicitParam(name="name",value="用户名",dataType="string", paramType = "query",example="xingguo"),
//	  @ApiImplicitParam(name="id",value="用户id",dataType="long", paramType = "query")
//  })
    public Map<String, Object> searchEventPage(Integer status, String search, int pageNumber, int pageSize, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        mapSearch.put("search", search);
        if(status != null && status != -1){
        	mapSearch.put(Event.COLUMN_Status, status);
        }
        PageInfo<Event> page = this.EventService.SearchPage(mapSearch, pageNumber, pageSize);
        mapResult.put(CodeInfo.sRowKey, page.getList());
        mapResult.put(CodeInfo.sTotalKey, page.getTotal());
        return sharedInstance().TrueData(mapResult, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @PostMapping("/setEventStatus")
    @RequestMapping(value="/setEventStatus", method=RequestMethod.POST)
    @ApiOperation(value = "设置状态")
    public Map<String, Object> setEventStatus(@RequestBody String data){
        Event temp = JSON.parseObject(data, Event.class);
        String[] ids = temp.getId().split(",");
        for (String Id : ids){
            if(temp.getStatus() == DBParam.RecordStatus.Delete.getCode()){
                EventService.RecoverBySpecial(Id);
            }else{
                EventService.RemoveBySpecial(Id);
            }
        }
        return sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @GetMapping("/searchEvent/{id}")
    @RequestMapping(value="/searchEvent/{id}", method=RequestMethod.GET)
    @ApiOperation(value = "根据编号查询内容")
    public Map<String, Object> searchEvent(@PathVariable("id") String Id){
        Event temp = EventService.SearchBySpecial(Id);
        if(temp != null){
        	return sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    	}else{
    		return sharedInstance().FalseData("获取失败!", CodeInfo.Code.NO.getCode());
		}
    }

//    @PostMapping("/modifyEvent")
    @RequestMapping(value="/modifyEvent", method=RequestMethod.POST)
    @ApiOperation(value = "修改")
    public Map<String, Object> modifyEvent(@RequestBody String data, HttpServletRequest request) {
        Event temp = JSON.parseObject(data, Event.class);
        Event obj = new Event();
        boolean isNew = false;
        if("0".equals(temp.getId())){
            isNew = true;
        }else{
            obj = EventService.SearchBySpecial(temp.getId());
            if(obj == null){
                isNew = true;
                obj = new Event();
            }
        }
        obj.setUseId(temp.getUseId());
        obj.setLogicId(temp.getLogicId());
        obj.setTitle(temp.getTitle());
        obj.setIntro(temp.getIntro());
        obj.setSite(temp.getSite());
        obj.setStartTime(temp.getStartTime());
        obj.setEndTime(temp.getEndTime());
        obj.setStartApply(temp.getStartApply());
        obj.setEndApply(temp.getEndApply());
        obj.setMemo(temp.getMemo());
        obj.setDelete(temp.getDelete());
        obj.setModifyTime(temp.getModifyTime());

        Event tempObj = null;
        if(isNew){
            obj.setId(IdWorker.CreateStringNewId());
            obj.setStatus(DBParam.RecordStatus.Default.getCode());
            tempObj = EventService.Insert(obj);
        }else{
            tempObj = EventService.Modify(obj);
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
