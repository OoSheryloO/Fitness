package com.huban.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huban.pojo.Errorlogs;
import com.huban.pojo.UserPlan;
import com.huban.service.AccessActiveService;
import com.huban.service.ActivityService;
import com.huban.service.ActivitypartService;
import com.huban.service.AddressService;
import com.huban.service.AdminService;
import com.huban.service.AreaService;
import com.huban.service.ArticleService;
import com.huban.service.AuctionRecordsService;
import com.huban.service.BookService;
import com.huban.service.BookpartService;
import com.huban.service.CertificateService;
import com.huban.service.CityService;
import com.huban.service.CommentService;
import com.huban.service.DateService;
import com.huban.service.DepartmentService;
import com.huban.service.DevicesService;
import com.huban.service.DictService;
import com.huban.service.ErrorsLogService;
import com.huban.service.EvaluationService;
import com.huban.service.FeedbacksService;
import com.huban.service.GamepartService;
import com.huban.service.GoodsService;
import com.huban.service.GradeService;
import com.huban.service.IdiomService;
import com.huban.service.InformationService;
import com.huban.service.IntegrationService;
import com.huban.service.LessonService;
import com.huban.service.LikeStatusService;
import com.huban.service.MessageService;
import com.huban.service.NewService;
import com.huban.service.NoteService;
import com.huban.service.OnLineService;
import com.huban.service.OrderService;
import com.huban.service.PayrecordsService;
import com.huban.service.PictureService;
import com.huban.service.ProblemService;
import com.huban.service.PushDrivesService;
import com.huban.service.PushRecordsService;
import com.huban.service.QuestionrecordsService;
import com.huban.service.ReadBookService;
import com.huban.service.RegionsService;
import com.huban.service.RewardService;
import com.huban.service.SaveReadManuscriptService;
import com.huban.service.SaveReadService;
import com.huban.service.SchoolService;
import com.huban.service.ShopService;
import com.huban.service.SignService;
import com.huban.service.UserInfoService;
import com.huban.service.UserJournalRecordService;
import com.huban.service.UserJournalService;
import com.huban.service.UserPlanService;
import com.huban.service.UserService;
import com.huban.service.VerifyrecordsService;
import com.huban.service.VersionService;
import com.huban.service.VideoService;
import com.huban.service.VoiceService;
import com.huban.util.BaseUtil;
import com.huban.util.HostUtil;
import com.huban.util.IdWorker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;
import java.util.Properties;

/**
 * Created by gejaingbo on 17/5/12.
 * 基础控制器
 */
@Controller
public class BaseController { 
	public Logger logger = LogManager.getRootLogger();
    @Autowired
    public UserService userService;
    @Autowired
    public VerifyrecordsService verifyrecordsService;
    @Autowired
    public ErrorsLogService errorsLogService;
    @Autowired
    public MessageService messageService;
    @Autowired
    public OnLineService onLineService;
    @Autowired
    public VideoService videoSerive;
    @Autowired
    public LikeStatusService likeStatusService;
    @Autowired
    public CommentService commentService;
    @Autowired
    public ActivityService activityService;
    @Autowired
    public GamepartService gamepartService;
    @Autowired
    public BookpartService bookpartService;
    @Autowired
    public ActivitypartService activitypartService;
    @Autowired
    public BookService bookService;
    @Autowired
    public NoteService noteService;
    @Autowired
    public RewardService rewardService;
    @Autowired
    public SignService signService;
    @Autowired
    public ShopService shopService;
    @Autowired
    public AddressService addressService;
    @Autowired
    public OrderService orderService;
    @Autowired
    public PayrecordsService payrecordsService;
    @Autowired
    public FeedbacksService feedbacksService;
    @Autowired
    public DevicesService devicesService;
    @Autowired
    public VersionService versionService;
    @Autowired
    public PictureService pictureService;
    @Autowired
    public RegionsService regionsService;
    @Autowired
    public IntegrationService integrationService;
    @Autowired
    public DateService dateService;
    @Autowired
    public DictService dictService;
    @Autowired
    public QuestionrecordsService questionrecordsService;
    @Autowired 
    public UserInfoService userInfoService;
    @Autowired
    public DepartmentService departmentService;
    @Autowired
    public SaveReadService saveReadService;
    @Autowired
    public SaveReadManuscriptService saveReadManuscriptService;
    @Autowired
    public UserJournalService userJournalService;
    @Autowired
    public UserJournalRecordService userJournalRecordService;
    @Autowired
    public UserPlanService userPlanService;
    /*主要是为了地区编码*/
    @Autowired
    public AreaService areaService;
    /*全部学校 地区检索*/
    @Autowired
    public CityService cityService;
    @Autowired
    public SchoolService schoolService;
    
    /**
     * 评测服务
     */
    @Autowired
    public EvaluationService EvaluationService;
    @Autowired
    public ProblemService ProblemService;
    @Autowired
    public GradeService GradeService;
    @Autowired
    public CertificateService CertificateService;
    
    @Autowired
    public PushDrivesService pushDrivesService;
    @Autowired
    public PushRecordsService pushRecordsService;
    
    @Autowired
    public GoodsService goodsService;
    @Autowired
    public AuctionRecordsService auctionService;
    @Autowired
    public IdiomService idiomService;
    @Autowired
    public ReadBookService readBookService;
    
    @Autowired
    public LessonService lessonService;
    @Autowired
    public AccessActiveService accessActiveService;

    @Autowired
    public NewService newService;
    @Autowired
    public InformationService informationService;
    @Autowired
    public VoiceService voiceService;
    @Autowired
    public AdminService adminService;
    @Autowired
    public ArticleService articleService;
    
        @ExceptionHandler
        public boolean exp(HttpServletRequest request, HttpServletResponse response, Exception ex) throws ServletException, IOException {
            ex.printStackTrace();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ex.printStackTrace(new PrintStream(baos));
            String exception = baos.toString();
            Properties props=System.getProperties(); //系统属性
            Errorlogs errorLog=new Errorlogs();
            errorLog.setErrorlogId(IdWorker.CreateNewID());
            String sUserid = request.getParameter("userid");
            if (sUserid == null) {
            	 	JSONObject jsonObject = JSON.parseObject((String) request.getAttribute(BaseUtil.paramKey));
            		sUserid = jsonObject.getString(BaseUtil.userIdKey);
            		if (sUserid == null) {
            			String uri = request.getRequestURI().replace(request.getContextPath(), "");
            			Map<String, String> map = HostUtil.getHeadersInfo(request);
            			String param ="";
            	        try {
            	            param = HostUtil.getPostData(request);
            	        }catch (Exception e){}
            			String mothod = request.getMethod();
            			if ("GET" == mothod || "GET".equals(mothod)) {
            				map.remove("cookie");
            				map.remove("accept-encoding");
            				map.remove("user-agent");
            				map.remove("postman-token");
            				map.remove("accept");
            				map.remove("host");
            				map.remove("connection");
            				map.remove("cache-control");
            				map.remove("content-type");
            				param = JSON.toJSONString(map);
            			}
            			if ("/user/updateheadicon".equals(uri)) {
            				map.remove("cookie");
            				map.remove("accept-encoding");
            				map.remove("user-agent");
            				map.remove("postman-token");
            				map.remove("accept");
            				map.remove("host");
            				map.remove("connection");
            				map.remove("cache-control");
            				map.remove("content-type");
            				map.remove("content-length");
            				param = JSON.toJSONString(map);
            			}
				}
			}
            Long lUserid = Long.parseLong(sUserid);
            errorLog.setErrorlogUserid(lUserid);
            errorLog.setErrorlogTypeid(new Long(BaseUtil.DeviceType.Server.getCode()));//3
            errorLog.setErrorlogDevice(props.getProperty("os.name")+props.getProperty("os.arch")+props.getProperty("os.version"));
            errorLog.setErrorlogNote(exception);
            errorsLogService.addErrorLog(errorLog);
            request.getRequestDispatcher("/pages/500.html").forward(request, response);
            return false;

    }

}
