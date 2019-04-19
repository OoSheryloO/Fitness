package com.kjyl.util.util;

import com.kjyl.pojo.User;
import com.kjyl.util.util.ClientResponseHandler;
import com.kjyl.util.util.TenpayHttpClient;
import com.kjyl.util.util.ConstantUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * Created by MeetLucky on 16/6/26.
 */
public class WXPayUtil {
    private static WXPayUtil instance;

    public static WXPayUtil sharedInstance() {
        if (instance == null) {
            instance = new WXPayUtil();
        }

        return instance;
    }

    /**
     *
     *
     *
     * @return Ping++对象
     */
    public static String pushClient(HttpServletRequest request, HttpServletResponse response, User user, int money,String orderNo) throws Exception {
        String result="";
        WeChatPayRequestHandler queryReq = new WeChatPayRequestHandler(request, response);
        queryReq.init();
        queryReq.setKey(ConstantUtil.PARTNER_KEY);
        queryReq.setGateUrl(ConstantUtil.WXPay_TansfersUrl);
        queryReq.setParameter("amount",String.valueOf(money));
        queryReq.setParameter("check_name", "NO_CHECK");//NO_CHECK    原:OPTION_CHECK
        queryReq.setParameter("desc", "我帮你托提现!");
        queryReq.setParameter("mch_appid", ConstantUtil.APP_ID);
        queryReq.setParameter("mchid", ConstantUtil.PARTNER);
        queryReq.setParameter("nonce_str",WXUtil.getNonceStr());
        queryReq.setParameter("openid", user.getWeChatOpenId());
        queryReq.setParameter("partner_trade_no",orderNo);
        queryReq.setParameter("re_user_name", user.getName());//可选
        System.out.println("没有String实例化"+user.getName()+"+实例化"+new String(user.getName()));
        queryReq.setParameter("spbill_create_ip", "122.233.132.237");//getIpAddr(request) 122.233.132.237   原:115.29.185.211
        queryReq.getSign();

 
        String WXParam=queryReq.getParamXML();
        TenpayHttpClient httpClient = new TenpayHttpClient();
        httpClient.setTimeOut(5);
        httpClient.setPostData(WXParam);
        String path =request.getSession().getServletContext().getRealPath("/WEB-INF/rootca.pem");
        httpClient.setCaInfo(new File(path));
        path =request.getSession().getServletContext().getRealPath("/WEB-INF/apiclient_cert.p12");
        httpClient.setCertInfo(new File(path),ConstantUtil.PARTNER);
        //设置请求内容
        httpClient.setReqContent(queryReq.getRequestURL());
       // System.out.println("queryReq:" + queryReq.getRequestURL());
        //后台调用
        if(httpClient.call()) {
            ClientResponseHandler queryRes = new ClientResponseHandler();
            //设置结果参数
            queryRes.setContent(httpClient.getResContent());
            result=httpClient.getResContent();
            System.out.println("queryRes:" + queryRes.getContent());
        }
        return result;
    }
    
    
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        System.err.print("current Ip:"+ip);
        return ip;
    }


}
