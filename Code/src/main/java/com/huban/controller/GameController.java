package com.huban.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huban.Utils.ArgumentsUtils;
import com.huban.Utils.ConstantUtils;
import com.huban.Utils.RandomUtil;
import com.huban.Utils.ReturnCodeUtils;
import com.huban.Utils.ReturnMessageUtils;
import com.huban.construct.LstDeptMemberModel;
import com.huban.construct.LstDeptModel;
import com.huban.construct.LstDeptSave;
import com.huban.construct.UserInfoModel;
import com.huban.pojo.Activity;
import com.huban.pojo.Activitypart;
import com.huban.pojo.AuctionRecords;
import com.huban.pojo.Department;
import com.huban.pojo.DepartmentWithBLOBs;
import com.huban.pojo.IdiomRecords;
import com.huban.pojo.Integration;
import com.huban.pojo.Order;
import com.huban.pojo.Payrecords;
import com.huban.pojo.Reward;
import com.huban.pojo.SaveRead;
import com.huban.pojo.User;
import com.huban.pojo.UserInfo;
import com.huban.publicway.ConstantWay;
import com.huban.publicway.UserWay;
import com.huban.util.BaseUtil;
import com.huban.util.IdWorker;
import com.huban.util.ResultUtil;

/**
 * @ClassName: GameController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Sheryl
 * @date 2017年11月2日 上午11:42:36
 */
@Controller
@RequestMapping("/Game")
public class GameController extends BaseController{
	/**
	 * 定义默认方法处理没有传递参数的错误处理
	 * @return 返回参数列表错误的处理
	 */
	@RequestMapping(value = { "/", "/Interface/", "/Interface/V1/", "/Interface/V2/" })
	public @ResponseBody Map<String, Object> defaultMethod() {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
	
	/**
	 * @Title: InitiateIdiomMethod
	 * @Description: TODO( 发起 )
	 * @param request
	 * @return  参数
	 * @return Map<String,Object>  返回类型
	 * @throws
	 */
	@RequestMapping(value="/Interface/V2/InitiateIdiom",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> InitiateIdiomMethod(HttpServletRequest request){
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		String param;
        param = (String)request.getAttribute(BaseUtil.paramKey);
        if(param==null || param.equals("")){
            param=request.getParameter(BaseUtil.paramKey);
        }
        JSONObject jsonObject = JSON.parseObject(param);
        User pjUser = JSON.parseObject(jsonObject.getString(User.sUserClass), User.class);
        IdiomRecords pjIdiom = JSON.parseObject(jsonObject.getString(IdiomRecords.sIdiomClass), IdiomRecords.class);
        if (0 == pjIdiom.getType()) {//发起
			
		}
        if (1 == pjIdiom.getType()) {//接
			
		}
        
        
        return null;
	}
	
	/**
	 * @Title: BidRecordMethod
	 * @Description: TODO( 接龙列表 )
	 * @param userId
	 * @param page
	 * @param size
	 * @param idiomId
	 * @param request
	 * @return  参数
	 * @return Map<String,Object>  返回类型
	 * @throws
	 */
	@RequestMapping(value="/Interface/V2/IdiomRecord",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> IdiomRecordMethod(@RequestParam String userId, @RequestParam(required=false) Integer page, @RequestParam(required=false) Integer size, @RequestParam Long idiomId, HttpServletRequest request){
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		if (null != page && null != size && 0 != page && 0 != size) {
			mapQuery.put(ArgumentsUtils.sStartKey, (page-1)*size);
			mapQuery.put(ArgumentsUtils.sSizeKey, size);
		}
		if ( null == idiomId || 0 ==  idiomId ) {
			return ResultUtil.sharedInstance().FalseData("参数缺失", ReturnCodeUtils.Error_Parameter.Default.getCode());
		}
		mapQuery.put(ArgumentsUtils.sIDKey, idiomId);
		List<AuctionRecords> LstAuction = auctionService.LstSearchMessage(mapQuery);
        return ResultUtil.sharedInstance().TrueData(LstAuction, ReturnMessageUtils.trueReturnMessageKey, ReturnCodeUtils.Code.OK.getCode());
	}
	
}
