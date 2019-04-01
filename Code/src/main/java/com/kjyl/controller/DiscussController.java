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
import com.kjyl.pojo.Discuss;
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
@Api(value="Discuss", description="")
@RestController
@RequestMapping("/Discuss")
public class DiscussController extends BaseController {

//    @GetMapping("/searchDiscussPage")
	@ApiOperation(value = "获取列表", notes= "", httpMethod = "GET")
	@RequestMapping(value="/searchDiscussPage", method=RequestMethod.GET)
//  @ApiImplicitParam(name = "data", value = "data描述", required = true, dataType = "UserInfo", paramType = "query")
//  @ApiImplicitParams({
//    @ApiImplicitParam(name="name",value="用户名",dataType="string", paramType = "query",example="xingguo"),
//	  @ApiImplicitParam(name="id",value="用户id",dataType="long", paramType = "query")
//  })
    public Map<String, Object> searchDiscussPage(Integer status, String search, int pageNumber, int pageSize, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        mapSearch.put("search", search);
        if(status != null && status != -1){
        	mapSearch.put(Discuss.COLUMN_Status, status);
        }
        PageInfo<Discuss> page = this.DiscussService.SearchPage(mapSearch, pageNumber, pageSize);
        mapResult.put(CodeInfo.sRowKey, page.getList());
        mapResult.put(CodeInfo.sTotalKey, page.getTotal());
        return sharedInstance().TrueData(mapResult, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @PostMapping("/setDiscussStatus")
    @RequestMapping(value="/setDiscussStatus", method=RequestMethod.POST)
    @ApiOperation(value = "设置状态")
    public Map<String, Object> setDiscussStatus(@RequestBody String data){
        Discuss temp = JSON.parseObject(data, Discuss.class);
        String[] ids = temp.getId().split(",");
        for (String Id : ids){
            if(temp.getStatus() == DBParam.RecordStatus.Delete.getCode()){
                DiscussService.RecoverBySpecial(Id);
            }else{
                DiscussService.RemoveBySpecial(Id);
            }
        }
        return sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @GetMapping("/searchDiscuss/{id}")
    @RequestMapping(value="/searchDiscuss/{id}", method=RequestMethod.GET)
    @ApiOperation(value = "根据编号查询内容")
    public Map<String, Object> searchDiscuss(@PathVariable("id") String Id){
        Discuss temp = DiscussService.SearchBySpecial(Id);
        if(temp != null){
        	return sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    	}else{
    		return sharedInstance().FalseData("获取失败!", CodeInfo.Code.NO.getCode());
		}
    }

//    @PostMapping("/modifyDiscuss")
    @RequestMapping(value="/modifyDiscuss", method=RequestMethod.POST)
    @ApiOperation(value = "修改")
    public Map<String, Object> modifyDiscuss(@RequestBody String data, HttpServletRequest request) {
        Discuss temp = JSON.parseObject(data, Discuss.class);
        Discuss obj = new Discuss();
        boolean isNew = false;
        if("0".equals(temp.getId()) || temp.getId() == null){
            isNew = true;
        }else{
            obj = DiscussService.SearchBySpecial(temp.getId());
            if(obj == null){
                isNew = true;
            }
        }
        obj.setUseId(temp.getUseId());
        obj.setLogicId(temp.getLogicId());
        obj.setParentId(temp.getParentId());
        obj.setTitle(temp.getTitle());
        obj.setContent(temp.getContent());
        obj.setLike(temp.getLike());
        obj.setReview(temp.getReview());
        obj.setDelete(temp.getDelete());
        obj.setModifyTime(temp.getModifyTime());

        Discuss tempObj = null;
        if(isNew){
            obj.setId(IdWorker.CreateStringNewId());
            obj.setStatus(DBParam.RecordStatus.Default.getCode());
            tempObj = DiscussService.Insert(obj);
            
            //帖子被评论数 +1
            Post pjPost = PostService.SearchBySpecial(tempObj.getLogicId());
            if (pjPost != null) {
				pjPost.setReview(pjPost.getReview()+1);
				PostService.Modify(pjPost);
			}
            
            Map<String, Object> mapSearch = new HashMap<String, Object>();
            mapSearch.put(Status.COLUMN_Delete, DBParam.RecordStatus.Delete.getCode());
            mapSearch.put(Status.COLUMN_LogicId, tempObj.getId());
            mapSearch.put(Status.COLUMN_UseId, tempObj.getUseId());
            
            List<Status> lstStatus = StatusService.SearchByCondition(mapSearch);
            if (lstStatus == null || lstStatus.size() == 0) {
				Status pjStatus = new Status();
				pjStatus.setId(IdWorker.CreateStringNewId());
				pjStatus.setUseId(tempObj.getUseId());
				pjStatus.setLogicId(tempObj.getId());
				pjStatus.setLike(DBParam.RecordStatus.Default.getCode());
				pjStatus.setCollect(DBParam.RecordStatus.Default.getCode());
				StatusService.Insert(pjStatus);
			}
        }else{
            tempObj = DiscussService.Modify(obj);
        }
        if (tempObj != null) {
			return sharedInstance().TrueData(tempObj, "修改成功!", CodeInfo.Code.OK.getCode());
		} else {
			return sharedInstance().FalseData("修改失败!", CodeInfo.Code.NO.getCode());
		}
    }

}
