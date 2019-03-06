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

import com.kjyl.pojo.Userlog;
import com.kjyl.service.UserlogService;

import com.kjyl.util.CodeInfo;
import com.kjyl.util.DBParam;
import com.kjyl.util.GenerateKey.IdWorker;

/**
 * <p> 控制器 Class</p>
 * @author sheryl 自动构建脚本
 */
@Api("Userlog")
@RestController
@RequestMapping("/Userlog")
public class UserlogController extends BaseController {

//    @GetMapping("/searchUserlogPage")
    @ApiOperation(value = "获取列表")
    public Map<String, Object> searchUserlogPage(int status, String search, int pageNumber, int pageSize, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        mapSearch.put("search", search);
        if(status!=-1){
        	mapSearch.put("Status", status);
        }
        PageInfo<Userlog> page = this.UserlogService.SearchPage(mapSearch, pageNumber, pageSize);
        mapResult.put(CodeInfo.sRowKey, page.getList());
        mapResult.put(CodeInfo.sTotalKey, page.getTotal());
        return mapResult;
    }

//    @PostMapping("/setUserlogStatus")
    @ApiOperation(value = "设置状态")
    public Map<String, Object> setUserlogStatus(String data){
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Userlog temp = JSON.parseObject(data, Userlog.class);
        String[] ids = temp.getId().split(",");
        for (String Id : ids){
            if(temp.getStatus() == DBParam.RecordStatus.Delete.getCode()){
                UserlogService.RecoverBySpecial(Id);
            }else{
                UserlogService.RemoveBySpecial(Id);
            }
        }
        mapResult.put(CodeInfo.sRowKey, 0);
        mapResult.put(CodeInfo.sMessageKey, "操作成功");
        return mapResult;
    }

//    @GetMapping("/searchUserlog/{id}")
    @ApiOperation(value = "根据编号查询内容")
    public Map<String, Object> searchUserlog(@PathVariable("id") String Id){
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Userlog temp = UserlogService.SearchBySpecial(Id);
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

//    @PostMapping("/modifyUserlog")
    @ApiOperation(value = "修改")
    public Map<String, Object> modifyUserlog(String data, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Userlog temp = JSON.parseObject(data, Userlog.class);
        Userlog obj = new Userlog();
        boolean isNew = false;
        if("0".equals(temp.getId())){
            isNew=true;
        }else{
            obj = UserlogService.SearchBySpecial(temp.getId());
            if(obj==null){
                isNew=true;
            }
        }

        obj.setUseId(temp.getUseId());
        obj.setTypeId(temp.getTypeId());
        obj.setNote(temp.getNote());
        obj.setVersion(temp.getVersion());
        obj.setDelete(temp.getDelete());
        obj.setModifyTime(temp.getModifyTime());

        Userlog tempObj=null;
        if(isNew){
            obj.setId(String.valueOf(IdWorker.CreateNewId()));
            obj.setStatus(DBParam.RecordStatus.Default.getCode());
            tempObj=UserlogService.Insert(obj);
        }else{
            tempObj=UserlogService.Modify(obj);
        }
        mapResult.put(CodeInfo.sRowKey, tempObj != null ? 0 : -1);
        mapResult.put(CodeInfo.sMessageKey, tempObj != null ? "修改成功" : "修改失败");
        return mapResult;
    }

}
