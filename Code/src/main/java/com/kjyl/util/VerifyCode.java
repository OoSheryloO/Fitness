package com.kjyl.util;

import java.util.Random;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.kjyl.pojo.Errorlog;
import com.kjyl.service.ErrorlogService;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

/**
 * Created by gejaingbo on 17/5/12.
 */
public class VerifyCode {
    private static VerifyCode instance;

    public static VerifyCode sharedInstance() {
        if (instance == null) {
            instance = new VerifyCode();
        }

        return instance;
    }

    /**
     * @param mobile 手机号码
     * @return 返回是否是正确的手机号码
     */
    public boolean checkNumberVerify(String mobile) {
        try {
            if (mobile.length() == 11) {
                Long.parseLong(mobile);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 随机生成验证码的位数
     *
     * @param count 要生成验证码的位数
     * @return 验证码字符串
     */
    public String createCheckNumber(int count) {
        StringBuffer buffer = new StringBuffer();
        String string = "123456789";
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            int number = random.nextInt(string.length());
            buffer.append(string.charAt(number));
            string = string.replace((string.charAt(number) + ""), "");// 去除重复的数值
        }

        return buffer.toString();
    }

    /**
     * 发送短信验证码
     *
     * @param mobile 手机号
     * @param checkNumber 验证码
     * @return 发送成功 true, 发送失败 false
     */
    // 产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    // 产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAItihMSmlTuWst";
    static final String accessKeySecret = "CozuTYt0d2NSX9LICTJ8iaQnmhg6g7";

    public boolean sendCheckNumber(String mobile, String checkNumber,
                                   ErrorlogService errorLogService) {
        String errormessage = null;
        try {
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                    accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);

            // 组装请求对象-具体描述见控制台-文档部分内容
            SendSmsRequest request = new SendSmsRequest();
            // 必填:待发送手机号
            request.setPhoneNumbers(mobile);
            // 必填:短信签名-可在短信控制台中找到
            request.setSignName("阅读银行");
            // 必填:短信模板-可在短信控制台中找到
            request.setTemplateCode("SMS_78705068");
            // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            request.setTemplateParam("{\"code\":\"" + checkNumber + "\"}");

            // 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
            // request.setSmsUpExtendCode("90997");

            // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            //request.setOutId("123456");

            // hint 此处可能会抛出异常，注意catch
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                return true;
            }
            errormessage = sendSmsResponse.getCode();
        } catch (ClientException e) {
            // TODO: handle exception
            Errorlog pjlog = new Errorlog();
            pjlog.setNote(e.getMessage() + ":" + errormessage);
            pjlog.setDevice("短信接口");
            pjlog.setTypeId(CodeInfo.DeviceType.Server.getCode());
            if (errorLogService != null) {
                errorLogService.Insert(pjlog);
                return false;
            }
        }
        return false;
    }


//    	
//		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");// 设置超时时间-可自行调整
//		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
//
//		final String product = "Dysmsapi";// 短信API产品名称
//		final String domain = "dysmsapi.aliyuncs.com";// 短信API产品域名
//
//		final String accessKeyId = "LTAItihMSmlTuWst";// accessKeyId
//		final String accessKeySecret = "CozuTYt0d2NSX9LICTJ8iaQnmhg6g7";//accessKeySecret
//
//		try {
//		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
//		accessKeySecret);
//		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
//		IAcsClient acsClient = new DefaultAcsClient(profile);
//
//		// 组装请求对象
//		SendSmsRequest request = new SendSmsRequest();
//		// 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为20个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
//		request.setPhoneNumbers("\""+mobile+"\"");
//		// 必填:短信签名-可在短信控制台中找到
//		request.setSignName("阅读银行");
//		// 必填:短信模板-可在短信控制台中找到
//		request.setTemplateCode("SMS_78705068");
//		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
//		request.setTemplateParam("{\"code\":\""+checkNumber+"\"}");
//		// 可选-上行短信扩展码(无特殊需求用户请忽略此字段)
//		// request.setSmsUpExtendCode("90997");
//		// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
////		request.setOutId("yourOutId");
//		// 请求失败这里会抛ClientException异常
//		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
//		if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {System
//		.out.println("进入判断");
//			return true;
//		}

//		 response = client.execute(request);
//		    System.out.println(response.getBody());
//		          body=response.getBody();
//		          return response.getResult().getSuccess();
//    	} catch (ClientException e) {
//    		e.printStackTrace();
//    		System.out.println("发送失败！！！！！！！！！！！！！");
    // TODO: handle exception
    //{"error_response":{"code":15,"msg":"Remote service error","sub_code":"isv
    // .AMOUNT_NOT_ENOUGH","sub_msg":"余额不足","request_id":"3gsm4zp7qkjm"}}
//          e.printStackTrace();
//          Errorlogs errlog=new Errorlogs();
//          errlog.setErrorlogNote(e.getMessage()+":"+body);
//          errlog.setErrorlogDevice("短信接口");
//          errlog.setErrorlogTypeid((long) BaseUtil.DeviceType.Server.getCode());
//          if(errorLogService!=null) {
//              errorLogService.addErrorLog(errlog);
//    		return false;
//		}
//		return true;
//    }


//       TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest",
//       ConstantUtil.VerifyCodeAppID, ConstantUtil.VerifyCodeSecretKey);// 接口, AppKey 应用的唯一标识,
//       应用的签名密钥
//    	//TaobaoClient client = new DefaultTaobaoClient("https://help.aliyun
//    	.com/document_detail/55284.html?spm=5176.sms-account.109.1.66e3621I1ZESp", ConstantUtil
//    	.VerifyCodeAppID, ConstantUtil.VerifyCodeSecretKey);// 接口, AppKey 应用的唯一标识, 应用的签名密钥
//        AlibabaAliqinFcSmsNumSendRequest request = new AlibabaAliqinFcSmsNumSendRequest();
//        request.setExtend(mobile);//公共回传参数:在消息返回中会透传回该参数,这样就可以知道哪些用户使用了短信验证码
//        request.setSmsType("normal");// 短信类型:传入类型值请填写 normal
//        request.setSmsFreeSignName(ConstantUtil.VerifyCodeSignName);//我帮你托
//        短信签名:传入的签名必须在在阿里大鱼"管理中心 -> 短信签名管理" 中可用的签名作为短信签名例如:【阿里大鱼】欢迎使用阿里大鱼服务。
//        request.setSmsParam("{\"code\":\"" + checkNumber + "\",\"product\":\""+ ConstantUtil
//        .VerifyCodeProdName +"\"}");
// 短信模板变量，传参规则{"key":"value"}，key的名字须和申请模板中的变量名一致，多个变量之间以逗号隔开。示例：针对模板“验证码${code}，您正在进行${product
// }身份验证，打死不要告诉别人哦！”，传参时需传入{"code":"1234","product":"alidayu"}
//        //request.setSmsParam("{\"code\":\"" + checkNumber + "\"}");//正式环境传值
//        request.setRecNum(mobile);
// 短信接收号码。支持单个或多个手机号码，传入号码为11位手机号码，不能加0或+86。群发短信需传入多个号码，以英文逗号分隔，一次调用最多传入200个号码。示例：18600000000,
// 13911111111,13322222222
//        request.setSmsTemplateCode(ConstantUtil.VerifyCodeTemplate);//短信模板编号，传入的模板必须是在阿里大鱼"管理中心
//        -> 短信模板管理"中的可用模板。示例：SMS_585014
//
//        AlibabaAliqinFcSmsNumSendResponse response;
//        String  body=null;
//        try {
//            //{"error_response":{"code":15,"msg":"Remote service error","sub_code":"isv
//            .AMOUNT_NOT_ENOUGH","sub_msg":"余额不足","request_id":"3gsm4zp7qkjm"}}
//            response = client.execute(request);
//      System.out.println(response.getBody());
//            body=response.getBody();
//            return response.getResult().getSuccess();
//        } catch (Exception e) {
//            e.printStackTrace();
//            Errorlogs errlog=new Errorlogs();
//            errlog.setErrorlogNote(e.getMessage()+":"+body);
//            errlog.setErrorlogDevice("短信接口");
//            errlog.setErrorlogTypeid((long) BaseUtil.DeviceType.Server.getCode());
//            if(errorLogService!=null) {
//                errorLogService.addErrorLog(errlog);
//            }
//            return true;
//        }

}
