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

import com.kjyl.pojo.Status;
import com.kjyl.service.StatusService;

import com.kjyl.util.CodeInfo;
import com.kjyl.util.DBParam;
import com.kjyl.util.GenerateKey.IdWorker;

/**
 * <p> 控制器 Class</p>
 * @author sheryl 自动构建脚本
 */
@Api("Status")
@RestController
@RequestMapping("/Status")
public class StatusController extends BaseController {

//    @GetMapping("/searchStatusPage")
    @ApiOperation(value = "获取列表")
    public Map<String, Object> searchStatusPage(int status, String search, int pageNumber, int pageSize, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        mapSearch.put("search", search);
        if(status!=-1){
        	mapSearch.put("Status", status);
        }
        PageInfo<Status> page = this.StatusService.SearchPage(mapSearch, pageNumber, pageSize);
        mapResult.put(CodeInfo.sRowKey, page.getList());
        mapResult.put(CodeInfo.sTotalKey, page.getTotal());
        return mapResult;
    }

//    @PostMapping("/setStatusStatus")
    @ApiOperation(value = "设置状态")
    public Map<String, Object> setStatusStatus(String data){
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Status temp = JSON.parseObject(data, Status.class);
        String[] ids = temp.getId().split(",");
        for (String Id : ids){
            if(temp.getStatus() == DBParam.RecordStatus.Delete.getCode()){
                StatusService.RecoverBySpecial(Id);
            }else{
                StatusService.RemoveBySpecial(Id);
            }
        }
        mapResult.put(CodeInfo.sRowKey, 0);
        mapResult.put(CodeInfo.sMessageKey, "操作成功");
        return mapResult;
    }

//    @GetMapping("/searchStatus/{id}")
    @ApiOperation(value = "根据编号查询内容")
    public Map<String, Object> searchStatus(@PathVariable("id") String Id){
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Status temp = StatusService.SearchBySpecial(Id);
        if(temp != null){
        	mapResult.put(CodeInfo.sRowKey, 0);
        	mapResult.put(CodeInfo.sDataKey, temp);
        	mapResult.put(CodeInfo.sMessageKey, "获取成功");
    	}else{
    		mapResult.put(CodeInfo.sRowKey, -1);
    		mapResult.put(CodeInfo.sDataKey, temp);
    		mapResult.put(CodeInfo.sMessageKey, "获取失败");
		}
        return mapResult;
    }

//    @PostMapping("/modifyStatus")
    @ApiOperation(value = "修改")
    public Map<String, Object> modifyStatus(String data, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Status temp = JSON.parseObject(data, Status.class);
        Status obj = new Status();
        boolean isNew = false;
        if("0".equals(temp.getId())){
            isNew=true;
        }else{
            obj = StatusService.SearchBySpecial(temp.getId());
            if(obj==null){
                isNew=true;
            }
        }

        obj.setUseId(temp.getUseId());
        obj.setObjectId(temp.getObjectId());
        obj.setLike(temp.getLike());
        obj.setMemo(temp.getMemo());
        obj.setDelete(temp.getDelete());
        obj.setModifyTime(temp.getModifyTime());

        Status tempObj=null;
        if(isNew){
            obj.setId(String.valueOf(IdWorker.CreateNewId()));
            obj.setStatus(DBParam.RecordStatus.Default.getCode());
            tempObj=StatusService.Insert(obj);
        }else{
            tempObj=StatusService.Modify(obj);
        }
        mapResult.put(CodeInfo.sRowKey, tempObj != null ? 0 : -1);
        mapResult.put(CodeInfo.sMessageKey, tempObj != null ? "修改成功" : "修改失败");
        return mapResult;
    }

}
