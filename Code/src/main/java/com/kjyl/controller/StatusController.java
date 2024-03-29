package com.kjyl.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.kjyl.pojo.Info;
import com.kjyl.pojo.Post;
import com.kjyl.pojo.Status;
import com.kjyl.util.CodeInfo;
import com.kjyl.util.DBParam;
import com.kjyl.util.GenerateKey.IdWorker;

import static com.kjyl.util.ResultUtil.sharedInstance;

/**
 * <p> 控制器 Class</p>
 * @author sheryl 自动构建脚本
 */
@Api(value="Status", description="")
@RestController
@RequestMapping("/Status")
public class StatusController extends BaseController {

//    @GetMapping("/searchStatusPage")
	@ApiOperation(value = "获取列表", notes= "", httpMethod = "GET")
	@RequestMapping(value="/searchStatusPage", method=RequestMethod.GET)
//  @ApiImplicitParam(name = "data", value = "data描述", required = true, dataType = "UserInfo", paramType = "query")
//  @ApiImplicitParams({
//    @ApiImplicitParam(name="name",value="用户名",dataType="string", paramType = "query",example="xingguo"),
//	  @ApiImplicitParam(name="id",value="用户id",dataType="long", paramType = "query")
//  })
    public Map<String, Object> searchStatusPage(Integer status, String search, int pageNumber, int pageSize, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        mapSearch.put("search", search);
        if(status != null && status != -1){
        	mapSearch.put(Status.COLUMN_Status, status);
        }
        PageInfo<Status> page = this.StatusService.SearchPage(mapSearch, pageNumber, pageSize);
        mapResult.put(CodeInfo.sRowKey, page.getList());
        mapResult.put(CodeInfo.sTotalKey, page.getTotal());
        return sharedInstance().TrueData(mapResult, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @PostMapping("/setStatusStatus")
    @RequestMapping(value="/setStatusStatus", method=RequestMethod.POST)
    @ApiOperation(value = "设置状态")
    public Map<String, Object> setStatusStatus(@RequestBody String data){
        Status temp = JSON.parseObject(data, Status.class);
        String[] ids = temp.getId().split(",");
        for (String Id : ids){
            if(temp.getStatus() == DBParam.RecordStatus.Delete.getCode()){
                StatusService.RecoverBySpecial(Id);
            }else{
                StatusService.RemoveBySpecial(Id);
            }
        }
        return sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @GetMapping("/searchStatus/{id}")
    @RequestMapping(value="/searchStatus/{id}", method=RequestMethod.GET)
    @ApiOperation(value = "根据编号查询内容")
    public Map<String, Object> searchStatus(@PathVariable("id") String Id){
        Status temp = StatusService.SearchBySpecial(Id);
        if(temp != null){
        	return sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    	}else{
    		return sharedInstance().FalseData("获取失败!", CodeInfo.Code.NO.getCode());
		}
    }

//    @PostMapping("/modifyStatus")
    @RequestMapping(value="/modifyStatus", method=RequestMethod.POST)
    @ApiOperation(value = "修改")
    public Map<String, Object> modifyStatus(@RequestBody String data, HttpServletRequest request) {
        Status temp = JSON.parseObject(data, Status.class);
        Status obj = new Status();
        boolean isNew = false;
        if("0".equals(temp.getId()) || temp.getId() == null){
            isNew = true;
        }else{
            obj = StatusService.SearchBySpecial(temp.getId());
            if(obj == null){
                isNew = true;
                obj = new Status();
            }
        }
        obj.setUseId(temp.getUseId());
        obj.setLogicId(temp.getLogicId());
        obj.setMemo(temp.getMemo());
        obj.setDelete(temp.getDelete());
        obj.setModifyTime(temp.getModifyTime());

        if (temp.getType() == 1) {//1 帖子 2资讯
        	Post pjPost = PostService.SearchBySpecial(temp.getLogicId());
			switch (temp.getState()) {
			case 1://点赞
				pjPost.setLike(pjPost.getLike() + 1);
				obj.setLike(1);
				break;
			case 2://取消点赞
				pjPost.setLike(pjPost.getLike() - 1);
				obj.setLike(0);
				break;
			case 3://收藏
				pjPost.setCollect(pjPost.getCollect() + 1);
				obj.setCollect(1);
				break;
			case 4://取消收藏
				pjPost.setCollect(pjPost.getCollect() - 1);
				obj.setCollect(0);
				break;
			default:
				break;
			}
			PostService.Modify(pjPost);
		}
        if (temp.getType() == 2) {
        	Info pjInfo = InfoService.SearchBySpecial(temp.getLogicId());
        	switch (temp.getState()) {
			case 1:
				pjInfo.setLike(pjInfo.getLike() + 1);
				obj.setLike(1);
				break;
			case 2:
				pjInfo.setLike(pjInfo.getLike() - 1);
				obj.setLike(0);
				break;
			case 3:
				pjInfo.setCollect(pjInfo.getCollect() + 1);
				obj.setCollect(1);
				break;
			case 4:
				pjInfo.setCollect(pjInfo.getCollect() - 1);
				obj.setCollect(0);
				break;
			default:
				break;
			}
        	InfoService.Modify(pjInfo);
		}
        Status tempObj = null;
        if(isNew){
            obj.setId(IdWorker.CreateStringNewId());
            obj.setStatus(DBParam.RecordStatus.Default.getCode());
            tempObj = StatusService.Insert(obj);
        }else{
            tempObj = StatusService.Modify(obj);
        }
        if (tempObj != null) {
			return sharedInstance().TrueData(tempObj, "修改成功!", CodeInfo.Code.OK.getCode());
		} else {
			return sharedInstance().FalseData("修改失败!", CodeInfo.Code.NO.getCode());
		}
    }

}
