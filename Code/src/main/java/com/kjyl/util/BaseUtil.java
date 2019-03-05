/**
 * 
 */
package com.kjyl.util;

/**
 * @author GeJiangbo
 * @date 2017年5月12日
 */
public class BaseUtil {//http请求的头部不区分大小写，method区分大小写
	public static final String userIdKey    = "userId";
	public static final String tokenKey    = "token";
	public static final String userKey = "user";
    public static final String paramKey    = "param";
    public static final String statusKey   = "status";
    public static final String dataKey     = "data";
    public static final String codeKey     = "code";
    public static final String newRetureKey  = "wh_code";//0 成功 -1失败 (自定义错误码)(40001 - 49999)
    public static final String existKey  = "exist";
    public static final String messageKey  = "message";
    public static final String typeKey     = "type";
    public static final String mobileKey   = "mobile";
    public static final String previousKey = "previous";
    public static final String portraitKey = "portrait";
    public static final String sessionKey  = "session";
    public static final String pageLimitKey  = "limit";
    public static final String pageStartKey  = "start";
    public static final String pageKey  = "page";
    public static final String pictureType_JPG  = ".jpg";
    public static final String AuthHistoryPath = "Resource/Upload/Authentication/History";//身份证历史路径
    public static final String AuthPath = "Resource/Upload/Authentication";//身份证路径
    public static final String UserImagePath = "Resource/Upload/Portrait";//头像路径
    public static final String ReportPath = "Resource/Upload/Report";//头像路径
    public static final String UserImageHistoryPath = "Resource/Upload/Portrait/History";//头像历史路径
    public static final String AppHistoryPath = "Resource/Upload/APP/History";//app历史路径
    public static final String AppPath = "Resource/Upload/APP";//app保存路径
    public static final String AdminImagePath = "Resource/Upload/AdminPortrait";//默认管理员头像路径
    public static final String Activity = "Resource/Upload/Activity";//身份证路径
    public static final String NewAppName = "whohelp";//保存app名
    public static final String NewAppNameTemp = "whohelp_Temp"; //保存的临时app名字
    public static final String NewAppNameExtendName = ".apk"; //保存的app后缀名
    public static final String DefaultDistance = "2000";//默认搜索距离
      
}
