package com.huban.util;

import java.text.SimpleDateFormat;

/** 
 * @author 作者: 葛江波
 * @date 创建时间: 2016-10-4 上午11:51:20 
 * @version 版本: 1.0 
 */
public class Constant {

	
	public static final String userKey = "FRAME_USER_KEY";
	
	public static final String TENANT_ID = "FRAME_TENANT_ID";
	
	public static final String USER_FLODERID_KEY = "USER_FLODERID_KEY";
	
	public static final String ExpRuleCondition = "1";//代表表达式规则的条件配置
	
	public static final String ExpRuleAction = "2";//代表表达式规则的动作配置
	
	public  static  SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
	
	public  static  SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public  static  SimpleDateFormat sdfDate_zn = new SimpleDateFormat("yyyy年MM月dd日");
	
}
