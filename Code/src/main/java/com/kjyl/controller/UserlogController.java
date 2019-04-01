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
import com.kjyl.pojo.Userlog;
import com.kjyl.util.CodeInfo;
import com.kjyl.util.DBParam;
import com.kjyl.util.GenerateKey.IdWorker;

import static com.kjyl.util.ResultUtil.sharedInstance;

/**
 * <p> 控制器 Class</p>
 * @author sheryl 自动构建脚本
 */
@Api(value="Userlog", description="")
@RestController
@RequestMapping("/Userlog")
public class UserlogController extends BaseController {

//    @GetMapping("/searchUserlogPage")
	@ApiOperation(value = "获取列表", notes= "", httpMethod = "GET")
	@RequestMapping(value="/searchUserlogPage", method=RequestMethod.GET)
//  @ApiImplicitParam(name = "data", value = "data描述", required = true, dataType = "UserInfo", paramType = "query")
//  @ApiImplicitParams({
//    @ApiImplicitParam(name="name",value="用户名",dataType="string", paramType = "query",example="xingguo"),
//	  @ApiImplicitParam(name="id",value="用户id",dataType="long", paramType = "query")
//  })
    public Map<String, Object> searchUserlogPage(Integer status, String search, int pageNumber, int pageSize, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        mapSearch.put("search", search);
        if(status != null && status != -1){
        	mapSearch.put(Userlog.COLUMN_Status, status);
        }
        PageInfo<Userlog> page = this.UserlogService.SearchPage(mapSearch, pageNumber, pageSize);
        mapResult.put(CodeInfo.sRowKey, page.getList());
        mapResult.put(CodeInfo.sTotalKey, page.getTotal());
        return sharedInstance().TrueData(mapResult, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @PostMapping("/setUserlogStatus")
    @RequestMapping(value="/setUserlogStatus", method=RequestMethod.POST)
    @ApiOperation(value = "设置状态")
    public Map<String, Object> setUserlogStatus(@RequestBody String data){
        Userlog temp = JSON.parseObject(data, Userlog.class);
        String[] ids = temp.getId().split(",");
        for (String Id : ids){
            if(temp.getStatus() == DBParam.RecordStatus.Delete.getCode()){
                UserlogService.RecoverBySpecial(Id);
            }else{
                UserlogService.RemoveBySpecial(Id);
            }
        }
        return sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @GetMapping("/searchUserlog/{id}")
    @RequestMapping(value="/searchUserlog/{id}", method=RequestMethod.GET)
    @ApiOperation(value = "根据编号查询内容")
    public Map<String, Object> searchUserlog(@PathVariable("id") String Id){
        Userlog temp = UserlogService.SearchBySpecial(Id);
        if(temp != null){
        	return sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    	}else{
    		return sharedInstance().FalseData("获取失败!", CodeInfo.Code.NO.getCode());
		}
    }

//    @PostMapping("/modifyUserlog")
    @RequestMapping(value="/modifyUserlog", method=RequestMethod.POST)
    @ApiOperation(value = "修改")
    public Map<String, Object> modifyUserlog(@RequestBody String data, HttpServletRequest request) {
        Userlog temp = JSON.parseObject(data, Userlog.class);
        Userlog obj = new Userlog();
        boolean isNew = false;
        if("0".equals(temp.getId())){
            isNew = true;
        }else{
            obj = UserlogService.SearchBySpecial(temp.getId());
            if(obj == null){
                isNew = true;
            }
        }
        obj.setUseId(temp.getUseId());
        obj.setTypeId(temp.getTypeId());
        obj.setNote(temp.getNote());
        obj.setVersion(temp.getVersion());
        obj.setDelete(temp.getDelete());
        obj.setModifyTime(temp.getModifyTime());

        Userlog tempObj = null;
        if(isNew){
            obj.setId(IdWorker.CreateStringNewId());
            obj.setStatus(DBParam.RecordStatus.Default.getCode());
            tempObj = UserlogService.Insert(obj);
        }else{
            tempObj = UserlogService.Modify(obj);
        }
        if (tempObj != null) {
			return sharedInstance().TrueData(tempObj, "修改成功!", CodeInfo.Code.OK.getCode());
		} else {
			return sharedInstance().FalseData("修改失败!", CodeInfo.Code.NO.getCode());
		}
    }

}
