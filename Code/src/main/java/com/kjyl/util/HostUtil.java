package com.kjyl.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


public class HostUtil {
    private static HostUtil instance;

    public static HostUtil sharedInstance() {
        if (instance == null) {
            instance = new HostUtil();
        }

        return instance;
    }

    /**
     * 获取服务端 IP 地址
     *
     * @param request 请求
     * @return IP 地址
     */
    public String serverAddress(HttpServletRequest request) {
        String address = request.getLocalAddr();
        if(address.equals("0:0:0:0:0:0:0:1")){
            address="127.0.0.1";
        }
        return address;
    }

    /**
     * 获取域名地址
     * @return  域名
     */
    public String serverDomain() {
        return "app.whohelp.cc";
    }

    /**
     * 获取服务端端口地址
     *
     * @param request 请求
     * @return 端口地址
     */
    public String serverPort(HttpServletRequest request) {
        String port = Integer.toString(request.getLocalPort());
        return port;
    }

    /**
     * 获取项目名称
     *
     * @param request 请求
     * @return 项目名称
     */
    public String projectName(HttpServletRequest request) {
        String name = request.getContextPath();
        if(name.isEmpty()){
            name="/";
        }
        return name;
    }

    /**
     * 根据请求获取协议名称
     * @param request 请求
     * @return  协议名称
     * @throws MalformedURLException    异常的 URL 格式
     * @throws URISyntaxException   URI 语法错误
     */
    public String serverProtocol(HttpServletRequest request) throws MalformedURLException, URISyntaxException {
        URL url = new URL(request.getRequestURL().toString());
        URI uri = url.toURI();
        String protocol = uri.getScheme();
        return protocol;
    }

    public static String getPostData(HttpServletRequest request) throws Exception{
        BufferedReader reader = request.getReader();
        char[] buf = new char[512];
        int len = 0;
        StringBuffer contentBuffer = new StringBuffer();
        while ((len = reader.read(buf)) != -1) {
            contentBuffer.append(buf, 0, len);
        }
        return contentBuffer.toString();
    }

    public static Map<String, String> getHeadersInfo(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration<?> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }
}
