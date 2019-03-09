package com.kjyl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kjyl.pojo.Errorlog;
import com.kjyl.service.ErrorlogService;

import com.kjyl.util.CodeInfo;
import com.kjyl.util.DBParam;
import com.kjyl.util.ResultUtil;
import com.kjyl.util.GenerateKey.IdWorker;

/**
 * <p> 控制器 Class</p>
 * @author sheryl 自动构建脚本
 */
@Api(value="Errorlog", description="")
@RestController
@RequestMapping("/Errorlog")
public class ErrorlogController extends BaseController {
	
//  未知错误
    @RequestMapping(value = "/Interface/Unknown")
    public @ResponseBody Map<String, Object> unknownMethod() {
        ResultUtil errorDetail = ResultUtil.sharedInstance();
        return errorDetail.otherError(CodeInfo.ErrorMessageType.UnKownError,null);
    }

    //  参数错误
    @RequestMapping(value = "/Interface/Parameter")
    public @ResponseBody Map<String, Object> parameterMethod() {
        ResultUtil errorDetail = ResultUtil.sharedInstance();
        return errorDetail.otherError(CodeInfo.ErrorMessageType.Parameter, null);
    }

    //  认证失败
    @RequestMapping(value = "/Interface/Authorized")
    public @ResponseBody Map<String, Object> authorizedMethod() {
        ResultUtil errorDetail = ResultUtil.sharedInstance();
        return errorDetail.otherError(CodeInfo.ErrorMessageType.Authorized, null);
    }

    //  用户不存在
    @RequestMapping(value = "/Interface/UserNoExist")
    public @ResponseBody Map<String, Object> userNoExistMethod() {
        ResultUtil errorDetail = ResultUtil.sharedInstance();
        return errorDetail.otherError(CodeInfo.ErrorMessageType.UserNoExist,null);
    }
    
    //  认证失败
    @RequestMapping(value = "/Interface/JsonError")
    public @ResponseBody Map<String, Object> jsonErrorMethod(HttpServletRequest request) {
        ResultUtil errorDetail = ResultUtil.sharedInstance();
        return errorDetail.otherError(CodeInfo.ErrorMessageType.JsonParseError,request);
    }

//    @GetMapping("/searchErrorlogPage")
	@ApiOperation(value = "获取列表", notes= "", httpMethod = "GET")
	@RequestMapping(value="/searchErrorlogPage", method=RequestMethod.GET)
//  @ApiImplicitParam(name = "data", value = "data描述", required = true, dataType = "UserInfo", paramType = "query")
//  @ApiImplicitParams({
//    @ApiImplicitParam(name="name",value="用户名",dataType="string", paramType = "query",example="xingguo"),
//	  @ApiImplicitParam(name="id",value="用户id",dataType="long", paramType = "query")
//  })
    public Map<String, Object> searchErrorlogPage(Integer status, String search, int pageNumber, int pageSize, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        mapSearch.put("search", search);
        if(status != null && status != -1){
        	mapSearch.put(Errorlog.COLUMN_Status, status);
        }
        PageInfo<Errorlog> page = this.ErrorlogService.SearchPage(mapSearch, pageNumber, pageSize);
        mapResult.put(CodeInfo.sRowKey, page.getList());
        mapResult.put(CodeInfo.sTotalKey, page.getTotal());
        return ResultUtil.sharedInstance().TrueData(mapResult, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @PostMapping("/setErrorlogStatus")
    @RequestMapping(value="/setErrorlogStatus", method=RequestMethod.POST)
    @ApiOperation(value = "设置状态")
    public Map<String, Object> setErrorlogStatus(String data){
        Errorlog temp = JSON.parseObject(data, Errorlog.class);
        String[] ids = temp.getId().split(",");
        for (String Id : ids){
            if(temp.getStatus() == DBParam.RecordStatus.Delete.getCode()){
                ErrorlogService.RecoverBySpecial(Id);
            }else{
                ErrorlogService.RemoveBySpecial(Id);
            }
        }
        return ResultUtil.sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @GetMapping("/searchErrorlog/{id}")
    @RequestMapping(value="/searchErrorlog/{id}", method=RequestMethod.GET)
    @ApiOperation(value = "根据编号查询内容")
    public Map<String, Object> searchErrorlog(@PathVariable("id") String Id){
        Errorlog temp = ErrorlogService.SearchBySpecial(Id);
        if(temp != null){
        	return ResultUtil.sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    	}else{
    		return ResultUtil.sharedInstance().FalseData("获取失败!", CodeInfo.Code.NO.getCode());
		}
    }

//    @PostMapping("/modifyErrorlog")
    @RequestMapping(value="/modifyErrorlog", method=RequestMethod.POST)
    @ApiOperation(value = "修改")
    public Map<String, Object> modifyErrorlog(String data, HttpServletRequest request) {
        Errorlog temp = JSON.parseObject(data, Errorlog.class);
        Errorlog obj = new Errorlog();
        boolean isNew = false;
        if("0".equals(temp.getId())){
            isNew = true;
        }else{
            obj = ErrorlogService.SearchBySpecial(temp.getId());
            if(obj == null){
                isNew = true;
            }
        }
        obj.setUseId(temp.getUseId());
        obj.setTypeId(temp.getTypeId());
        obj.setNote(temp.getNote());
        obj.setVersion(temp.getVersion());
        obj.setDevice(temp.getDevice());
        obj.setDelete(temp.getDelete());
        obj.setModifyTime(temp.getModifyTime());

        Errorlog tempObj = null;
        if(isNew){
            obj.setId(IdWorker.CreateStringNewId());
            obj.setStatus(DBParam.RecordStatus.Default.getCode());
            tempObj = ErrorlogService.Insert(obj);
        }else{
            tempObj = ErrorlogService.Modify(obj);
        }
        if (tempObj != null) {
			return ResultUtil.sharedInstance().TrueData(tempObj, "修改成功!", CodeInfo.Code.OK.getCode());
		} else {
			return ResultUtil.sharedInstance().FalseData("修改失败!", CodeInfo.Code.NO.getCode());
		}
    }

}
