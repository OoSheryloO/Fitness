//package wtb.smUtil;
//import com.taobao.api.ApiException;
//import com.taobao.api.DefaultTaobaoClient;
//import com.taobao.api.TaobaoClient;
//import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
//import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.List;
//import java.util.Random;
//import java.util.regex.Pattern;
//
//import javax.servlet.ServletContext;
//import javax.xml.parsers.ParserConfigurationException;
//
//import org.xml.sax.SAXException;
//
///**
// * 
// */
//public class VerifyCode {
//    private static VerifyCode instance;   
//    private static String APPKey="23723181";
//    private static String AppSecret="6de0194a5a23041009bb1b8ee19fc870";
//    private static String FreeSignName= "微新闻社";
//
//    public static VerifyCode sharedInstance() {
//        if (instance == null) {
//            instance = new VerifyCode();
//        }
//
//        return instance;
//    }
//
//    /**
//     * 校验证手机号码格式是否正确
//     *
//     * @param mobile 手机号码
//     * @return 返回是否是正确的手机号码
//     */
//    public boolean checkNumberVerify(String mobile) {
//        try {
//            if(mobile.length()==11) {
//                Long.parseLong(mobile);
//                return true;
//            }else{
//                return false;
//            }
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    /**
//     * 随机生成验证码的位数
//     *
//     * @param count 要生成验证码的位数
//     * @return 验证码字符串
//     */
//    public String createCheckNumber(int count) {
//        StringBuffer buffer = new StringBuffer();
//        String string = "0123456789";
//        Random random = new Random();
//
//        for (int i = 0; i < count; i++) {
//            int number = random.nextInt(string.length());
//            buffer.append(string.charAt(number));
//            string = string.replace((string.charAt(number) + ""), "");//  去除重复的数值
//        }
//
//        return buffer.toString();
//    }
//
//    /**
//     * 发送短信验证码
//     *
//     * @param mobile      手机号
//     * @param checkNumber 验证码
//     * @return 发送成功 true, 发送失败 false
//     * @throws ParserConfigurationException 
//     * @throws IOException 
//     * @throws SAXException 
//     * @throws FileNotFoundException 
//     */
//    public boolean sendCheckNumber(String mobile, String checkNumber,ServletContext arg0) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException {
//        TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23385529", "5e5bcebc6f91610aa4e967d2b2f43046");// 接口, AppKey 应用的唯一标识, 应用的签名密钥
//        AlibabaAliqinFcSmsNumSendRequest request = new AlibabaAliqinFcSmsNumSendRequest();
//        request.setExtend(mobile);//公共回传参数:在消息返回中会透传回该参数,这样就可以知道哪些用户使用了短信验证码
//        request.setSmsType("normal");// 短信类型:传入类型值请填写 normal
//        request.setSmsFreeSignName("杭州校播科技");//短信签名:传入的签名必须在在阿里大鱼"管理中心 -> 短信签名管理" 中可用的签名作为短信签名例如:【阿里大鱼】欢迎使用阿里大鱼服务。
//        request.setSmsParam("{\"name\":\"" + checkNumber + "\"}");//短信模板变量，传参规则{"key":"value"}，key的名字须和申请模板中的变量名一致，多个变量之间以逗号隔开。示例：针对模板“验证码${code}，您正在进行${product}身份验证，打死不要告诉别人哦！”，传参时需传入{"code":"1234","product":"alidayu"}
//        request.setRecNum(mobile);//短信接收号码。支持单个或多个手机号码，传入号码为11位手机号码，不能加0或+86。群发短信需传入多个号码，以英文逗号分隔，一次调用最多传入200个号码。示例：18600000000,13911111111,13322222222
//        request.setSmsTemplateCode("SMS_53045189");//短信模板编号，传入的模板必须是在阿里大鱼"管理中心 -> 短信模板管理"中的可用模板。示例：SMS_60985095
//          return SendMessage(request,client,arg0);
//    }
//    
//    
//    /**
//     * 发送错误报警
//     *
//     * @param mobile      手机号
//     * @param Email       邮箱
//     * @return 发送成功 true, 发送失败 false
//     * @throws ParserConfigurationException 
//     * @throws IOException 
//     * @throws SAXException 
//     * @throws FileNotFoundException 
//     */
//    public boolean sendErrorNumber(String mobile, String Email,ServletContext arg0) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException {
//        TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", APPKey,AppSecret);// 接口, AppKey 应用的唯一标识, 应用的签名密钥
//        AlibabaAliqinFcSmsNumSendRequest request = new AlibabaAliqinFcSmsNumSendRequest();
//        request.setExtend(mobile);//公共回传参数:在消息返回中会透传回该参数,这样就可以知道哪些用户使用了短信验证码
//        request.setSmsType("normal");// 短信类型:传入类型值请填写 normal
//        request.setSmsFreeSignName("微新闻社");//短信签名:传入的签名必须在在阿里大鱼"管理中心 -> 短信签名管理" 中可用的签名作为短信签名例如:【阿里大鱼】欢迎使用阿里大鱼服务。
//        request.setSmsParam("{\"account\":\"" + Email + "\"}");//短信模板变量，传参规则{"key":"value"}，key的名字须和申请模板中的变量名一致，多个变量之间以逗号隔开。示例：针对模板“验证码${code}，您正在进行${product}身份验证，打死不要告诉别人哦！”，传参时需传入{"code":"1234","product":"alidayu"}
//        request.setRecNum(mobile);//短信接收号码。支持单个或多个手机号码，传入号码为11位手机号码，不能加0或+86。群发短信需传入多个号码，以英文逗号分隔，一次调用最多传入200个号码。示例：18600000000,13911111111,13322222222
//        request.setSmsTemplateCode("SMS_71040400");//短信模板编号，传入的模板必须是在阿里大鱼"管理中心 -> 短信模板管理"中的可用模板。示例：SMS_60985095
//          return SendMessage(request,client,arg0);
//    }
//
//    public static long CreateNewID(){
//        return new IdWorker(1, new Random().nextInt(31)).nextId();
//    }
//    /**
//     * 发送短信
//     * @param request
//     * @param client
//     * @param arg0
//     * @return
//     * @throws ParserConfigurationException 
//     * @throws IOException 
//     * @throws SAXException 
//     * @throws FileNotFoundException 
//     */
//    private boolean  SendMessage(AlibabaAliqinFcSmsNumSendRequest request,TaobaoClient client,ServletContext arg0) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException {
//    	if(arg0==null){
//    		return false;
//    	}
//    	List<String> configList=SmBaseUtil.initServersList(arg0);
//		if(configList.size()>=4 && configList.get(3)!=null && configList.get(3).equals("true")){
//			AlibabaAliqinFcSmsNumSendResponse response;
//	    	 try {
//	             response = client.execute(request);
//	             System.out.println(response.getBody());
//	             if(response!=null&& response.getResult()!=null){
//	             	return response.getResult().getSuccess();
//	             }else{
//	             	return false;
//	             }
//	         } catch (ApiException e) {
//	             e.printStackTrace();
//	             return false;
//	         }
//		}else{
//			return true;
//		}
//    	
//	}
//}
