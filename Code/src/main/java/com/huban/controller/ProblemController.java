/**
 * 
 */
package com.huban.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huban.Utils.ReturnCodeUtils;
import com.huban.pojo.Certificate;
import com.huban.pojo.Evaluation;
import com.huban.pojo.Grade;
import com.huban.pojo.Problem;
import com.huban.pojo.User;
import com.huban.Utils.ArgumentsUtils;
import com.huban.Utils.Constant;
import com.huban.util.BaseUtil;
import com.huban.util.IdWorker;
import com.huban.util.ResultUtil;

/**
 * @author GeJiangbo
 * @date 2017年5月23日
 */
@Controller
@RequestMapping("/problem")
public class ProblemController extends BaseController{
	/**
	 * 
		 * 
		 * @Author 作者：马健
		 * @Phone  联系qq：1039510196
		 * @CreateTime 创建时间：2017年9月26日 下午12:29:12 
		 * @Details 题库 http://127.0.0.1/ReadBank/problem/problemlist?type=1&userId=890836967086100480
	 */
	@RequestMapping(value="/problemlist",method={RequestMethod.GET,RequestMethod.POST})
	public
	@ResponseBody
	Map<String, Object> problemlist(@RequestParam int type,@RequestParam(required=false) int level ,HttpServletRequest request) throws UnsupportedEncodingException{
		Map<String, Object> result=new HashMap<String, Object>();
		Map<String, Object> map=new HashMap<String, Object>();
		String UID = request.getParameter(BaseUtil.userIdKey);
		User users=userService.selectUserByUserId(Long.parseLong(UID));
		if (type==1) {//心里测试
			//查询学生是否大于3年级
			map.put(ArgumentsUtils.sIDKey, users.getUserId());
			Map<String, Object> mapResult = userInfoService.QuerySomeMessage(map);
			map.clear();
			if (Integer.parseInt(mapResult.get("Grade").toString()) < 3) {
				return ResultUtil.sharedInstance().FalseData("只适合3年级以上的同学", ReturnCodeUtils.Code.NO.getCode());
			}
		}else if(type==2) {// 阅读素养 
			map.put("level", level);
			//大于2次的线上 ，大于一次的线下  才能继续下一次
			if (level>1) {
				Map<String, Object> maplevel=new HashMap<>();
				maplevel.put("type", 2);
				maplevel.put("online", 0);
				maplevel.put("userid", UID);
				int count=GradeService.getGradeCount(maplevel);
				maplevel=new HashMap<>();
				maplevel.put("type", 2);
				maplevel.put("online", 1);
				maplevel.put("userid", UID);
				int countx=GradeService.getGradeCount(maplevel);
				if (count>=2&&countx>=1) {
					//通过验证，可以进入下次比赛
				}else {
					return ResultUtil.sharedInstance().FalseData("您还未完成上一阶段的问题，无法进入这个阶段", ReturnCodeUtils.Code.NO.getCode());
				}
			}
		}
		map.put("type", type);
		map.put("start", 0);
		map.put("size", 15);
		map.put("orderrand", 1);//随机
		List<Problem> list = ProblemService.getProblemList(map);
		int count=0;
		if (list.size()>0) {
			//小鸡吃米算法 7 12 11 1 3 5
			for (int i = 0; i < list.size(); i++) {
				int grade=0;
				if (i<7) {
					grade=1;
				}else if(i>7&&i<19){
					grade=3;
				}else {
					grade=5;
				}
				list.get(i).setGrade(grade);
				count=count+grade;
			}
			System.err.println("总分数"+count);
			result=ResultUtil.sharedInstance().TrueData(list, String.valueOf(list.size()), ReturnCodeUtils.Code.OK.getCode());
		}else {
			result=ResultUtil.sharedInstance().FalseData("题库为空", ReturnCodeUtils.Code.NO.getCode());
		}
		return result;
	}
	
	/**
		 * @Author 作者：马健
		 * @Phone  联系qq：1039510196
		 * @CreateTime 创建时间：2017年9月26日 下午2:11:21 
		 * @Details 增加成绩
	 */
	@RequestMapping(value="/addProblemGrade",method={RequestMethod.GET,RequestMethod.POST})
	public
	@ResponseBody
	Map<String, Object> addProblemGrade(@RequestParam int type,@RequestParam(required=false) int level ,@RequestParam String grade,HttpServletRequest request) throws UnsupportedEncodingException{
		Map<String, Object> result=new HashMap<String, Object>();
		String UID = request.getParameter(BaseUtil.userIdKey);
		User users=userService.selectUserByUserId(Long.parseLong(UID));
		Grade grades=new Grade();
		grades.setId(IdWorker.CreateNewID());
		if (type==1) {//心里测试
			grades.setLevel(0);
		}else if(type==2) {// 阅读素养
			//level
			grades.setLevel(level);
		}else if(type==3) {//学科/项目
			grades.setLevel(0);
		}
		grades.setStatus(1);
		grades.setType(type);
		grades.setUserid(Long.parseLong(UID));
		grades.setGrade(grade);
		grades.setPkid(String.valueOf(grades.getId()));
		grades.setOnline(0);
		int i=GradeService.addGrade(grades);
		if (i>0) {
			result=ResultUtil.sharedInstance().TrueData(grades, "写入成绩成功", ReturnCodeUtils.Code.OK.getCode());
		}else {
			result=ResultUtil.sharedInstance().FalseData("写入成绩失败", ReturnCodeUtils.Code.NO.getCode());
		}
		return result;
	}
	
	
	@RequestMapping(value="/getCertificate",method={RequestMethod.GET,RequestMethod.POST})
	public
	@ResponseBody
	Map<String, Object> getCertificate(@RequestParam long gradeid,HttpServletRequest request) throws UnsupportedEncodingException{
		Map<String, Object> result=new HashMap<String, Object>();
		String UID = request.getParameter(BaseUtil.userIdKey);
		Map<String, Object> map=new HashMap<>();
		map.put("gradeid", gradeid);
		List<Certificate> certificates=CertificateService.getCertificateList(map);
		if (certificates.size()>0) {
			result=ResultUtil.sharedInstance().FalseData("你已获取过该测试的证书，无需重复获取", ReturnCodeUtils.Code.NO.getCode());
		}else {
			map=new HashMap<>();
			map.put("id", gradeid);
			List<Grade> grades=GradeService.getGradeList(map);
			if (grades.size()>0) {
				if ((grades.get(0).getType()==2&&Integer.parseInt(grades.get(0).getGrade())>=90)||grades.get(0).getType()==1||grades.get(0).getType()==3) {
					Certificate certificate=new Certificate();
					certificate.setId(IdWorker.CreateNewID());
					if (grades.get(0).getType()==1) {//
						certificate.setName("心理测试"+"证书");
					}else if(grades.get(0).getType()==2) {
						certificate.setName("阅读素养测试"+"证书");
					}else if(grades.get(0).getType()==3) {
						certificate.setName("学科/项目测试"+"证书");
					}
					certificate.setType(grades.get(0).getType());
					certificate.setGrade(grades.get(0).getGrade());
					certificate.setGradeid(grades.get(0).getId());
					certificate.setUserid(Long.parseLong(UID));
					certificate.setStatus(1);
					certificate.setPkid(String.valueOf(certificate.getId()));
					certificate.setLevel(grades.get(0).getLevel());
					int i=CertificateService.addCertificate(certificate);
					if (i>0) {
						result=ResultUtil.sharedInstance().TrueData(certificate, "获取证书成功", ReturnCodeUtils.Code.OK.getCode());
					}else {
						result=ResultUtil.sharedInstance().FalseData("获取证书失败", ReturnCodeUtils.Code.NO.getCode());
					}
				}else {
					result=ResultUtil.sharedInstance().FalseData("获取证书失败，这次考试成绩没有大于90分", ReturnCodeUtils.Code.NO.getCode());
				}
			}else {
				result=ResultUtil.sharedInstance().FalseData("当前获取出现异常，请稍后重试", ReturnCodeUtils.Code.NO.getCode());
			}
		}
		return result;
	}
	
	
}
