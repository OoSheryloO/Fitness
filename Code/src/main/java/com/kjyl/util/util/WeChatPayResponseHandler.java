package com.kjyl.util.util;


import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.kjyl.util.util.TenpayUtil;
import com.kjyl.util.util.XMLUtil;
import com.kjyl.util.util.MD5Util;
import org.jdom.JDOMException;

/**
 * 应答处理类
 * 应答处理类继承此类，重写isTenpaySign方法即可。
 * @author miklchen
 *
 */
public class WeChatPayResponseHandler {

	/** 密钥 */
	private String key;

	/** 应答的参数 */
	private SortedMap parameters;

	/** debug信息 */
	private String debugInfo;

	private HttpServletRequest request;

	private HttpServletResponse response;

	private String uriEncoding;

	/**
	 * 构造函数
	 *
	 * @param request
	 * @param response
	 */
	public WeChatPayResponseHandler(HttpServletRequest request,
									HttpServletResponse response) throws IOException, JDOMException {
		this.request = request;
		this.response = response;

		this.key = "";
		this.parameters = new TreeMap();
		this.debugInfo = "";

		this.uriEncoding = "";

		Map m = this.request.getParameterMap();
		if(m.size()<=0) {
			//返回xml  获取InputStream转换为map格式
			InputStream in = this.request.getInputStream();//获取输入流
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			//读取缓存
			byte[] buffer = new byte[2048];
			int length = 0;
			while((length = in.read(buffer)) != -1) {
				bos.write(buffer, 0, length);//写入输出流
			}
			in.close();//读取完毕，关闭输入流
			// 根据输出流创建字符串对象
			System.err.print(new String(bos.toByteArray(), "UTF-8"));
			m= XMLUtil.doXMLParse(new String(bos.toByteArray(), "UTF-8"));
		}
		System.err.print(m.toString());
		Iterator it = m.keySet().iterator();
		while (it.hasNext()) {
			String k = (String)it.next();
			String v = m.get(k).toString();
			this.setParameter(k, v);
		}

	}

	/**
	 *获取密钥
	 */
	public String getKey() {
		return key;
	}

	/**
	 *设置密钥
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * 获取参数值
	 * @param parameter 参数名称
	 * @return String
	 */
	public String getParameter(String parameter) {
		String s = (String)this.parameters.get(parameter);
		return (null == s) ? "" : s;
	}

	/**
	 * 设置参数值
	 * @param parameter 参数名称
	 * @param parameterValue 参数值
	 */
	public void setParameter(String parameter, String parameterValue) {
		String v = "";
		if(null != parameterValue) {
			v = parameterValue.trim();
		}
		this.parameters.put(parameter, v);
	}

	/**
	 * 返回所有的参数
	 * @return SortedMap
	 */
	public SortedMap getAllParameters() {
		return this.parameters;
	}

	/**
	 * 是否财付通签名,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 * @return boolean
	 */
	public boolean isTenpaySign() {
		StringBuffer sb = new StringBuffer();
		Set es = this.parameters.entrySet();
		Iterator it = es.iterator();
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String k = (String)entry.getKey();
			String v = (String)entry.getValue();
			if(!"sign".equals(k) && null != v && !"".equals(v)) {
				sb.append(k + "=" + v + "&");
			}
		}

		sb.append("key=" + this.getKey());

		//算出摘要
		String enc = TenpayUtil.getCharacterEncoding(this.request, this.response);
		String sign = MD5Util.MD5Encode(sb.toString(), enc).toLowerCase();

		String tenpaySign = this.getParameter("sign").toLowerCase();

		//debug信息
		this.setDebugInfo(sb.toString() + " => sign:" + sign +
				" tenpaySign:" + tenpaySign);

		return tenpaySign.isEmpty()|| tenpaySign.equals(sign);
	}
	public String getSign(){
		StringBuffer sb = new StringBuffer();
		Set es = this.parameters.entrySet();
		Iterator it = es.iterator();
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String k = (String)entry.getKey();
			String v = (String)entry.getValue();
			if(!"sign".equals(k) && null != v && !"".equals(v)) {
				sb.append(k + "=" + v + "&");
			}
		}

		//算出摘要
		String enc = TenpayUtil.getCharacterEncoding(this.request, this.response);
		String sign = MD5Util.MD5Encode(sb.toString(), enc).toLowerCase();

		String tenpaySign = this.getParameter("sign").toLowerCase();



		return tenpaySign;
	}
	/**
	 * 返回处理结果给财付通服务器。
	 * @param msg: Success or fail。
	 * @throws IOException
	 */
	public void sendToCFT(String msg) throws IOException {
		String strHtml = msg;
		PrintWriter out = this.getHttpServletResponse().getWriter();
		out.println(strHtml);
		out.flush();
		out.close();

	}

	/**
	 * 获取uri编码
	 * @return String
	 */
	public String getUriEncoding() {
		return uriEncoding;
	}

	/**
	 * 设置uri编码
	 * @param uriEncoding
	 * @throws UnsupportedEncodingException
	 */
	public void setUriEncoding(String uriEncoding)
			throws UnsupportedEncodingException {
		if (!"".equals(uriEncoding.trim())) {
			this.uriEncoding = uriEncoding;

			// 编码转换
			String enc = TenpayUtil.getCharacterEncoding(request, response);
			Iterator it = this.parameters.keySet().iterator();
			while (it.hasNext()) {
				String k = (String) it.next();
				String v = this.getParameter(k);
				v = new String(v.getBytes(uriEncoding.trim()), enc);
				this.setParameter(k, v);
			}
		}
	}

	/**
	 *获取debug信息
	 */
	public String getDebugInfo() {
		return debugInfo;
	}

	/**
	 *设置debug信息
	 */
	protected void setDebugInfo(String debugInfo) {
		this.debugInfo = debugInfo;
	}

	protected HttpServletRequest getHttpServletRequest() {
		return this.request;
	}

	protected HttpServletResponse getHttpServletResponse() {
		return this.response;
	}

}
