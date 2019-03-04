package com.huban.Utils;

import javax.servlet.http.HttpServletRequest;

public class Constant {
    public static String getProjectBaseUrl(HttpServletRequest request){
    	String basePath=request.getScheme() + "://"
    			+ request.getServerName() + ":" + request.getServerPort()
    			+ request.getContextPath() + "/";
    
    	return basePath;
    }
}

