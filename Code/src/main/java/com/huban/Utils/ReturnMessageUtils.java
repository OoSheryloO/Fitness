/**
 * 
 */
package com.huban.Utils;

/**
 * @author GeJiangbo
 * @date 2017年5月12日
 */
public class ReturnMessageUtils {//http请求的头部不区分大小写，method区分大小写
	
	public static final String trueReturnMessageKey  = "请求成功";
	public static final String falseReturnMessageKey  = "系统繁忙，请稍候再试";
	
	public static final String truePseudonymMessageKey  = "该笔名可以使用";
	public static final String falsePseudonymMessageKey  = "该笔名已被他人使用，请更换";
	
	public static final String trueSignInMessageKey  = "登录成功";
	public static final String falseSignInMessageKey  = "笔名或密码错误，请重新输入";
	
	public static final String trueChangePwdMessageKey  = "修改成功";
	public static final String falseChangePwdMessageKey  = "原密码不正确，请重新输入";
	public static final String trueBindingMessageKey  = "绑定成功";
	
	public static final String nullMessageKey = "缺少参数";
	
	public static final String sMorePeopleInUse = "该手机号已被三个学生绑定，请更换";
	public static final String sByOtherPeopleUse = "该手机号已被他人使用，请更换手机号";
	public static final String sByOtherTeacherUse = "该手机号已被老师使用，请更换手机号";
	public static final String sByOtherAgentUse = "该手机号已被代理商使用，请更换手机号";
	
	public static final String sError_User_NotFound  = "用户不存在";
	
	public static final String sError_PushName_NotFound  = "缺少推送设备名称参数";
	public static final String sError_PushSystem_NotFound  = "缺少推送设备系统参数";
	public static final String sError_PushModel_NotFound  = "缺少推送设备Model参数";
	public static final String sError_PushChannel_NotFound = "缺少推送设备channel参数";
	public static final String sError_PushToken_NotFound = "缺少推送设备Token参数";
	
}