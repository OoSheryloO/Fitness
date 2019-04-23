package com.kjyl.config.Ali;

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
	public static String PARTNER = "1532253961";// 财付通商户号
	public static String PARTNER_KEY = "woshichenliangfeng33032619961129";// 商户号对应的密钥
	
	//商户号：1486349882
	//秘钥：BeiJingKuaiChengKeJiReadBank2017
//	public static String PARTNER = "1367071302";// 财付通商户号 1486349882
//	public static String PARTNER_KEY = "WuTuoBangAppKeyQueTingKangMobile";// 商户号对应的密钥
	
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
	public static String AliPay_PayAccount="13396810625";
	public static String AliPay_PayAccountName="阙廷康";
	
	/** * 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。*/
	public static String Alipay_Public_Key ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAg5R/mHiN9Ku4qzxHXSBFeP8Jdd+bpaQTyruSvkLCp9Lf7nTZ3NoFuTWLe9xtBv1pEtgc3+bHh+/JsP71U9s8z5+efSqNRVeVKMlsFrFKf43Bjf/nYsZztfhmrEKOsdUT1PgDamtpSHfOfw/f2boZAO2cycTaJ/UVr97C84MmdFDN1EkUYRh1aQN7FhZvWvhlyfrXnwBKzzxbXFhXWx5eu5pUWELCfMKXKi3mVuw7MwyUxlX6pVGjInCdr1kkiEPrL2uZT9FfaPfd/3508HUT/HsGfGriTUmXs4An16PwMk9vwGZz6y3bJ1GgmiVuD1mdI8KHWdsKwQXfzObzNzF3/wIDAQAB";
	/** * 支付宝应用私钥 商户私钥，您的PKCS8格式RSA2私钥*/
	public static String Alipay_Private_Key ="MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCeAzrXBjBcgjDO2/lGFP59IYStHz2QLZzH41Y00Yfgvbf8/6gMKaSIOigGiQJlkmJ3ypQhjVFJK9PLdquOr5qCnQFdOEfOpmsxaREh/+TKLlL4J6WfvvtFaGdFaF0mJZ/A6lqiE+thfPHQSc81LSuFhRHUHwwFHlIE9pXomlmidMeD2tZQQTSoXQ88GJdGFIw2egli+NS2x2EzewiC+aV/2jtoBwocR0YQOgjAmV3cufoCdOQDN/xcYM/NP/YeWwi3UcUXMhoMNFd833QIN3cKW9aZq+bJWnfokIha/lBINiuNeGS5mc13QQzSKSRoetv05Y2ZeFYBOaChYm4Uos6rAgMBAAECggEBAIUIwRxSKJtqPT+cMnYPrOebNcIwIDvoOyyLuYPEuXq9IE/QyyJaWR0coaBG8A6mPWMP2sGY4kwRq0KNSglazWKUX5alkMdqk6tJA1S1xmF2uQIXFzm0qIcaTIBCStKYMLVSWjZFQRKWCF1OFzd1LUQ/8e++p/cTPQjStwncEgVUqxNT4t9K3f+gBxlE5HIgq92I7Z5cMpTpd9vEl3DYnFuBZKD5ze/EBJ9Ph04n9sO1c4Es9np5Q7ogPX1mHLDvqn5rizByMR929qwXVye6RRChJdWUuwTtywkFp25fEP8WQ91vm89TlknOGqD+NveX1tLKIB/f3nwkasCER1pYVqECgYEA2Qo/1g1TCdB0JDkl/uut29gPXLCtKFHc8LtWVJ4PTpJSmtlpuXhHATEYaQqExTv8pgMWIE8yG6Zo0W8iv268whiechBNo28rP9ECNFzvINyR7EhI5BO7BvsZY7t0S95NE5Yq6gVbeAJGjwBKfmbTMgFSVLoXDqLJ66mreNzuI/sCgYEAumB2rTKWEHqUSJA1w59AoVXj2fDOVHH2TvvvJYx+TiFvL2BqdemjIbFJX4v+Rn+7ewXnvJyix4GxwGvSRi6d5LbhyYgxLA1A40kDN9nbkQTX2I3Y++GsFGCfQBuwMeZYAPpmU/ROMZ02Hie3nGYSLZ7s8YPrV78mSkZ+xKNQURECgYEAq8nqByIaxNzi4Lz/LwwaW7VjS2loVU6eg0i0p1tIIFe+3GQTw8LI6M+UU6gIuRim/DvbKo2kyq/nrUW7Ciy7aSIVX5E58o+Oev+TMCETAT60nYIFd8mKMpdTXFZGUbrN5R/qLbs5z/51HMENNYlrxtu27+svTt1Y15g/afXs5DsCgYA60EADDciPMK9kaqWcsr+Jafo6BySFP2UzrYrc9YXSD/jvIWTXCsBg3HpD/YBjLOGzw7xKJ7+yZ3n5N80mOoW27AM6PpENzqzgTGUIleYU0Etw2epUKfXMhGUcrOAgDwlMXbg23Axk2bB/OZdWcltgLBunZzK4AOkLXU2ZrdOlkQKBgQCEdkFNuY4vZJRhxq6W31+cMUdHqTtWbk5DU5K6rNMc5es9TpRe6q/OjL/RFJTpzNo+c4njP/oIOUhy6S97pQLc8GjpHDCQn0SRHvOUXfL718/eoi5XPRzUQZ5ZjTnD4q5cOrPPTU2pyH3yWwGwKRI7ylbNRy8zxmr8jPWFs/ND/Q==";
	/** * 支付宝APPID 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号 */
	public static String Alipay_Appid_Key ="2019042064010061";
	/** * 支付宝网关 */
	public static String Alipay_Webway_Key ="https://openapi.alipay.com/gateway.do";
	/** * 签名方式 */
	public static String Alipay_Sign_Type_Key = "RSA2";
	/** * 支付宝编码 */
	public static String Alipay_Charset_Key ="utf-8";//UTF-8
	/** * 支付宝异步回调地址 */
	public static String Alipay_Notify_URL_Key ="http://47.104.141.243:80/Fitness/Pay/AliPayBack";
	/** * 支付宝回调地址 */
	public static String Alipay_Return_URL_Key ="http://47.104.141.243:80/Fitness/Pay/AliPayBack1";
	
	/** * 支付宝沙箱 */
	/** * 支付宝沙箱公钥 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。*/
	public static String Alipay_SandBox_Public_Key ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA42GRpiPVQ5IsweYpNhZxe0SmQTCByYS50p43NTQwoQS59P/xYhpYqU+ecMmGvG2J3v9Lec4pQZ/QpEos74OlhkBQrULgWm2P38ofhcHgKrMQzpnLeV3MhmTmtXGrN9zl2c6dY6KxANlWkG6q6e9w7rD3eEnKHxvIQYRk0bciOGDZ9crIHX5jQpTXMydH0XYfBdZ9XYsjLgwzne787qowjqo5gNHzp7aI7OhQuWfev6uKuuJdy8IxE/964Y6dvdr5qGAXWLTOjU6qAYMM74K4QTca7eCGc/iSGW1uiBrNdUfQRYUgHEqqCthTcklU0ngM5k4RHbKB+AU97g4AfPQ10QIDAQAB";
	/** * 支付宝沙箱应用私钥 商户私钥，您的PKCS8格式RSA2私钥*/
	public static String Alipay_SandBox_Private_Key ="MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDjYZGmI9VDkizB5ik2FnF7RKZBMIHJhLnSnjc1NDChBLn0//FiGlipT55wyYa8bYne/0t5zilBn9CkSizvg6WGQFCtQuBabY/fyh+FweAqsxDOmct5XcyGZOa1cas33OXZzp1jorEA2VaQbqrp73DusPd4ScofG8hBhGTRtyI4YNn1ysgdfmNClNczJ0fRdh8F1n1diyMuDDOd7vzuqjCOqjmA0fOntojs6FC5Z96/q4q64l3LwjET/3rhjp292vmoYBdYtM6NTqoBgwzvgrhBNxrt4IZz+JIZbW6IGs11R9BFhSAcSqoK2FNySVTSeAzmThEdsoH4BT3uDgB89DXRAgMBAAECggEBAJtypKQ0cyQS1s5oj1wO0941/IMsqk4ekvtOdufEmko6dPbt0xQCdwKpD+SDob1IFa2CUxMewk2++GCY5UeJdKvnZEBPLBomNOoFwK8s8BfA+4iC0/xRB/YJHUCGSUuTlP/mpOrs2p19+BQd1HLB4y8LhOOTbr79u1YvMM2+qYJoSdrU76NPinv+rvutu1WQwNAVaHAPoAH31D7CNP7fGqIYo5VWEmKTIAxs6fUS4DjU7LdTfI/3CLFGTQUaqLetEwJctz99zC/TbdYfs9Zp6RPAajLv2A0cRw/QjNTmNo90qEX2ptnP3o1Ufwtxu3173pL0WWzeJs6iBeNTteVUgcECgYEA/NLbjTkNEnj/BdqTDo0DWMZWIYDZdpwbxAd3Pd9GWcOKT6IUqW0IxGLD/R/G5cKOOJjJzP8a8PpM2uNC7f3TWiqQ+oq6YQQta+34ZLjXMfF7dOs8u5tcyicY/L9+DDV/SBU1enAFGWNUJBOK6jMlAvebIC+xtUzu5EVf+9P5AiUCgYEA5jzhxvJvRW5dJJf5l3rpWx8UhoYv5wY6FdIsk8oMC0MMrs0b0fwdoONitRav6ZYIDoa/qPADMVWPnuZSjjVVtBwml7fZOKPCHCxgV1E3uIO+IOfpGEYVJAGlHol98wSdriLVL1OZ69EG0poArfU9iCUfhgeI4XBp3ButcJlJ9z0CgYEAsLX0zTW2JFY78ex/Yuk/EJq4cvxrgxLD+XU6bB2WP4GEN/rStEVInnNmB5NhYxBkdbydOFKKkypv84JrMot6YYhlAylZ8huIX+rZ9hSYHQlrQCJ3ta124rkHJwn1YTU3PLvOQEDx33fkxL6AFwgec397hrivXkhipVy0JQFZB50CgYEAiEiXeJT+yoF2fve6lTD7OqXeF3cMmdq0vT/ohR6/HFd3BW2Bvz1WKGsQfZLNVB4r7LVUc+KGcUhtOCiry2PEtFVJftsjqIQ+nUCjUmxP6FeLTp0X+/dYAjU7ESVfuV8NGwuhV19MKCtVt4nTRyV4Y8Ndso95NcC92L1FX/ZMsx0CgYBXgoNIH4z5nAt+pC+Tj2yixGe26AgRPAUcg1SZGqtLd6wYqLEdElJUvjhmpBujA6VzqIJCIZeAGQD73OlvCvfufJKoNzurcmIwHb1E6wZPAAkAc7aCBbWeruo53tIbmNo1ipe0hOe7hkwYKdWFVLn8B3kvBLx/RiAX7tdsZihrYw==";
	/** * 支付宝沙箱APPID 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号 */
	public static String Alipay_SandBox_Appid_Key ="2016092500597118";
	/** * 支付宝沙箱网关 */
	public static String Alipay_SandBox_Webway_Key ="https://openapi.alipaydev.com/gateway.do";
	/** * 支付宝沙箱 签名方式 */
	public static String Alipay_SandBox_Sign_Type_Key = "RSA2";
	/** * 支付宝沙箱编码 */
	public static String Alipay_SandBox_Charset_Key ="utf-8";//UTF-8
	/** * 支付宝沙箱异步回调地址 */
	public static String Alipay_SandBox_Notify_URL_Key ="http://47.104.141.243:80/Fitness/Pay/AliPayBackSandBox";
	/** * 支付宝回调地址 */
	public static String Alipay_SandBox_Return_URL_Key ="http://47.104.141.243:80/Fitness/Pay/AliPayBack1";

	/** * 阿里大于短信验证码 */
	public static String VerifyCodeAppID="78155019";//开发 23270090  正式 234647150
	public static String VerifyCodeSecretKey="CozuTYt0d2NSX9LICTJ8iaQnmhg6g7";//开发 c23a2a3742b8f6d851b127d33ce40c37  正式 1e227989f34009104554afe0417f2d49
	public static String VerifyCodeSignName="阅读银行";
	public static String VerifyCodeProdName="验证码${code},您正在登录，若非本人操作，请勿泄露。";//我帮你托
	public static String VerifyCodeTemplate="SMS_78705068";//开发 SMS_2455093   正式SMS_11905040

}
