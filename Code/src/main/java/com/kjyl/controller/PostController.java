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
import com.kjyl.pojo.Post;
import com.kjyl.util.CodeInfo;
import com.kjyl.util.DBParam;
import com.kjyl.util.GenerateKey.IdWorker;

import static com.kjyl.util.ResultUtil.sharedInstance;

/**
 * <p> 控制器 Class</p>
 * @author sheryl 自动构建脚本
 */
@Api(value="Post", description="")
@RestController
@RequestMapping("/Post")
public class PostController extends BaseController {

//    @GetMapping("/searchPostPage")
	@ApiOperation(value = "获取列表", notes= "", httpMethod = "GET")
	@RequestMapping(value="/searchPostPage", method=RequestMethod.GET)
//  @ApiImplicitParam(name = "data", value = "data描述", required = true, dataType = "UserInfo", paramType = "query")
//  @ApiImplicitParams({
//    @ApiImplicitParam(name="name",value="用户名",dataType="string", paramType = "query",example="xingguo"),
//	  @ApiImplicitParam(name="id",value="用户id",dataType="long", paramType = "query")
//  })
    public Map<String, Object> searchPostPage(Integer status, String id, int pageNumber, int pageSize, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        mapSearch.put(Post.COLUMN_UseId, id);
        if(status != null && status != -1){
        	mapSearch.put(Post.COLUMN_Status, status);
        }
        PageInfo<Post> page = this.PostService.SearchPage(mapSearch, pageNumber, pageSize);
        mapResult.put(CodeInfo.sRowKey, page.getList());
        mapResult.put(CodeInfo.sTotalKey, page.getTotal());
        return sharedInstance().TrueData(mapResult, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @PostMapping("/setPostStatus")
    @RequestMapping(value="/setPostStatus", method=RequestMethod.POST)
    @ApiOperation(value = "设置状态")
    public Map<String, Object> setPostStatus(@RequestBody String data){
        Post temp = JSON.parseObject(data, Post.class);
        String[] ids = temp.getId().split(",");
        for (String Id : ids){
            if(temp.getStatus() == DBParam.RecordStatus.Delete.getCode()){
                PostService.RecoverBySpecial(Id);
            }else{
                PostService.RemoveBySpecial(Id);
            }
        }
        return sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @GetMapping("/searchPost/{id}")
    @RequestMapping(value="/searchPost/{id}", method=RequestMethod.GET)
    @ApiOperation(value = "根据编号查询内容")
    public Map<String, Object> searchPost(@PathVariable("id") String Id){
        Post temp = PostService.SearchBySpecial(Id);
        if(temp != null){
        	return sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    	}else{
    		return sharedInstance().FalseData("获取失败!", CodeInfo.Code.NO.getCode());
		}
    }

//    @PostMapping("/modifyPost")
    @RequestMapping(value="/modifyPost", method=RequestMethod.POST)
    @ApiOperation(value = "修改")
    public Map<String, Object> modifyPost(@RequestBody String data, HttpServletRequest request) {
        Post temp = JSON.parseObject(data, Post.class);
        Post obj = new Post();
        boolean isNew = false;
        if("0".equals(temp.getId()) || temp.getId() == null){
            isNew = true;
        }else{
            obj = PostService.SearchBySpecial(temp.getId());
            if(obj == null){
                isNew = true;
            }
        }
        obj.setUseId(temp.getUseId());
        obj.setLogicId(temp.getLogicId());
        obj.setTitle(temp.getTitle());
        obj.setContent(temp.getContent());
        obj.setLike(temp.getLike());
        obj.setReview(temp.getReview());
        obj.setCollect(temp.getCollect());
        obj.setDelete(temp.getDelete());
        obj.setModifyTime(temp.getModifyTime());

        Post tempObj = null;
        if(isNew){
            obj.setId(IdWorker.CreateStringNewId());
            obj.setStatus(DBParam.RecordStatus.Default.getCode());
            tempObj = PostService.Insert(obj);
        }else{
            tempObj = PostService.Modify(obj);
        }
        if (tempObj != null) {
			return sharedInstance().TrueData(tempObj, "修改成功!", CodeInfo.Code.OK.getCode());
		} else {
			return sharedInstance().FalseData("修改失败!", CodeInfo.Code.NO.getCode());
		}
    }

}
