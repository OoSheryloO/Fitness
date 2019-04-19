package com.kjyl.util.WeChatPay;

public class ConstantUtil {
	 /**
     * 微信开发平台应用ID
     */
    public static final String APP_ID="wx44fdba638a1b0dee";//wx44fdba638a1b0dee
    /**
     * 应用对应的AppSecret
     */
    public static final String APP_SECRET="cbedc29bb406732eda73f401443e9b86";//cbedc29bb406732eda73f401443e9b86
    /**
     * APP_KEY                   商户平台---api安全---密钥
     */
    public static final String APP_KEY="woshichenliangfeng33032619961129";
    /**
     * 微信支付商户号
     */
    public static final String MCH_ID="1532253961";
    /**
     * 商品描述
     */
    public static final String BODY="小柜子";
    /**
     * 商户号对应的密钥         也是 商户平台---api安全---密钥 同上面那个APP_KEY
     */
    public static final String PARTNER_key = "woshichenliangfeng33032619961129";
    
    /**
     * 商户id                 同微信支付商户号
     */
   public static final String PARTNER_ID = "1532253961";
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
    public static String NOTIFY_URL = "http://www.sf1938.com/Fitness/pay/WeChatPay";//SystemValue.TENPAY_NOTIFY_URL
    
}