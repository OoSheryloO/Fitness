package com.huban.publicway;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.huban.controller.BaseController;
import com.huban.pojo.UserInfo;
import com.huban.service.UserInfoService;
import com.huban.service.UserService;
import com.huban.service.imp.UserInfoServiceImpl;
import com.huban.util.IdWorker;

/**关于用户的一些公共的方法
 * @author Sheryl
 * @created 2017年9月18日 下午3:04:05
 */
@Configuration
public class UserWay/* extends BaseController*/{
//private UserInfoService userInfoService;

//	private static UserWay userWay;
	
	@Autowired
	private static UserInfoService userInfoService;
	
//	public static void setUserWay(UserWay userWay) {
//		UserWay.userWay = userWay;
//	}
//	public UserInfoService getUserInfoService() {
//		return userInfoService;
//	}
//	public void setUserInfoService(UserInfoService userInfoService) {
//		this.userInfoService = userInfoService;
//	}
//
//	@PostConstruct
//	public void Init(){
//		userWay = this;
//		userWay.userInfoService = this.userInfoService;
//	}
//@Bean
//public UserWay get(){
//    UserWay userWay = new UserWay();
//    UserInfoService userInfoService = new UserInfoServiceImpl();
//    userWay.setUserService = userService;
//    return userWay;
//}
	
	public static void addUserInfoNewMessage(Long userId ,BigDecimal readcount, long deptId ,String address ,String belongsite ,String belongdept ,int position ,int identity){
		UserInfo ui = new UserInfo();
//		ui.setUserinfoId(IdWorker.CreateNewID());
		ui.setUserinfoUserid(userId);
//		ui.setUserinfoSignature(null);
		ui.setUserinfoReadcount(readcount);
//		ui.setUserinfoDepartmentid(deptId);
//		ui.setUserinfoAddress(address);
//		ui.setUserinfoAddressid(null);
//		ui.setUserinfoBelongsite(belongsite);//班级
		ui.setUserinfoBelongdept(belongdept);//部门
		ui.setUserinfoPosition(position);//0:没有 1:行长 2:副行长 3:课长 4:副课长 5:领读者 6:同读者 
		ui.setUserinfoIdentity(identity);//1:老师 2:学生
		userInfoService.AddNewMessage(ui);
		
	}
	
	public static UserInfo AddUserInfoNewMessage(Long userId , String Number, BigDecimal readcount, String deptId ,String address ,String belongsite ,String belongdept ,int position ,int identity, String patriarchid){
		UserInfo ui = new UserInfo();
//		ui.setUserinfoId(IdWorker.CreateNewID());
		ui.setUserinfoUserid(userId);
		ui.setUserinfoNumber(Number);
//		ui.setUserinfoSignature(null);
		ui.setUserinfoReadcount(readcount);
		ui.setUserinfoDepartmentid(deptId);
//		ui.setUserinfoAddress(address);
//		ui.setUserinfoAddressid(null);
//		ui.setUserinfoBelongsite(belongsite);//班级
		ui.setUserinfoBelongdept(belongdept);//部门
		ui.setUserinfoPosition(position);//0:没有 1:行长 2:副行长 3:课长 4:副课长 5:领读者 6:同读者 
		ui.setUserinfoIdentity(identity);//1:老师 2:学生 3:代理商 4:家长 5:游客
		ui.setUserinfoPatriarchid(patriarchid);
		return ui;
	}
}
