package com.kjyl.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.kjyl.bean.GymDataBean;
import com.kjyl.pojo.Clock;
import com.kjyl.pojo.Course;
import com.kjyl.pojo.Info;
import com.kjyl.pojo.User;
import com.kjyl.util.CodeInfo;
import com.kjyl.util.DBParam;
import com.kjyl.util.GenerateKey.IdWorker;
import com.kjyl.util.ResultUtil;

/**
 * <p> 控制器 Class</p>
 * @author sheryl 自动构建脚本
 */
@Api(value="User", description="")
@RestController
@RequestMapping("/User")
public class UserController extends BaseController {

//    @GetMapping("/searchUserPage")
	@ApiOperation(value = "获取列表", notes= "", httpMethod = "GET")
	@RequestMapping(value="/searchUserPage", method=RequestMethod.GET)
//  @ApiImplicitParam(name = "data", value = "data描述", required = true, dataType = "UserInfo", paramType = "query")
//  @ApiImplicitParams({
//    @ApiImplicitParam(name="name",value="用户名",dataType="string", paramType = "query",example="xingguo"),
//	  @ApiImplicitParam(name="id",value="用户id",dataType="long", paramType = "query")
//  })
    public Map<String, Object> searchUserPage(Integer status, String search, int pageNumber, int pageSize, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        mapSearch.put("search", search);
        if(status != null && status != -1){
        	mapSearch.put(User.COLUMN_Status, status);
        }
        PageInfo<User> page = this.UserService.SearchPage(mapSearch, pageNumber, pageSize);
        mapResult.put(CodeInfo.sRowKey, page.getList());
        mapResult.put(CodeInfo.sTotalKey, page.getTotal());
        return ResultUtil.sharedInstance().TrueData(mapResult, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @PostMapping("/setUserStatus")
    @RequestMapping(value="/setUserStatus", method=RequestMethod.POST)
    @ApiOperation(value = "设置状态")
    public Map<String, Object> setUserStatus(@RequestBody String data){
        User temp = JSON.parseObject(data, User.class);
        String[] ids = temp.getId().split(",");
        for (String Id : ids){
            if(temp.getStatus() == DBParam.RecordStatus.Delete.getCode()){
                UserService.RecoverBySpecial(Id);
            }else{
                UserService.RemoveBySpecial(Id);
            }
        }
        return ResultUtil.sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @GetMapping("/searchUser/{id}")
    @RequestMapping(value="/searchUser/{id}", method=RequestMethod.GET)
    @ApiOperation(value = "根据编号查询内容")
    public Map<String, Object> searchUser(@PathVariable("id") String Id){
        User temp = UserService.SearchBySpecial(Id);
        if (temp == null) {
            return ResultUtil.sharedInstance().FalseData("获取失败!", CodeInfo.Code.NO.getCode());
        } else {
        	return ResultUtil.sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    	}
    }

//    @PostMapping("/modifyUser")
    @RequestMapping(value="/modifyUser", method=RequestMethod.POST)
    @ApiOperation(value = "修改")
    public Map<String, Object> modifyUser(@RequestBody String data, HttpServletRequest request) {
        User temp = JSON.parseObject(data, User.class);
        User obj = new User();
        boolean isNew = false;
        if("0".equals(temp.getId())){
            isNew = true;
        }else{
            obj = UserService.SearchBySpecial(temp.getId());
            if(obj == null){
                isNew = true;
                obj = new User();
            }
        }
        obj.setHeadIcon(temp.getHeadIcon());
        obj.setName(temp.getName());
        obj.setSex(temp.getSex());
        obj.setBirthday(temp.getBirthday());
        obj.setPhone(temp.getPhone());
        obj.setLogicId(temp.getLogicId());
        obj.setQQ(temp.getQQ());
        obj.setMicroBlog(temp.getMicroBlog());
        obj.setQRCode(temp.getQRCode());
        obj.setWeChatOpenId(temp.getWeChatOpenId());
        obj.setPassword(temp.getPassword());
        obj.setPayPassword(temp.getPayPassword());
        obj.setCity(temp.getCity());
        obj.setFitClub(temp.getFitClub());
        obj.setLevel(temp.getLevel());
        obj.setDelete(temp.getDelete());
        obj.setModifyTime(temp.getModifyTime());
        obj.setWallet(temp.getWallet());

        User tempObj = null;
        if(isNew){
            obj.setId(IdWorker.CreateStringNewId());
            obj.setStatus(DBParam.RecordStatus.Default.getCode());
            tempObj = UserService.Insert(obj);
        }else{
            tempObj = UserService.Modify(obj);
        }
        if (tempObj != null) {
			return ResultUtil.sharedInstance().TrueData(tempObj, "修改成功!", CodeInfo.Code.OK.getCode());
		} else {
			return ResultUtil.sharedInstance().FalseData("修改失败!", CodeInfo.Code.NO.getCode());
		}
    }
    
    @RequestMapping(value="/searchUserInfo/{id}", method=RequestMethod.GET)
    @ApiOperation(value = "个人信息")
    public Map<String, Object> searchUserInfo(@PathVariable("id") String Id){
    	Map<String, Object> mapSearch = new HashMap<String, Object>();
    	mapSearch.put(DBParam.sUIdKey, Id);
    	mapSearch.put(DBParam.sDeleteKey, DBParam.RecordStatus.Delete.getCode());
    	GymDataBean pjData = UserService.SearchGymDataByCondition(mapSearch);
        if(pjData != null){
        	return ResultUtil.sharedInstance().TrueData(pjData, "请求成功!", CodeInfo.Code.OK.getCode());
    	}else{
    		return ResultUtil.sharedInstance().FalseData("获取失败!", CodeInfo.Code.NO.getCode());
		}
    }
    
    @ApiOperation(value = "次首页列表", notes= "", httpMethod = "GET")
	@RequestMapping(value="/nextHomePage", method=RequestMethod.GET)
    public Map<String, Object> nextHomePage(String id, int pageNum, int pageSize, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        mapSearch.put(Clock.COLUMN_UseId, id);
        mapSearch.put(DBParam.sDeleteKey, DBParam.RecordStatus.Delete.getCode());
        int num = this.ClockService.SearchData(mapSearch);
        mapSearch.put(Clock.COLUMN_CreateTime, new Date());
        List<Clock> lstCl = this.ClockService.SearchByCondition(mapSearch);
        
        List<Course> lstCo = this.CourseService.SearchBySpecialRand();
        mapSearch.clear();
        mapSearch.put(Info.COLUMN_Delete, DBParam.RecordStatus.Delete.getCode());
        PageInfo<Info> pageIn = this.InfoService.SearchPage(mapSearch, pageNum, pageSize);
        mapResult.put("ClockNumber", num);
        mapResult.put("Clock", lstCl != null && lstCl.size() > 0 ? true : false);
        mapResult.put("Course", lstCo);
        mapResult.put("Info", pageIn.getList());
        mapResult.put("InfoTotal", pageIn.getTotal());
        return ResultUtil.sharedInstance().TrueData(mapResult, "请求成功!", CodeInfo.Code.OK.getCode());
    }
    
}
