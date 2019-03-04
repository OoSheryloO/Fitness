package com.huban.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huban.util.BaseUtil;
import com.huban.util.ResultUtil;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;

/**
 * Created by MeetLucky on 16/5/27.
 */
@Controller
@RequestMapping("/Error")
public class ErrorController {
	
    //  未知错误
    @RequestMapping(value = "/Interface/Unknown")
    public
    @ResponseBody
    Map<String, Object> unknownMethod() {
        ResultUtil errorDetail = ResultUtil.sharedInstance();
        return errorDetail.otherError(BaseUtil.ErrorMessageType.UnKownError,null);
    }

    //  参数错误
    @RequestMapping(value = "/Interface/Parameter")
    public
    @ResponseBody
    Map<String, Object> parameterMethod() {
        ResultUtil errorDetail = ResultUtil.sharedInstance();
        return errorDetail.otherError(BaseUtil.ErrorMessageType.Parameter,null);
    }

    //  认证失败
    @RequestMapping(value = "/Interface/Authorized")
    public
    @ResponseBody
    Map<String, Object> authorizedMethod() {
        ResultUtil errorDetail = ResultUtil.sharedInstance();
        return errorDetail.otherError(BaseUtil.ErrorMessageType.Authorized,null);
    }

    //  用户不存在
    @RequestMapping(value = "/Interface/UserNoExist")
    public
    @ResponseBody
    Map<String, Object> userNoExistMethod() {
        ResultUtil errorDetail = ResultUtil.sharedInstance();
        return errorDetail.otherError(BaseUtil.ErrorMessageType.UserNoExist,null);
    }
    //  认证失败
    @RequestMapping(value = "/Interface/JsonError")
    public
    @ResponseBody
    Map<String, Object> jsonErrorMethod(HttpServletRequest request) {
        ResultUtil errorDetail = ResultUtil.sharedInstance();
        return errorDetail.otherError(BaseUtil.ErrorMessageType.JsonParseError,request);
    }
}
