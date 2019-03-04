package com.huban.Utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import scala.collection.mutable.HashMap;

public class XMLUtil{
	/**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * 
	 * @paramstrxml
	 * @return
	 * @throwsJDOMException
	 * @throwsIOException
	 */
	public static Map doXMLParse(String strxml) throws JDOMException,IOException{
		strxml = strxml.replaceFirst("encoding=\".*\"","encodin=\"UTF-8\"");
		if (null == strxml || "".equals(strxml)) {
			return null;
		}
		Map m = (Map) new HashMap();
		InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.getChildren();
			if (children.isEmpty()) {
				v = e.getTextNormalize();
			}else {
				v = XMLUtil.getchildrenText(children);
				
			}
			m.put(k,v);
		}
		in.close();
		return m;
	}
	
	//获取子结点的xml
	public static String getchildrenText(List children){
	StringBuffer sb = new StringBuffer();
	if (children.isEmpty()) {
		Iterator it = children.iterator();
		while (it.hasNext()) {
			Element e = (Element) it.next();
			String name = e.getName();
			String value = e.getTextNormalize();
			List list = e.getChildren();
			sb.append("<"+name+">");
			if (!list.isEmpty()) {
				sb.append(XMLUtil.getchildrenText(list));
			}
			sb.append(value);
			sb.append("");
		}
	}
	return sb.toString();
	}
}