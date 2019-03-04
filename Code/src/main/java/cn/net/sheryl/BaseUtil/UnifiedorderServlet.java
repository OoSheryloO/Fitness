//package wtb.smUtil;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.io.UnsupportedEncodingException;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.Random;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.activiti.engine.impl.util.json.JSONArray;
//import org.activiti.engine.impl.util.json.JSONML;
//import org.apache.http.ParseException;
//import org.apache.http.client.ClientProtocolException;
//
//import net.sf.json.JSONException;
//import net.sf.json.JSONObject;
//
//
//public class UnifiedorderServlet extends HttpServlet {
//
//	private static final long serialVersionUID = 1L;
//	//private static final String appid = "wxeb5043ba75e89f51";
////	private static final String appid = "wxab5e4ee5a20faf3f";
////	private static final String mch_id = "1360151602";
////	public static final String api_key = "WuTuoBangAppKeyQueTingKangMobile";
//	private static final String trade_type = "JSAPI";
//	
//	//huban
//	private static final String appid = "wxeb5043ba75e89f51";
//	private static final String mch_id = "1360151602";
//	public static final String api_key = "WuTuoBangAppKeyQueTingKangMobile";
//	
//	
//	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html; charset=utf-8");
////		PrintWriter out = response.getWriter();
////		JSONObject jso = new JSONObject();
////		double money = 0.01;
////		String openId = "", out_trade_no = "", body = "测试", ip = "",address="";
////		try {
////			out.print(execute(openId,out_trade_no, body, money, ip,jso).toString());
////		} catch (JSONException e) {
////			e.printStackTrace();
////		}
////		out.flush();
////		out.close();
//	}
//
//	/**
//	 * 微信服务号统一下单支付
//	 * 
//	 * @param out_trade_no
//	 *            订单号
//	 * @param body
//	 *            标题
//	 * @param openId
//	 *            用户的openId
//	 * @param money
//	 *            支付金额
//	 * @param ip
//	 *            客户端ip
//	 * @param request
//	 *            HttpServletRequest
//	 * @return
//	 * @throws JSONException
//	 */
//	public static JSONObject execute(String nonce_str, String timeStamp, String openId, String out_trade_no, String body, double money, String ip,HttpServletRequest req, JSONObject jso) throws JSONException {
//
//		StringBuilder xml = new StringBuilder();
//		//String notify_url = "http://test.com/payCallBack";// 支付回调地址
//		String prepay_id = "", sign = "", charset = "UTF-8";
//		String notify_url=SmBaseUtil.getCurrentWebUrl(req)+"/WeMoney/payCallBack";
//		try {
//			String weixinMoney = new java.text.DecimalFormat("#").format(money * 100);// 微信是以分为单位的所以要乘以100
//			xml.append("appid=").append(appid).append("&body=").append(new String(body.getBytes(charset),charset));// 处理中文
//			xml.append("&mch_id=").append(mch_id).append("&nonce_str=").append(nonce_str);
//			xml.append("&notify_url=").append(notify_url);
//			xml.append("&openid=").append(openId);
//			xml.append("&out_trade_no=").append(out_trade_no).append("&spbill_create_ip=").append(ip);
//			xml.append("&total_fee=").append(weixinMoney);
//			xml.append("&trade_type=").append(trade_type);
//			xml.append("&key=").append(api_key);
//			sign = MD5Purity(xml.toString());
//			xml.delete(0, xml.length());
//			xml.append("<xml>");
//			xml.append("   <appid>").append(appid).append("</appid>");
//			xml.append("   <body>").append(body).append("</body>");
//			xml.append("   <mch_id>").append(mch_id).append("</mch_id>");
//			xml.append("   <nonce_str>").append(nonce_str).append("</nonce_str>");
//			xml.append("   <notify_url>").append(notify_url).append("</notify_url>");
//			xml.append("   <openid>").append(openId).append("</openid>");
//			xml.append("   <out_trade_no>").append(out_trade_no).append("</out_trade_no>");
//			xml.append("   <spbill_create_ip>").append(ip).append("</spbill_create_ip>");
//			xml.append("   <total_fee>").append(weixinMoney).append("</total_fee>");
//			xml.append("   <trade_type>").append(trade_type).append("</trade_type>");
//			xml.append("   <sign>").append(sign).append("</sign>");
//			xml.append("</xml>");
//
//			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.mch.weixin.qq.com/pay/unifiedorder").openConnection();
//			conn.setDoOutput(true);
//			conn.setRequestMethod("POST");
//			conn.setRequestProperty("Content-Type", "text/xml");
//			conn.setRequestProperty("Charset", charset);
//			OutputStream os = conn.getOutputStream();
//			os.write(xml.toString().getBytes(charset));
//			xml.delete(0, xml.length());
//			os.close();
//			int responseCode = conn.getResponseCode();
//			InputStreamReader in = null;
//			BufferedReader br = null;
//			if (responseCode == 200) {
//				in = new InputStreamReader(conn.getInputStream(), "utf-8");
//				br = new BufferedReader(in);
//				String retData = null;
//				while ((retData = br.readLine()) != null)
//					xml.append(retData);
//				JSONArray childNodes = JSONML.toJSONObject(xml.toString()).getJSONArray("childNodes");
//				int len = childNodes.length() - 1;
//				for (int i = len; i > -1; i--) {
//					org.activiti.engine.impl.util.json.JSONObject js = childNodes.getJSONObject(i);
//					if (js.get("tagName").equals("prepay_id")) {
//						prepay_id = js.getJSONArray("childNodes").getString(0);
//						break;
//					}
//				}
//			}
//			if (in != null)
//				in.close();
//			if (br != null)
//				br.close();
//			conn.disconnect();
//			// ----------------------------------给h5返回的数据
//		
//			//nonce_str = getRandomString(32);
//			jso.put("appId", appid);
//			jso.put("nonceStr", nonce_str);
//			jso.put("package", "prepay_id=" + prepay_id);
//			jso.put("signType", "MD5");
//			jso.put("timeStamp", timeStamp);
//			xml.delete(0, xml.length());
//			xml.append("appId=").append(appid);
//			xml.append("&nonceStr=").append(nonce_str);
//			xml.append("&package=").append(jso.getString("package"));
//			xml.append("&signType=").append(jso.getString("signType"));
//			xml.append("&timeStamp=").append(timeStamp);
//			xml.append("&key=").append(api_key);
//			sign = MD5Purity(new String(xml.toString().getBytes(charset), charset));
//			jso.put("paySign", sign);
//			//返回的数据主要用在这地方 https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=7_7&index=6
//			// ----------------------------------给h5返回的数据
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		} catch (ParseException e) {
//			e.printStackTrace();
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return jso;
//	}
//
//	/**
//	 * MD5
//	 * 
//	 * @param plainText
//	 * @return
//	 */
//	public static String MD5Purity(String plainText) {
//		try {
//			MessageDigest md = MessageDigest.getInstance("MD5");
//			md.update(plainText.getBytes("utf-8"));
//			byte b[] = md.digest();
//			int i;
//			StringBuffer buf = new StringBuffer("");
//			for (int offset = 0; offset < b.length; offset++) {
//				i = b[offset];
//				if (i < 0)
//					i += 256;
//				if (i < 16)
//					buf.append("0");
//				buf.append(Integer.toHexString(i));
//			}
//			plainText = buf.toString();
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		return plainText.toUpperCase();
//	}
//
//	/**
//	 * 生成一个随机字符串
//	 * 
//	 * @param length
//	 *            表示生成字符串的长度
//	 * @return
//	 */
//	private static String getRandomString(int length) {
//		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
//		Random random = new Random();
//		StringBuffer sb = new StringBuffer();
//		for (int i = 0; i < length; i++) {
//			int number = random.nextInt(base.length());
//			sb.append(base.charAt(number));
//		}
//		return sb.toString();
//	}
//}
