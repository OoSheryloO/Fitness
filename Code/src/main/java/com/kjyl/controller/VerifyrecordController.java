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

import com.kjyl.pojo.Verifyrecord;
import com.kjyl.service.VerifyrecordService;

import com.kjyl.util.CodeInfo;
import com.kjyl.util.DBParam;
import com.kjyl.util.ResultUtil;
import com.kjyl.util.GenerateKey.IdWorker;

/**
 * <p> 控制器 Class</p>
 * @author sheryl 自动构建脚本
 */
@Api(value="Verifyrecord", description="")
@RestController
@RequestMapping("/Verifyrecord")
public class VerifyrecordController extends BaseController {

//    @GetMapping("/searchVerifyrecordPage")
	@ApiOperation(value = "获取列表", notes= "", httpMethod = "GET")
	@RequestMapping(value="/searchVerifyrecordPage", method=RequestMethod.GET)
//  @ApiImplicitParam(name = "data", value = "data描述", required = true, dataType = "UserInfo", paramType = "query")
//  @ApiImplicitParams({
//    @ApiImplicitParam(name="name",value="用户名",dataType="string", paramType = "query",example="xingguo"),
//	  @ApiImplicitParam(name="id",value="用户id",dataType="long", paramType = "query")
//  })
    public Map<String, Object> searchVerifyrecordPage(Integer status, String search, int pageNumber, int pageSize, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        mapSearch.put("search", search);
        if(status != null && status != -1){
        	mapSearch.put(Verifyrecord.COLUMN_Status, status);
        }
        PageInfo<Verifyrecord> page = this.VerifyrecordService.SearchPage(mapSearch, pageNumber, pageSize);
        mapResult.put(CodeInfo.sRowKey, page.getList());
        mapResult.put(CodeInfo.sTotalKey, page.getTotal());
        return ResultUtil.sharedInstance().TrueData(mapResult, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @PostMapping("/setVerifyrecordStatus")
    @RequestMapping(value="/setVerifyrecordStatus", method=RequestMethod.POST)
    @ApiOperation(value = "设置状态")
    public Map<String, Object> setVerifyrecordStatus(String data){
        Verifyrecord temp = JSON.parseObject(data, Verifyrecord.class);
        String[] ids = temp.getId().split(",");
        for (String Id : ids){
            if(temp.getStatus() == DBParam.RecordStatus.Delete.getCode()){
                VerifyrecordService.RecoverBySpecial(Id);
            }else{
                VerifyrecordService.RemoveBySpecial(Id);
            }
        }
        return ResultUtil.sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @GetMapping("/searchVerifyrecord/{id}")
    @RequestMapping(value="/searchVerifyrecord/{id}", method=RequestMethod.GET)
    @ApiOperation(value = "根据编号查询内容")
    public Map<String, Object> searchVerifyrecord(@PathVariable("id") String Id){
        Verifyrecord temp = VerifyrecordService.SearchBySpecial(Id);
        if(temp != null){
        	return ResultUtil.sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    	}else{
    		return ResultUtil.sharedInstance().FalseData("获取失败!", CodeInfo.Code.NO.getCode());
		}
    }

//    @PostMapping("/modifyVerifyrecord")
    @RequestMapping(value="/modifyVerifyrecord", method=RequestMethod.POST)
    @ApiOperation(value = "修改")
    public Map<String, Object> modifyVerifyrecord(String data, HttpServletRequest request) {
        Verifyrecord temp = JSON.parseObject(data, Verifyrecord.class);
        Verifyrecord obj = new Verifyrecord();
        boolean isNew = false;
        if("0".equals(temp.getId())){
            isNew = true;
        }else{
            obj = VerifyrecordService.SearchBySpecial(temp.getId());
            if(obj == null){
                isNew = true;
            }
        }
        obj.setUseId(temp.getUseId());
        obj.setPhone(temp.getPhone());
        obj.setCheckNumber(temp.getCheckNumber());
        obj.setDisableTime(temp.getDisableTime());
        obj.setEnableTime(temp.getEnableTime());
        obj.setVersion(temp.getVersion());
        obj.setDelete(temp.getDelete());
        obj.setModifyTime(temp.getModifyTime());

        Verifyrecord tempObj = null;
        if(isNew){
            obj.setId(IdWorker.CreateStringNewId());
            obj.setStatus(DBParam.RecordStatus.Default.getCode());
//            tempObj = VerifyrecordService.Insert(obj);
        }else{
            tempObj = VerifyrecordService.Modify(obj);
        }
        if (tempObj != null) {
			return ResultUtil.sharedInstance().TrueData(tempObj, "修改成功!", CodeInfo.Code.OK.getCode());
		} else {
			return ResultUtil.sharedInstance().FalseData("修改失败!", CodeInfo.Code.NO.getCode());
		}
    }

}
