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

import com.kjyl.pojo.Admin;
import com.kjyl.util.CodeInfo;
import com.kjyl.util.DBParam;
import com.kjyl.util.GenerateKey.IdWorker;

/**
 * <p> 控制器 Class</p>
 * @author sheryl 自动构建脚本
 */
@Api("Admin")
@RestController
@RequestMapping("/Admin")
public class AdminController extends BaseController {

    @GetMapping("/searchAdminPage")
    @ApiOperation(value = "获取列表")
    public Map<String, Object> searchAdminPage(int status, String search, int pageNumber, int pageSize, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        mapSearch.put("search", search);
        if(status!=-1){
        	mapSearch.put("status", status);
        }
        PageInfo<Admin> page = this.AdminService.SearchPage(mapSearch, pageNumber, pageSize);
        mapResult.put(CodeInfo.sRowKey, page.getList());
        mapResult.put(CodeInfo.sTotalKey, page.getTotal());
        return mapResult;
    }

    @PostMapping("/setAdminStatus")
    @ApiOperation(value = "设置状态")
    public Map<String, Object> setAdminStatus(String data){
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Admin temp = JSON.parseObject(data, Admin.class);
        String[] ids = temp.getId().split(",");
        for (String Id : ids){
            if(temp.getStatus() == DBParam.RecordStatus.Delete.getCode()){
                AdminService.RecoverBySpecial(Id);
            }else{
                AdminService.RemoveBySpecial(Id);
            }
        }
        mapResult.put(CodeInfo.sCodeKey, 0);
        mapResult.put(CodeInfo.sMessageKey, "操作成功");
        return mapResult;
    }

    @GetMapping("/searchAdmin/{id}")
    @ApiOperation(value = "根据编号查询内容")
    public Map<String, Object> searchAdmin(@PathVariable("id") String Id){
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Admin temp = AdminService.SearchBySpecial(Id);
        if(temp != null){
        	mapResult.put(CodeInfo.sCodeKey, 0);
        	mapResult.put(CodeInfo.sDataKey, temp);
        	mapResult.put(CodeInfo.sMessageKey, "获取成功");
    	}else{
    		mapResult.put(CodeInfo.sCodeKey, -1);
    		mapResult.put(CodeInfo.sDataKey, temp);
    		mapResult.put(CodeInfo.sMessageKey, "获取失败");
		}
        return mapResult;
    }


    @PostMapping("/modifyAdmin")
    @ApiOperation(value = "修改")
    public Map<String, Object> modifyAdmin(String data, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Admin temp = JSON.parseObject(data, Admin.class);
        Admin  obj = new Admin();
        boolean isNew = false;
        if("0".equals(temp.getId())){
            isNew=true;
        }else{
            obj = AdminService.SearchBySpecial(String.valueOf(temp.getId()));
            if(obj==null){
                isNew=true;
            }
        }

        obj.setName(temp.getName());
        obj.setPassWord(temp.getPassWord());
        obj.setNickName(temp.getNickName());
        obj.setImage(temp.getImage());
        obj.setPhone(temp.getPhone());
        obj.setLevel(temp.getLevel());
        obj.setVersion(temp.getVersion());
        obj.setMemo(temp.getMemo());
        obj.setParentId(temp.getParentId());
        obj.setAreaId(temp.getAreaId());
        obj.setDelete(temp.getDelete());
        obj.setModifyTime(temp.getModifyTime());

        Admin tempObj=null;
        if(isNew){
            obj.setId(String.valueOf(IdWorker.CreateNewId()));
            obj.setStatus(DBParam.RecordStatus.Default.getCode());
            tempObj=AdminService.Insert(obj);
        }else{
            tempObj=AdminService.Modify(obj);
        }
        mapResult.put(CodeInfo.sCodeKey, tempObj != null ? 0 : -1);
        mapResult.put(CodeInfo.sMessageKey, tempObj != null ? "修改成功" : "修改失败");
        return mapResult;
    }

}
