package com.kjyl.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;
import java.util.Properties;

import com.kjyl.service.AdminService;
import com.kjyl.service.ApplyService;
import com.kjyl.service.CardService;
import com.kjyl.service.CartService;
import com.kjyl.service.ClockService;
import com.kjyl.service.ClubService;
import com.kjyl.service.ClubcardService;
import com.kjyl.service.CourseService;
import com.kjyl.service.DictService;
import com.kjyl.service.DiscussService;
import com.kjyl.service.ErrorlogService;
import com.kjyl.service.EventService;
import com.kjyl.service.FitdataService;
import com.kjyl.service.GoodsService;
import com.kjyl.service.IdentityService;
import com.kjyl.service.InfoService;
import com.kjyl.service.OnlineService;
import com.kjyl.service.OpinionService;
import com.kjyl.service.OrderService;
import com.kjyl.service.PayrecordService;
import com.kjyl.service.PictureService;
import com.kjyl.service.PostService;
import com.kjyl.service.StatusService;
import com.kjyl.service.SyllabusService;
import com.kjyl.service.UserService;
import com.kjyl.service.UserlogService;
import com.kjyl.service.VerifyrecordService;

/**
 * 
* @ClassName: BaseController 
* @Description: TODO(基础控制器) 
* @author: Sheryl 
* @date: 2019年3月5日 
*
 */
@Controller
public class BaseController { 
	public Logger logger = LogManager.getRootLogger();
	
	@Autowired
	public AdminService AdminService;
    @Autowired
    public ApplyService ApplyService;
    @Autowired
    public CardService CardService;
    @Autowired
    public CartService CartService;
    @Autowired
    public ClockService ClockService;
    @Autowired
    public ClubcardService ClubcardService;
    @Autowired
    public ClubService ClubService;
    @Autowired
    public CourseService CourseService;
    @Autowired
    public DiscussService DiscussService;
    @Autowired
    public ErrorlogService ErrorlogService;
    @Autowired
    public EventService EventService;
    @Autowired
    public FitdataService FitdataService;
    @Autowired
    public GoodsService	GoodsService;
    @Autowired
    public IdentityService IdentityService;
    @Autowired
    public InfoService InfoService;
    @Autowired
    public OpinionService OpinionService;
    @Autowired
    public OrderService OrderService;
    @Autowired
    public PayrecordService PayrecordService;
    @Autowired
    public PictureService PictureService;
    @Autowired
    public PostService PostService;
    @Autowired
    public StatusService StatusService;
    @Autowired
    public SyllabusService SyllabusService;
    @Autowired
    public UserlogService UserlogService;
    @Autowired
    public UserService UserService;
    @Autowired
    public VerifyrecordService VerifyrecordService;
    @Autowired
    public OnlineService OnlineService;
    @Autowired
    public DictService DictService;
    
        @ExceptionHandler
        public boolean exp(HttpServletRequest request, HttpServletResponse response, Exception ex) throws ServletException, IOException {
            ex.printStackTrace();
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            ex.printStackTrace(new PrintStream(baos));
//            String exception = baos.toString();
//            Properties props=System.getProperties(); //系统属性
//            Errorlogs errorLog=new Errorlogs();
//            errorLog.setErrorlogId(IdWorker.CreateNewID());
//            String sUserid = request.getParameter("userid");
//            if (sUserid == null) {
//            	 	JSONObject jsonObject = JSON.parseObject((String) request.getAttribute(BaseUtil.paramKey));
//            		sUserid = jsonObject.getString(BaseUtil.userIdKey);
//            		if (sUserid == null) {
//            			String uri = request.getRequestURI().replace(request.getContextPath(), "");
//            			Map<String, String> map = HostUtil.getHeadersInfo(request);
//            			String param ="";
//            	        try {
//            	            param = HostUtil.getPostData(request);
//            	        }catch (Exception e){}
//            			String mothod = request.getMethod();
//            			if ("GET" == mothod || "GET".equals(mothod)) {
//            				map.remove("cookie");
//            				map.remove("accept-encoding");
//            				map.remove("user-agent");
//            				map.remove("postman-token");
//            				map.remove("accept");
//            				map.remove("host");
//            				map.remove("connection");
//            				map.remove("cache-control");
//            				map.remove("content-type");
//            				param = JSON.toJSONString(map);
//            			}
//            			if ("/user/updateheadicon".equals(uri)) {
//            				map.remove("cookie");
//            				map.remove("accept-encoding");
//            				map.remove("user-agent");
//            				map.remove("postman-token");
//            				map.remove("accept");
//            				map.remove("host");
//            				map.remove("connection");
//            				map.remove("cache-control");
//            				map.remove("content-type");
//            				map.remove("content-length");
//            				param = JSON.toJSONString(map);
//            			}
//				}
//			}
//            Long lUserid = Long.parseLong(sUserid);
//            errorLog.setErrorlogUserid(lUserid);
//            errorLog.setErrorlogTypeid(new Long(BaseUtil.DeviceType.Server.getCode()));//3
//            errorLog.setErrorlogDevice(props.getProperty("os.name")+props.getProperty("os.arch")+props.getProperty("os.version"));
//            errorLog.setErrorlogNote(exception);
//            errorsLogService.addErrorLog(errorLog);
//            request.getRequestDispatcher("/pages/500.html").forward(request, response);
            return false;

    }

}
