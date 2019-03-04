package com.kjyl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import com.code.domain.Admin;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.code.until.CommonStatus;
import com.code.until.CommonUntil;

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
    public Map searchAdminPage(int status, String search, int pageNumber, int pageSize, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        mapSearch.put("search", search);
        if(status!=-1){
        	mapSearch.put("Status", status);
        }
        PageInfo<Admin> page = this.ReadAdminService.SearchPage(mapSearch, pageNumber, pageSize);
        mapResult.put("rows", page.getList());
        mapResult.put("total", page.getTotal());
        return mapResult;
    }

    @PostMapping("/setAdminStatus")
    @ApiOperation(value = "设置状态")
    public Map setAdminStatus(String data){
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        Admin temp = JSON.parseObject(data,Admin.class);
        String[] ids = temp.getId().split(",");
        for (String id : ids){
            if(temp.getStatus() == Integer.parseInt(CommonStatus.Status.Ectivity.getId())){
                AdminService.RecoverBySpecial(Id);
            }else{
                AdminService.RemoveBySpecial(Id);
            }
            mapSearch.clear();
        }
        mapResult.put("code", 0);
        mapResult.put("message", "操作成功");
        return mapResult;
    }

    @GetMapping("/searchAdmin/{id}")
    @ApiOperation(value = "根据编号查询内容")
    public Map searchAdmin(@PathVariable("id") String Id){
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        Admin temp = ReadAdminService.SearchBySpecial(Id);
        if(temp != null){
        	mapResult.put("code", 0);
        	mapResult.put("data", temp);
        	mapResult.put("message", "获取成功");
    	}else{
    		mapResult.put("code", -1);
    		mapResult.put("data", temp);
    		mapResult.put("message", "获取失败");
		}
        return mapResult;
    }


    @PostMapping("/modifyAdmin")
    @ApiOperation(value = "修改")
    public Map modifyAdmin(String data, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Admin temp = JSON.parseObject(data, Admin.class);
        Admin  obj = new Admin();
        boolean isNew = false;
        if("0".equals(temp.getId())){
            isNew=true;
        }else{
            obj = ReadAdminService.SearchBySpecial(String.valueOf(temp.getId()));
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
            obj.setID(CommonUntil.getInstance().CreateNewId());
            obj.setStatus(Integer.parseInt(CommonStatus.Status.Ectivity.getId()));
            tempObj=AdminService.Insert(obj);
        }else{
            tempObj=AdminService.Modify(obj);
        }
        mapResult.put("code", tempObj != null ? 0 : -1);
        mapResult.put("message", tempObj != null ? "修改成功" : "修改失败");
        return mapResult;
    }

}
