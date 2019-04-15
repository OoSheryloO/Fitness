package com.kjyl.util.WeChatPay;

public class ConstantUtil {
	 /**
     * 微信开发平台应用ID
     */
    public static final String APP_ID="wx416e37b025fa6664";
    /**
     * 应用对应的AppSecret
     */
    public static final String APP_SECRET="516ccb3ba78313b00d38394b4cb6d4d2";
    /**
     * APP_KEY                   商户平台---api安全---密钥
     */
    public static final String APP_KEY="mGMzMKEZ9yWcYWQyAB71CDiBH7zNBfTD";
    /**
     * 微信支付商户号
     */
    public static final String MCH_ID="1494896412";
    /**
     * 商品描述
     */
    public static final String BODY="小柜子";
    /**
     * 商户号对应的密钥         也是 商户平台---api安全---密钥 同上面那个APP_KEY
     */
    public static final String PARTNER_key = "mGMzMKEZ9yWcYWQyAB71CDiBH7zNBfTD";
    
    /**
     * 商户id                 同微信支付商户号
     */
   public static final String PARTNER_ID = "1494896412";
    /**
     * 常量固定值
     */
    public static final String GRANT_TYPE="client_credential";
    /**
     * 获取预支付id的接口url
     */
    public static String GATEURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    /**
     * 微信服务器回调通知url
     */
    public static String NOTIFY_URL = "";//SystemValue.TENPAY_NOTIFY_URL
    
}