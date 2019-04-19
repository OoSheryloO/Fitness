package com.kjyl.util.util;
//package com.huban.util;
//
//import java.io.IOException;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Set;
//import java.util.SortedMap;
//
//import org.jdom.JDOMException;
//
//public class PayCommonUtil {
//    //定义签名，微信根据参数字段的ASCII码值进行排序 加密签名,故使用SortMap进行参数排序
//    public static String createSign(String characterEncoding,SortedMap<String,String> parameters){
//        StringBuffer sb = new StringBuffer();
//        Set es = parameters.entrySet();
//        Iterator it = es.iterator();
//        while(it.hasNext()) {
//            Map.Entry entry = (Map.Entry)it.next();
//            String k = (String)entry.getKey();
//            Object v = entry.getValue();
//            if(null != v && !"".equals(v)
//                    && !"sign".equals(k) && !"key".equals(k)) {
//                sb.append(k + "=" + v + "&");
//            }
//        }
//        sb.append("key=" + ConstantUtil.PARTNER_KEY);//最后加密时添加商户密钥，由于key值放在最后，所以不用添加到SortMap里面去，单独处理，编码方式采用UTF-8
//        String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
//        return sign;
//    }
// 
////将封装好的参数转换成Xml格式类型的字符串
//
//    public static String getRequestXml(SortedMap<String,String> parameters){
//        StringBuffer sb = new StringBuffer();
//        sb.append("<xml>");
//        Set es = parameters.entrySet();
//        Iterator it = es.iterator();
//        while(it.hasNext()) {
//            Map.Entry entry = (Map.Entry)it.next();
//            String k = (String)entry.getKey();
//            String v = (String)entry.getValue();
//            if("sign".equalsIgnoreCase(k)){
//
//            }
//            else if ("attach".equalsIgnoreCase(k)||"body".equalsIgnoreCase(k)) {
//                sb.append("<"+k+">"+"<![CDATA["+v+"]]></"+k+">");
//            }
//            else {
//                sb.append("<"+k+">"+v+"</"+k+">");
//            }
//        }
//        sb.append("<"+"sign"+">"+"<![CDATA["+parameters.get("sign")+"]]></"+"sign"+">");
//        sb.append("</xml>");
//        return sb.toString();
//    }
//}
// 
////微信Md5加密工具
//public class MD5Util {
//
//   private static String byteArrayToHexString(byte b[]) {
//      StringBuffer resultSb = new StringBuffer();
//      for (int i = 0; i < b.length; i++)
//         resultSb.append(byteToHexString(b[i]));
//
//      return resultSb.toString();
//   }
//
//   private static String byteToHexString(byte b) {
//      int n = b;
//      if (n < 0)
//         n += 256;
//      int d1 = n / 16;
//      int d2 = n % 16;
//      return hexDigits[d1] + hexDigits[d2];
//   }
//
//   public static String MD5Encode(String origin, String charsetname) {
//      String resultString = null;
//      try {
//         resultString = new String(origin);
//         MessageDigest md = MessageDigest.getInstance("MD5");
//         if (charsetname == null || "".equals(charsetname))
//            resultString = byteArrayToHexString(md.digest(resultString
//                  .getBytes()));
//         else
//            resultString = byteArrayToHexString(md.digest(resultString
//                  .getBytes(charsetname)));
//      } catch (Exception exception) {
//      }
//      return resultString;
//   }
//
//   private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
//         "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
//
//}
// 
////微信返回的结果为Xml格式的字符串，XmlUtil主要用于解析结果
//public class XMLUtil {
//
//   /**
//    * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
//    * @param strxml
//    * @return
//    * @throws JDOMException
//    * @throws IOException
//    */
//   public static Map doXMLParse(String strxml) throws JDOMException, IOException {
//      strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");
//
//      if(null == strxml || "".equals(strxml)) {
//         return null;
//      }
//
//      Map m = new HashMap();
//
//      InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
//      SAXBuilder builder = new SAXBuilder();
//      Document doc = builder.build(in);
//      Element root = doc.getRootElement();
//      List list = root.getChildren();
//      Iterator it = list.iterator();
//      while(it.hasNext()) {
//         Element e = (Element) it.next();
//         String k = e.getName();
//         String v = "";
//         List children = e.getChildren();
//         if(children.isEmpty()) {
//            v = e.getTextNormalize();
//         } else {
//            v = XMLUtil.getChildrenText(children);
//         }
//
//         m.put(k, v);
//      }
//
//      //关闭流
//      in.close();
//
//      return m;
//   }
//
//   /**
//    * 获取子结点的xml
//    * @param children
//    * @return String
//    */
//   public static String getChildrenText(List children) {
//      StringBuffer sb = new StringBuffer();
//      if(!children.isEmpty()) {
//         Iterator it = children.iterator();
//         while(it.hasNext()) {
//            Element e = (Element) it.next();
//            String name = e.getName();
//            String value = e.getTextNormalize();
//            List list = e.getChildren();
//            sb.append("<" + name + ">");
//            if(!list.isEmpty()) {
//               sb.append(XMLUtil.getChildrenText(list));
//            }
//            sb.append(value);
//            sb.append("</" + name + ">");
//         }
//      }
//
//      return sb.toString();
//   }
//
//   /**
//    * 获取xml编码字符集
//    * @param strxml
//    * @return
//    * @throws IOException
//    * @throws JDOMException
//    */
//   public static String getXMLEncoding(String strxml) throws JDOMException, IOException {
//      InputStream in = HttpClientUtil.String2Inputstream(strxml);
//      SAXBuilder builder = new SAXBuilder();
//      Document doc = builder.build(in);
//      in.close();
//      return (String)doc.getProperty("encoding");
//   }
//}
// 
////发送https
//public class CommonUtil {
//    private static Logger log = LoggerFactory.getLogger(CommonUtil.class);
//    /**
//     * 发送https请求
//     * @param requestUrl 请求地址
//     * @param requestMethod 请求方式（GET、POST）
//     * @param outputStr 提交的数据
//     * @return 返回微信服务器响应的信息
//     */
//    public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
//        try {
//            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
//            MyX509TrustManager[] tm = { new MyX509TrustManager() };
//            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
//            sslContext.init(null, tm, new java.security.SecureRandom());
//            // 从上述SSLContext对象中得到SSLSocketFactory对象
//            SSLSocketFactory ssf = sslContext.getSocketFactory();
//            URL url = new URL(requestUrl);
//            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
//            conn.setSSLSocketFactory(ssf);
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            conn.setUseCaches(false);
//            // 设置请求方式（GET/POST）
//            conn.setRequestMethod(requestMethod);
//            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
//            // 当outputStr不为null时向输出流写数据
//            if (null != outputStr) {
//                OutputStream outputStream = conn.getOutputStream();
//                // 注意编码格式
//                outputStream.write(outputStr.getBytes("UTF-8"));
//                outputStream.close();
//            }
//            // 从输入流读取返回内容
//            InputStream inputStream = conn.getInputStream();
//            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
//            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//            String str = null;
//            StringBuffer buffer = new StringBuffer();
//            while ((str = bufferedReader.readLine()) != null) {
//                buffer.append(str);
//            }
//            // 释放资源
//            bufferedReader.close();
//            inputStreamReader.close();
//            inputStream.close();
//            inputStream = null;
//            conn.disconnect();
//            return buffer.toString();
//        } catch (ConnectException ce) {
//            log.error("连接超时：{}", ce);
//        } catch (Exception e) {
//            log.error("https请求异常：{}", e);
//        }
//        return null;
//    }
//
//    /**
//     * 获取接口访问凭证
//     *
//     * @param appid 凭证
//     * @param appsecret 密钥
//     * @return
//     *//*
//    public static Token getToken(String appid, String appsecret) {
//        Token token = null;
//        String requestUrl = ConfigUtil.TOKEN_URL.replace("APPID", appid).replace("APPSECRET", appsecret);
//        // 发起GET请求获取凭证
//        JSONObject jsonObject = JSONObject.fromObject(httpsRequest(requestUrl, "GET", null));
//
//        if (null != jsonObject) {
//            try {
//                token = new Token();
//                token.setAccessToken(jsonObject.getString("access_token"));
//                token.setExpiresIn(jsonObject.getInt("expires_in"));
//            } catch (JSONException e) {
//                token = null;
//                // 获取token失败
//                log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
//            }
//        }
//        return token;
//    }
//    public static String urlEncodeUTF8(String source){
//        String result = source;
//        try {
//            result = java.net.URLEncoder.encode(source,"utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }*/
//}
// 
//重点：封装参数调用统一下单接口，生成prepay_id(预支付订单Id)
///**
// * 微信支付
// * @param orderId 订单编号
// * @param actualPay 实际支付金额
// * @return 
// */
//private String generateOrderInfoByWeiXinPay(String orderId, float actualPay,HttpServletRequest request,HttpServletResponse response) throws Exception{
//   String notify_url = propertiesService.WEI_XIN_NOTIFY_URL;//回调地址
//   String uuid = IdGen.uuid();
//   System.out.print("uuid" + uuid);
//   SortedMap<String, String> signParams = new TreeMap<String, String>();
//   signParams.put("appid", ConstantUtil.APP_ID);//app_id
//   signParams.put("body","测试");//商品参数信息
//   signParams.put("mch_id", ConstantUtil.PARTNER);//微信商户账号
//   signParams.put("nonce_str", uuid);//32位不重复的编号
//   signParams.put("notify_url", notify_url);//回调页面
//   signParams.put("out_trade_no", orderId);//订单编号
//   signParams.put("spbill_create_ip",request.getRemoteAddr() );//请求的实际ip地址
//   signParams.put("total_fee","1");//支付金额 单位为分
//   signParams.put("trade_type", "APP");付款类型为APP
//   String sign = PayCommonUtil.createSign("UTF-8", signParams);//生成签名
//   signParams.put("sign", sign);
//   signParams.remove("key");//调用统一下单无需key（商户应用密钥）
//   String requestXml = PayCommonUtil.getRequestXml(signParams);//生成Xml格式的字符串
//   String result = CommonUtil.httpsRequest   (ConstantUtil.UNIFIED_ORDER_URL, "POST", requestXml);//以post请求的方式调用统一下单接口
//（注：ConstantUtil.UNIFIED_ORDER_URL=https://api.mch.weixin.qq.com/pay/unifiedorder;
//）
// 
//返回的result成功结果取出prepay_id：
//Map map = XMLUtil.doXMLParse(result);
//String return_code=(String) map.get("return_code");
//String prepay_id =null;
//String returnSign=null;
//String returnNonce_str=null;
//if (return_code.contains("SUCCESS")){
//   prepay_id=(String) map.get("prepay_id");//获取到prepay_id
//}
//StringBuffer weiXinVo=new StringBuffer();
//long currentTimeMillis = System.currentTimeMillis();//生成时间戳
//long second = currentTimeMillis / 1000L;（转换成秒）
//String seconds = String.valueOf(second).substring(0, 10);（截取前10位）
//SortedMap<String, String> signParam = new TreeMap<String, String>();
//signParam.put("appid", ConstantUtil.APP_ID);//app_id
//signParam.put("partnerid", ConstantUtil.PARTNER);//微信商户账号
//signParam.put("prepayid", prepay_id);//预付订单id
//signParam.put("package", "Sign=WXPay");//默认sign=WXPay
//signParam.put("noncestr", uuid);//自定义不重复的长度不长于32位
//signParam.put("timestamp",seconds);//北京时间时间戳
//String signAgain = PayCommonUtil.createSign("UTF-8", signParam);//再次生成签名
//signParams.put("sign", signAgain);
//weiXinVo.append("appid=").append(ConstantUtil.APP_ID).append("&partnerid=").append(ConstantUtil.PARTNER).append("&prepayid=").append(prepay_id).append("&package=Sign=WXPay").append("&noncestr=").append(uuid).append("&timestamp=").append(seconds).append("&sign=").append(signAgain);//拼接参数返回给移动端
//return weiXinVo.toString();
