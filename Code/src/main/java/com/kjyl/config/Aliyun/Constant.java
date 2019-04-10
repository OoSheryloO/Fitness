package com.kjyl.config.Aliyun;

public class Constant {
	/**
	 * 微信支付需要的信息
	 */
	//初始化
//	public static String APP_ID = "wx48b485a25b4a42c9";//微信开发平台应用id
//	public static String APP_SECRET = "21456e183b4657a9385cc08170db1aa4";//应用对应的凭证
//	public static String PARTNER = "1367071302";//财付通商户号
//	public static String PARTNER_KEY = "WuTuoBangAppKeyQueTingKangMobile";//商户号对应的密钥
	
//	public static String APP_ID = "wx374010a8213a12d0";// 微信开发平台应用id
//	public static String APP_SECRET = "7b6071747ab062ae9beb39ece4844361";// 应用对应的凭证
//	public static String PARTNER = "1486349882";// 财付通商户号
//	public static String PARTNER_KEY = "BeiJingKuaiChengKeJiReadBank2017";// 商户号对应的密钥
//
//	/**
//	 * 微信提现信息
//	 */
//	public static String WXPay_TansfersUrl="https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
//	public static String WXPay_APPID="wxeb5043ba75e89f51";

	public static final String accessKeyId = "LTAIKQT8LtxclmP8";
    public static final String accessKeySecret = "GgAGv7OaFdcJmmZIzG1ihLWUEI2jDj";
	
//	// 初始化
    public static String APP_ID = "wx44fdba638a1b0dee";// 微信开发平台应用id
	public static String APP_SECRET = "cbedc29bb406732eda73f401443e9b86";// 应用对应的凭证
	public static String PARTNER = "1486349882";// 财付通商户号
	public static String PARTNER_KEY = "BeiJingKuaiChengKeJiReadBank2017";// 商户号对应的密钥
	
//	public static String PARTNER = "1367071302";// 财付通商户号
//	public static String PARTNER_KEY = "WuTuoBangAppKeyQueTingKangMobile";// 商户号对应的密钥
	
	//商户号：1486349882
	//秘钥：BeiJingKuaiChengKeJiReadBank2017

	/**
	 * 微信提现信息
	 */
	public static String WXPay_TansfersUrl = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
	public static String WXPay_APPID = "wxeb5043ba75e89f51";

	
	/**
	 * 百度APIStore需要的信息 实名认证接口
	 */
	public static String BDAPI_Url="http://apis.baidu.com/chazhao/idcard/idcard";//身份证实名认证
	public static String BDAPI_AppKey="399385e38546cecf76a3429f69ad7ad9";
	public static String BDAPI_BankAppKey="399385e38546cecf76a3429f69ad7ad9";//银行卡实名认证   699d0ddeaf053182b29df74d7eb0aa76
	public static String BDAPI_BankUrl="http://apis.baidu.com/datatiny/cardinfo/cardinfo";//银行卡实名认证

	/**
	 * 阿凡达数据 身份证实名认证
	 */
	public static String ADF_APPUrl="http://api.avatardata.cn/IdCard/LookUp?key=681b9a1b8e4b491b8ba8bb7f2c30e2f6&";//id=330381199403135412
	
	/**
	 * 百度推送
	 */
//	public static String BDPush_ApiKey = "gMy75cA2ulPxAaIKIoIPTvuz";
//	public static String BDPush_SecretKey = "hPUfTGoIwKxoaDhSsGHZj4G6puz1qKHw";
	public static int BDPush_AndroidAppIDKey = 10133326;
//	public static String BDPush_AndroidApiKey = "vL4j448KCel4fcXdoAP6kxGa";//Sheryl测试
//	public static String BDPush_AndroidSecretKey = "b9b5250be70ea71418da856ed227bac0";
	public static String BDPush_AndroidApiKey = "FhFn6f5PzYEQqSG8w1PByu58";
	public static String BDPush_AndroidSecretKey = "Fpjgw9IxFMEbgNT8BPwPRky48lXMnvSV";
	
//	public static String BDPush_IOSApiKey = "b3NFnZju9uc6KCUpz31yyAMj";//正式 gGFoC2IV8c3WZCM8Gd7QWdkH 测试 b3NFnZju9uc6KCUpz31yyAMj
//	public static String BDPush_IOSSecretKey = "fkIcDXBrWbLcxVcSnxWwg48Sz2k30kIs";//正式 lZGnukIIHu7mII4vwWiStLnM4orT4MZs 测试 fkIcDXBrWbLcxVcSnxWwg48Sz2k30kIs
	public static int BDPush_IOSAppIDKey = 10133354;
	public static String BDPush_IOSApiKey = "fsogGKRwII1WthswAc2Z1FKx";
	public static String BDPush_IOSSecretKey = "Ul0szIlFtzECxDWRss4PHawqfGPEn6Nu";
	
	public static int IOSPublicStatus = 1;//  iOS 证书状态 // 1: 开发环境,测试环境 // 2: 生成环境,正式环境.

	/**
	 * 阿里百川需要的信息
	 */
	public static String BCALI_APIKey = "23387502";
	public static String BCALI_SecretKey = "c92174216bd1a310b87d60a738e7eb4a";
	public static String BCALI_Url="http://gw.api.taobao.com/router/rest";

	/**
	 * ping ++
	 */
	public static String PingPay_App_ID="app_irHqrT4yDqbTPi1y";
	public static String PingPay_SecretKey = "sk_live_Der5a5ePGujHbzPWPSXLKiT8";//"sk_test_DGGGWD9mj54GOuHuv9zHyHG4";
	public static String PingPay_PrivateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
			"MIICXAIBAAKBgQCs6Gx2vfTAlucRj25wxkZcpSDH8SVS7pfcFf6BNjhRP4fIzYeS\n" +
			"qk5vi0yO7kB82kReFsuCbg0qS76f47UdzDboLX1fhnT6NnJxUr8XeDObKAMCrYZV\n" +
			"njFRW4Vrir4WDqQkLGYcKvhWlOny4kcn8zD4sJk+Yy7/j/dE2hYjMwE1JwIDAQAB\n" +
			"AoGAekqo7BLN2KjiJPldE0Yo3b2swb08uLsnjPHBX4IJsu1MDCnRiVoKv7m2HCFG\n" +
			"WwfE8wcMSOOtTAzmdg+HOdwFuGbGxVPRHJA8YE7KYpALTG62UFApKekFZkcjtQGZ\n" +
			"sGKQI3xTMKD8CmSMY9tYAeidhQLlJfY1ye7D7r7K5otelIECQQDfzGHqRWc+d57E\n" +
			"HifO2JXMNxbzrAtl/7KxWfdae5V0rcSUs+odd3xGKun369/e9HG5oabZuud1YTdq\n" +
			"NOV/GErLAkEAxcl9a0h912lHUSeDFsQb7OFmQXGyIJqD/zZYJZMVFEKDOV8PCUe0\n" +
			"ZRDRBk2wLBlIIh9li/OE29tvPpsi5JJnlQJAUXikBJbzeOgvIhIgR8rM7aT4dAij\n" +
			"taQaNzobNBNdWzaPI9tDVSrUUO76cciqnQ28AOn6RbUm4bJcbAuJqHT9dQJAcrru\n" +
			"hc0HMm1fcTP6VrpreH1HU68gJMl8yA4+E42+LIJonF8H2do+SmH23jEygGIp9aFA\n" +
			"xm38I4ymeyqCkxRaAQJBAJ2vUoxznukqrXAOQ/OlIGcV1QL+MSpZAu5kfctw8SZ4\n" +
			"Zmpx5pu/n96rs+NoKPRchdctW5seKedEW3iiAs1pMjM=\n" +
			"-----END RSA PRIVATE KEY-----";

	/**
	 * AliPay 所需要的信息
	 */
	public static String AliPay_NotifyUrl="http://115.29.185.211/WuTuoBang/Order/Interface/V1/AliPayWebhooks";
	public static  String AliPay_PayAccount="13396810625";
	public static String  AliPay_PayAccountName="阙廷康";

	/**
	 * 阿里大于短信验证码
	 */
	public static String VerifyCodeAppID="78155019";//开发 23270090  正式 234647150
	public static String VerifyCodeSecretKey="CozuTYt0d2NSX9LICTJ8iaQnmhg6g7";//开发 c23a2a3742b8f6d851b127d33ce40c37  正式 1e227989f34009104554afe0417f2d49
	public static String VerifyCodeSignName="阅读银行";
	public static String VerifyCodeProdName="验证码${code},您正在登录，若非本人操作，请勿泄露。";//我帮你托
	public static String VerifyCodeTemplate="SMS_78705068";//开发 SMS_2455093   正式SMS_11905040

//	public static String VerifyCodeAppID="23464715";//开发 23270090  正式 234647150
//	public static String VerifyCodeSecretKey="1e227989f34009104554afe0417f2d49";//开发 c23a2a3742b8f6d851b127d33ce40c37  正式 1e227989f34009104554afe0417f2d49
//	public static String VerifyCodeSignName="注册验证";
//	public static String VerifyCodeProdName="我帮你托";//我帮你托
//	public static String VerifyCodeTemplate="SMS_11905040";//开发 SMS_2455093   正式SMS_11905040

}
