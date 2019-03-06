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

import com.kjyl.pojo.User;
import com.kjyl.service.UserService;

import com.kjyl.util.CodeInfo;
import com.kjyl.util.DBParam;
import com.kjyl.util.GenerateKey.IdWorker;

/**
 * <p> 控制器 Class</p>
 * @author sheryl 自动构建脚本
 */
@Api("User")
@RestController
@RequestMapping("/User")
public class UserController extends BaseController {

//    @GetMapping("/searchUserPage")
    @ApiOperation(value = "获取列表")
    public Map<String, Object> searchUserPage(int status, String search, int pageNumber, int pageSize, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        mapSearch.put("search", search);
        if(status!=-1){
        	mapSearch.put("Status", status);
        }
        PageInfo<User> page = this.UserService.SearchPage(mapSearch, pageNumber, pageSize);
        mapResult.put(CodeInfo.sRowKey, page.getList());
        mapResult.put(CodeInfo.sTotalKey, page.getTotal());
        return mapResult;
    }

//    @PostMapping("/setUserStatus")
    @ApiOperation(value = "设置状态")
    public Map<String, Object> setUserStatus(String data){
        Map<String, Object> mapResult = new HashMap<String, Object>();
        User temp = JSON.parseObject(data, User.class);
        String[] ids = temp.getId().split(",");
        for (String Id : ids){
            if(temp.getStatus() == DBParam.RecordStatus.Delete.getCode()){
                UserService.RecoverBySpecial(Id);
            }else{
                UserService.RemoveBySpecial(Id);
            }
        }
        mapResult.put(CodeInfo.sRowKey, 0);
        mapResult.put(CodeInfo.sMessageKey, "操作成功");
        return mapResult;
    }

//    @GetMapping("/searchUser/{id}")
    @ApiOperation(value = "根据编号查询内容")
    public Map<String, Object> searchUser(@PathVariable("id") String Id){
        Map<String, Object> mapResult = new HashMap<String, Object>();
        User temp = UserService.SearchBySpecial(Id);
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

//    @PostMapping("/modifyUser")
    @ApiOperation(value = "修改")
    public Map<String, Object> modifyUser(String data, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        User temp = JSON.parseObject(data, User.class);
        User obj = new User();
        boolean isNew = false;
        if("0".equals(temp.getId())){
            isNew=true;
        }else{
            obj = UserService.SearchBySpecial(temp.getId());
            if(obj==null){
                isNew=true;
            }
        }

        obj.setHeadIcon(temp.getHeadIcon());
        obj.setName(temp.getName());
        obj.setSex(temp.getSex());
        obj.setPhone(temp.getPhone());
        obj.setQQ(temp.getQQ());
        obj.setMicroBlog(temp.getMicroBlog());
        obj.setQRCode(temp.getQRCode());
        obj.setWeChatOpenID(temp.getWeChatOpenID());
        obj.setPassword(temp.getPassword());
        obj.setPayPassword(temp.getPayPassword());
        obj.setCity(temp.getCity());
        obj.setFitClub(temp.getFitClub());
        obj.setDelete(temp.getDelete());
        obj.setModifyTime(temp.getModifyTime());

        User tempObj=null;
        if(isNew){
            obj.setId(String.valueOf(IdWorker.CreateNewId()));
            obj.setStatus(DBParam.RecordStatus.Default.getCode());
            tempObj=UserService.Insert(obj);
        }else{
            tempObj=UserService.Modify(obj);
        }
        mapResult.put(CodeInfo.sRowKey, tempObj != null ? 0 : -1);
        mapResult.put(CodeInfo.sMessageKey, tempObj != null ? "修改成功" : "修改失败");
        return mapResult;
    }

}
