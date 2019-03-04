package com.huban.Utils;

import java.text.SimpleDateFormat;

/**
* <p>Title: ConstantUtil</p>
* <p>Description: 常量集合 for java</p>
* <p>Company: huban</p>
* @author sheryl
* @date 2017-6-19 下午12:47:51
*/
public class ConstantUtils {
	public  static  SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
	public  static  SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public  static  SimpleDateFormat sdfDate_zn = new SimpleDateFormat("yyyy年MM月dd日");
	public  static  SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	/** 默认数字 : 0/否 */
	public static final int iDefaultZeroValue = 0;
	
	/** 默认数字 : 1/是 */
	public static final int iDefaultOneValue = 1;
	
	public static final String sSeparator = "@::@";
	
	//day签到积分
	public static final int daymultiple = 1;
	
	//验证码位数
	public static final int securityCodeNum = 6;
	//用户登录编号位数
	public static final int iNumericalOrderLength = 9;
	
	//top5排行分页
	public static final int LimitTopStart = 0;
	public static final int LimitTopSize = 5;
	//
	public static final int numfalse = 0;
	public static final int numtrue = 1;
	//
	public static final int TFsign = 1;
	public static final int TFshare = 1;
	//活动分享积分
	public static final int ActivityShareIntegral = 10;
	//视频分享积分
	public static final int VideoShareIntegral = 10;
	//签到积分
	public static final int SignIntegral = 10;
	//活动参加积分
	public static final int ActivityJoinIntegral = 10;
	//读书积分
	public static final int ReadIntegral = 10;
	//游戏积分
	public static final int GameIntegral = 5;
	//排行活动积分
	public static final int TopIntegral = 10;
    //兑换比例，默认1:100 元:金币
	public static final int convert = 100;
	
	
	//读后感奖励比例，默认 每1000字，积累5分
	public static final double ReadWordsRatio = 0.9;
	public static final int ReadWordsNumber = 100;
	public static final int ReadScore = 3;
	//思维导图奖励比例，默认 每1000字，积累5分
	public static final double MindMapWordsRatio = 0.85;
	public static final int MindMapWordsNumber = 1000;
	public static final int MindMapScore = 3;
	//写作奖励积分
	//奖励比例，默认 每100字，积累10分
	public static final int WordsNumber = 100;
	public static final int score = 10;
	
	//写作奖励积分
	//奖励比例，默认 每100字，积累10分
	public static final double iProportion = 0.1;//一个字多说分
	
	//提现比例，默认100:1 金币:元
	public static final int ebody = 100;
	//最低提现 换算成金币数
	public static final int ebodyGodCoin = 2000;
	
	
	//计算金币扣除 1
	public static final int delGodCoin = 1;
	//微信充值金币  的显示字眼
	public static final String WeChatRecharge = "微信充值金币";
	public static final String AliPayRecharge = "支付宝充值金币";
	public static final String AppleRechargeGold = "苹果充值金币";
	public static final String AppleRecharge = "苹果充值";
	//体现
	public static final String embody = "体现到微信钱包";
	//视频支付
	public static final String payVideo = "视频解锁";
	//活动报名
	public static final String payAct = "活动报名";
	//活动报名退款
	public static final String payActRefund = "活动报名退款";
	//商场消费返还
	public static final String payGoodsBack= "商场消费返还";
	//商场消费
	public static final String payGoods= "商场消费";
	//论坛打赏
	public static final String forumTip = "论坛打赏";
	//积分兑换
	public static final String Conversion = "积分兑换";
	//打赏
	public static final String reward = "被打赏";
	//打赏帖子
	public static final String notereward = "打赏帖子";
	//打赏视频
	public static final String videoreward = "打赏视频";
	//视频支付
	public static final String signSecondBox = "宝箱金币获得";
	//打赏
	public static final String sAuction = "竞拍消费";
	//打赏
	public static final String sAuctionReturn = "竞拍物品返还";
	//查看付费书籍
	public static final String sViewChargesBook = "查看付费书籍";
	//拼接字符串
	public static String addname(String name){
		return "来自"+name+"的打赏";
	}
	
	//积分兑换比例   100积分:1金币
	public static final int Conver = 100;
		
	/*------------积分---------------*/
	
	//V2中 1积分=1金币 
	public static final String AppleRecharge_Integration = "苹果充值响应积分";
	//签到
	public static final String sign = "签到积分";
	//活动分享积分
	public static final String ActivityShare = "活动分享积分";
	//视频分享积分
	public static final String VideoShare = "视频分享积分";
	//阅读积分
	public static final String FirstRead = "首次阅读书籍积分";
	
	//写日志的积分奖励
	public static final String JournalIntegration = "日志奖励积分";
	//存储单的积分奖励
	public static final String ReadSaveSingleIntegration = "存储单写作奖励积分";
	//写计划的积分奖励
	public static final String PlanIntegration = "计划奖励积分";
	//读后感的积分奖励
	public static final String ReadWordsIntegration = "读后感存储奖励积分";
	//思维导图的积分奖励
	public static final String MindMapIntegration = "思维导图存储奖励积分";
//	//写计划的积分奖励
//	public static final String PlanIntegration = "计划奖励积分";
	//交易类型(0:付钱,1:收钱)
	public static final int ShopOut = 0;
	public static final int ShopIn = 1;
	
	//付费记录方式
	public static final String sPayForGoldCoin = "金币";
	public static final String sPayForIntegration = "积分";
	
	//盐字符
	public static final String HexString = "";
	//盐数字
	public static final int Hex = 123456;
	
	//宝箱奖励
	public static final int FirstBox = 5;
	public static final int SecondBox = 20;
	
	//支行首月存储字数判定
	public static final long lFirstDetermineForDeptStatus = 100;//单位/万字
	//升星限制
	public static final long lDeptLevelLimit = 1000;//单位/万字
	public static final int iTopLevel_Status10_Limit = 63;
	public static final int iTopLevel_Status11_Limit = 62;//7.7高限制(转换成八进制77=63)
	//教师指导支行上限
	public static final int iTeacherTeachLimit = 5;//
	//支行指导老师上限
	public static final int iDepartmentTeacherLimit = 1;//
	
	//用户注册未完全的分辨标识
	public static final int iNotCompletelyStatus = 9;
	
	//拍卖获得的分辨标识
	public static final int iGetAuction = 8;
	public static final int iOverAuction = 7;
	
	public static final boolean bFirstReadIntegral = false;
	
	
	//推送类型
	public static final int iPushTypeForBook = 1;//推送书籍
	
//	public static final String sReadBank_App_Tip = "阅读银行提醒";//推送书籍
//	public static final String sTeacherPushBook = "支行指导老师推荐阅读书籍";//推送书籍
	
	public static final String sReadBank_Department_President = "继承行长职位";//
	public static final String sReadBank_Department_Identity = "支行职位变更";//
	public static final String sReadBank_Department_Retreats = "支行踢出";//
	public static final String sReadBank_Department_Disband = "支行解散";//
	public static final String sReadBank_Activity_Refunds = "活动退款";//
	public static final String sReadBank_Activity_Issue = "分行活动发布";//
	public static final String sReadBank_Teacher_Recommend = "推荐阅读";//
	
	//推送跳转类型列表
	public static final Integer iReadBank_DefaultValue = null;//默认null
	public static final int iReadBank_PushType_BookValue = 1;//书籍
	public static final int iReadBank_PushType_ActivityValue = 2;//活动
	
	/** DIct表 type分辨  */
	public static final int iReadBank_DictType_Authorization_Code = 3;//授权码
	
}
