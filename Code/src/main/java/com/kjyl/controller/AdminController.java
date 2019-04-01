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
import com.kjyl.pojo.Admin;
import com.kjyl.util.CodeInfo;
import com.kjyl.util.DBParam;
import com.kjyl.util.GenerateKey.IdWorker;

import static com.kjyl.util.ResultUtil.sharedInstance;

/**
 * <p> 控制器 Class</p>
 * @author sheryl 自动构建脚本
 */
@Api(value="Admin", description="")
@RestController
@RequestMapping("/Admin")
public class AdminController extends BaseController {

//    @GetMapping("/searchAdminPage")
	@ApiOperation(value = "获取列表", notes= "", httpMethod = "GET")
	@RequestMapping(value="/searchAdminPage", method=RequestMethod.GET)
//  @ApiImplicitParam(name = "data", value = "data描述", required = true, dataType = "UserInfo", paramType = "query")
//  @ApiImplicitParams({
//    @ApiImplicitParam(name="name",value="用户名",dataType="string", paramType = "query",example="xingguo"),
//	  @ApiImplicitParam(name="id",value="用户id",dataType="long", paramType = "query")
//  })
    public Map<String, Object> searchAdminPage(Integer status, String search, int pageNumber, int pageSize, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        mapSearch.put("search", search);
        if(status != null && status != -1){
        	mapSearch.put(Admin.COLUMN_Status, status);
        }
        PageInfo<Admin> page = this.AdminService.SearchPage(mapSearch, pageNumber, pageSize);
        mapResult.put(CodeInfo.sRowKey, page.getList());
        mapResult.put(CodeInfo.sTotalKey, page.getTotal());
        return sharedInstance().TrueData(mapResult, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @PostMapping("/setAdminStatus")
    @RequestMapping(value="/setAdminStatus", method=RequestMethod.POST)
    @ApiOperation(value = "设置状态")
    public Map<String, Object> setAdminStatus(@RequestBody String data){
        Admin temp = JSON.parseObject(data, Admin.class);
        String[] ids = temp.getId().split(",");
        for (String Id : ids){
            if(temp.getStatus() == DBParam.RecordStatus.Delete.getCode()){
                AdminService.RecoverBySpecial(Id);
            }else{
                AdminService.RemoveBySpecial(Id);
            }
        }
        return sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @GetMapping("/searchAdmin/{id}")
    @RequestMapping(value="/searchAdmin/{id}", method=RequestMethod.GET)
    @ApiOperation(value = "根据编号查询内容")
    public Map<String, Object> searchAdmin(@PathVariable("id") String Id){
        Admin temp = AdminService.SearchBySpecial(Id);
        if(temp != null){
        	return sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    	}else{
    		return sharedInstance().FalseData("获取失败!", CodeInfo.Code.NO.getCode());
		}
    }

//    @PostMapping("/modifyAdmin")
    @RequestMapping(value="/modifyAdmin", method=RequestMethod.POST)
    @ApiOperation(value = "修改")
    public Map<String, Object> modifyAdmin(@RequestBody String data, HttpServletRequest request) {
        Admin temp = JSON.parseObject(data, Admin.class);
        Admin obj = new Admin();
        boolean isNew = false;
        if("0".equals(temp.getId())){
            isNew = true;
        }else{
            obj = AdminService.SearchBySpecial(temp.getId());
            if(obj == null){
                isNew = true;
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

        Admin tempObj = null;
        if(isNew){
            obj.setId(IdWorker.CreateStringNewId());
            obj.setStatus(DBParam.RecordStatus.Default.getCode());
            tempObj = AdminService.Insert(obj);
        }else{
            tempObj = AdminService.Modify(obj);
        }
        if (tempObj != null) {
			return sharedInstance().TrueData(tempObj, "修改成功!", CodeInfo.Code.OK.getCode());
		} else {
			return sharedInstance().FalseData("修改失败!", CodeInfo.Code.NO.getCode());
		}
    }

}
