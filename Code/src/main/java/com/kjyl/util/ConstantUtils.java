package com.kjyl.util;

import java.text.SimpleDateFormat;

/**
* <p>Title: ConstantUtil</p>
* <p>Description: 常量集合 for java</p>
* <p>Company: huban hangzhou</p>
* @author sheryl
* @date 2017-6-19 下午12:47:51
*/
public class ConstantUtils {
/** value：Id */ public  static  SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
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
	
	/**
     * 标记用户数据被删除, 默认为有效数据
     */
    public enum DeletedFlag {
        Effective { //  有效的数据: 默认为有效的数据

            public int getCode() {
                return 0;
            }
        },

        Invalid {   //  无效的数据: 假删除标记

            public int getCode() {
                return 1;
            }
        };

        public abstract int getCode();
    }
    /**
     * 增加积分类型
     */
    public enum IntegrationType {
        ShareFriend { //  分享微信好友

            public int getCode() {
                return 1;
            }
        },

        ShareSina {   //  分享新浪

            public int getCode() {
                return 2;
            }
        },

        ShareFriendCircle  {   //  分享朋友圈

            public int getCode() {
                return 3;
            }
        },

        PublishOrder {   //  发布订单

            public int getCode() {
                return 4;
            }
        },

        ReceiveOrder {   //  接受订单

            public int getCode() {
                return 5;
            }
        },

        Comment {   //  评价

            public int getCode() {
                return 6;
            }
        },

        CancelOrder {   //  取消订单

            public int getCode() {
                return 7;
            }
        },

        Report {   //  举报(订单,用户)

            public int getCode() {
                return 8;
            }
        },

        Revive {   //  恢复积分

            public int getCode() {
                return 9;
            }
        },

        Punish {   //  惩罚 恶意评价 恶意刷单 恶意投诉等等

            public int getCode() {
                return 10;
            }
        },

        OpinionAdoption {   //  意见采纳

            public int getCode() {
                return 11;
            }
        },

        Recharge {   //  充值

            public int getCode() {
                return 12;
            }
        },

        AddFriend {   //  添加好友

            public int getCode() {
                return 13;
            }
        },

        LuckDraw {   //  抽奖

            public int getCode() {
                return 14;
            }
        },

        reward {   //  其他奖励

            public int getCode() {
                return 15;
            }
        },

        Certification {   //  实名认证

            public int getCode() {
                return 16;
            }
        };

        public abstract int getCode();
    }

    /**
     * 标记是否默认, 新增加的数据均为普通状态
     */
    public enum DefaultFlag {
        General {   //  一般或者普通的状态: 默认为一般状态

            public int getCode() {
                return 0;
            }
        },

        Special {   //  默认的或特殊的状态: 设置默认的值或者方式

            public int getCode() {
                return 1;
            }
        };

        public abstract int getCode();
    }

    /**
     * 地区级别, 用于区域的树形结构显示
     */
    public enum RegionLevel {
        Country {   //  国家

            public int getCode() {
                return 0;
            }
        },

        Province {  //  省份、特别行政区、直辖市

            public int getCode() {
                return 1;
            }
        },

        City {  //  城市

            public int getCode() {
                return 2;
            }
        },

        County {    //  县、 地级市

            public int getCode() {
                return 3;
            }
        },

        Town {  //  镇、 小区

            public int getCode() {
                return 4;
            }
        },

        Village {   //  村

            public int getCode() {
                return 5;
            }
        },

        Street {    //  街

            public int getCode() {
                return 6;
            }
        },

        Road {  //  路

            public int getCode() {
                return 7;
            }
        };

        public abstract int getCode();
    }

    //  图片类型

    /***
     * 图片类型
     */
    public enum PictureType {
        PlatformPicture {  //  平台图片、系统图片

            public int getCode() {
                return 0;
            }
        },

        PortraitPicture {  //  用户头像

            public int getCode() {
                return 1;
            }
        },

        PortraitHistory {   //  历史头像
            public int getCode() {
                return 2;
            }
        },

        CertificationPicture { //  实名认证图片

            public int getCode() {
                return 3;
            }
        },

        CertificationHistory { //  实名认证历史图片

            public int getCode() {
                return 4;
            }
        },

        ResumePicture { //  简历头像

            public int getCode() {
                return 5;
            }
        },

        ResumeHistory { //  简历历史头像

            public int getCode() {
                return 6;
            }
        },


        OrderPicture {    //  订单图片

            public int getCode() {
                return 7;
            }
        },

        ChatPicture { //  聊天图片

            public int getCode() {
                return 8;
            }
        },

        OtherPicture {  //  其他类型图片

            public int getCode() {
                return 9;
            }
        },
        AdminPicture { //  管理员头像

            public int getCode() {
                return 10;
            }
        },
        ReportPicture { //  管理员头像

            public int getCode() {
                return 11;
            }
        },
        ActivityPicture { //  管理员头像

            public int getCode() {
                return 12;
            }
        };

        public abstract int getCode();
    }

    /***
     * 关键字类型
     */
    public enum KeywordType {
        LimitChar { // 平台关键字或者、系统关键字

            public int getCode() {
                return 0;
            }
        },
        SendCommentChar {   //  发单人评价字符

            public int getCode() {
                return 1;
            }
        },
        ReceiveCommentChar {   //  接单人评价字符

            public int getCode() {
                return 2;
            }
        },
        ComplainChar {   //  投诉字符

            public int getCode() {
                return 3;
            }
        },
        SearchChar {  //  用户搜索关键字

            public int getCode() {
                return 4;
            }
        },
        CancelChar {  //  取消原因关键字

            public int getCode() {
                return 5;
            }
        };
        public abstract int getCode();
    }

    /**
     * 用户状态类型
     */
    public enum UserStatus {
        Enable {    //  可用: 正常状态

            public int getCode() {
                return 0;
            }
        },

        Disabled {  //  禁用: 一段时间后继续可以使用

            public int getCode() {
                return 1;
            }
        },

        Invalid {   //  无效: 永久封号不允许登录系统

            public int getCode() {
                return 2;
            }
        },

        Previous {  //  废弃: 关联的上次使用的用户

            public int getCode() {
                return 3;
            }
        },

        TimeOver {  //  验证码 超时

            public int getCode() {
                return 4;
            }
        };

        public abstract int getCode();
    }

    /***
     * 性别类型
     */
    public enum GenderType {
        None {  //  未知的: 默认的性别

            public int getCode() {
                return 0;
            }
        },

        Male {  //  男性

            public int getCode() {
                return 1;
            }
        },

        Female {    //  女性

            public int getCode() {
                return 2;
            }
        };

        public abstract int getCode();
    }

    /**
     * 实名认证审核类型
     */
    public enum CertificationType {
        Oneself { // 人工审核: 自己平台审核

            public int getCode() {
                return 0;
            }
        },

        Agency { // 机构审核: 第三方平台审核

            public int getCode() {
                return 1;
            }
        };

        public abstract int getCode();
    }


    /**
     * 实名认证审核结果
     */
    public enum CertificationFlag {
        Failure {   //  审核失败

            public int getCode() {
                return 0;
            }
        },

        Success {   //  审核成功

            public int getCode() {
                return 1;
            }
        };

        public abstract int getCode();
    }

    /**
     * 实名认证审核状态
     */
    public enum CertificationStatus {
        None {  //  没有

            public int getCode() {
                return 0;
            }
        },

        Wait {  //  待审

            public int getCode() {
                return 1;
            }
        },

        Again { //  重新

            public int getCode() {
                return 2;
            }
        },

        Fail {  //  拒绝

            public int getCode() {
                return 3;
            }
        },

        Pass {  //  通过

            public int getCode() {
                return 4;
            }
        };

        public abstract int getCode();
    }

    /**
     * 是否开启账号保护, 开启后异地登录会有推送
     */
    public enum ProtectionFlag {
        Disabled { // 禁用:一段时间

            public int getCode() {
                return 0;
            }
        },

        Enable { // 可用:正常状态

            public int getCode() {
                return 1;
            }
        };

        public abstract int getCode();
    }

    /**
     * 是否接受委托, 开启后其他用户才能制定你帮忙
     */
    public enum AcceptFlag {
        Close { // 拒绝接受委托

            public int getCode() {
                return 0;
            }
        },

        Open { // 允许接受委托

            public int getCode() {
                return 1;
            }
        };

        public abstract int getCode();
    }

    /**
     * 是否免密支付
     */
    public enum PaymentFlag {
        Disabled { // 禁用:一段时间

            public int getCode() {
                return 0;
            }
        },

        Enable { // 可用:正常状态

            public int getCode() {
                return 1;
            }
        };

        public abstract int getCode();
    }

    /**
     * 支付类型
     */
    public enum PaymentType {
        Balance {   //  我的余额: 使用钱包零钱进行支付

            public int getCode() {
                return 0;
            }
        },

        Alipay {    //  支付宝

            public int getCode() {
                return 1;
            }
        },

        WeChatPay { //  微信支付

            public int getCode() {
                return 2;
            }
        },

        UnionPay {  //  银联支付

            public int getCode() {
                return 3;
            }
        };

        public abstract int getCode();
    }

    /**
     * 付款的状态
     */
    public enum PaymentStatus {
        Normal {    //  默认

            public int getCode() {
                return 0;
            }
        },

        Freeze {    //  冻结

            public int getCode() {
                return 1;
            }
        },

        Refund {    //  退款

            public int getCode() {
                return 2;
            }
        }
        ;

        public abstract int getCode();
    }

    /*
     * 交易类型
     */
    public enum TradeType {
        Payment { // 付钱
            public int getCode() {
                return 0;
            }
        },
        Collect { // 收钱
            public int getCode() {
                return 1;
            }
        },
        Credit {    //  充值
            public int getCode() {
                return 3;
            }
        },
        Withdraw {    //  提现
            public int getCode() {
                return 4;
            }
        },
        Other {    //  其他
            public int getCode() {
                return 5;
            }
        };
        public abstract int getCode();
    }

    /**
     * 地址类别
     */
    public enum AddressType {
        History {   //  历史地址
            public int getCode() {
                return 0;
            }
        },
        Favorite {  //  常用地址
            public int getCode() {
                return 1;
            }
        };
        public abstract int getCode();
    }

    /**
     * 地址状态
     */
    public enum AddressStatus {
        Ordinary {    //  普通地址
            public int getCode() {
                return 0;
            }
        },
        Default {    //  默认地址
            public int getCode() {
                return 1;
            }
        };
        public abstract int getCode();
    }

    /**
     * 简历状态, 打开的简历才可以搜索
     */
    public enum VitaeStatus {
        Close {    //  关闭状态
            public int getCode() {
                return 0;
            }
        },
        Open {    //  启用状态
            public int getCode() {
                return 1;
            }
        };
        public abstract int getCode();
    }

    /**
     * 设备类型
     */
    public enum DeviceType {
        Android { //  安卓设备
            public int getCode() {
                return 0;
            }
        },
        iOS { //  苹果设备
            public int getCode() {
                return 1;
            }
        },
        Desktop { //  笔记本
            public int getCode() {
                return 2;
            }
        },
        Server { //  服务器
            public int getCode() {
                return 3;
            }
        },
        All { //  所有设备
            public int getCode() {
                return -1;
            }
        };
        public abstract int getCode();
    }

    /**
     * 会话状态, 登录状态
     */
    public enum SessionStatus {
        OffLine {    //  离线
            public int getCode() {
                return 0;
            }
        },
        OnLine {    //  在线
            public int getCode() {
                return 1;
            }
        };
        public abstract int getCode();
    }

    /**
     * 推送类型
     */
    public enum PushType {
        Account {
            public int getCode() {
                return 0;
            }
        },
        Specified { //  指定人
            public int getCode() {
                return 1;
            }
        },
        Receive { //  接单了
            public int getCode() {
                return 2;
            }
        },
        Complete { //   完成
            public int getCode() {
                return 3;
            }
        };
        public abstract int getCode();
    }

    /**
     * 推送的状态
     */
    public enum PushStatus {
        Close {    //  关闭
            public int getCode() {
                return 1;
            }
        },
        Open {    //  开启
            public int getCode() {
                return 0;
            }
        };

        public abstract int getCode();
    }

    /**
     * 工作类型
     */
    public enum WorkType {
        Permanent { //  全职
            public int getCode() {
                return 0;
            }
        },
        Parttime {  //  兼职
            public int getCode() {
                return 1;
            }
        },
        Internship {    //  实习
            public int getCode() {
                return 2;
            }
        };
        public abstract int getCode();
    }

    /**
     * 订单类型
     */
    public enum OrderType {
        Sender {    //  发单: 他人可以接单
            public int getCode() {
                return 0;
            }
        },
        SenderReceive {   //  接单: 完成别人需求

            public int getCode() {
                return 1;
            }
        },
        Appobyte {   //  指定:指定他人接单
            public int getCode() {
                return 2;
            }
        },
        AppobyteReceive {   //  接单: 完成别人需求
            public int getCode() {
                return 3;
            }
        };
        public abstract int getCode();
    }

    //  订单状态
    public enum OrderStatus {
        Publish { // 发布:等待接单
            public int getCode() {
                return 0;
            }
        },
        Reject { // 拒绝:他人委托给我,我拒绝接受
            public int getCode() {
                return 1;
            }
        },
        Doing { // 接单:正在完成,正在做订单
            public int getCode() {
                return 2;
            }
        },
        Retreat { //  退单: 用户接单了又要取消接单
            public int getCode() {
                return 3;
            }
        },
        Finish { // 完成:接单人确认完成订单还没付款,双方未评价
            public int getCode() {
                return 4;
            }
        },
        Refund { // 退款:退回金额
            public int getCode() {
                return 6;
            }
        },
        Cancel { //  取消:删除订单
            public int getCode() {
                return 8;
            }
        },
        Expire { // 过期:时间过期
            public int getCode() {
                return 9;
            }
        },
        Complaint { // 投诉:违法投诉
            public int getCode() {
                return 10;
            }
        },
        Payment { // 付款完成:发单人未评价,接单人未评价
            public int getCode() {
                return 5;
            }
        },
        PayMentNotComment { // 评价:发单人未评价 对方已评价
            public int getCode() {
                return 11;
            }
        },
        Comment { // 评价:发单人已经评价 接单人未评价
            public int getCode() {
                return 7;
            }
        },
        ReceiveComomit { // 接单人评价,发单人未付款
            public int getCode() {
                return 12;
            }
        },
        FinishComomit { // 全部完成评价
            public int getCode() {
                return 13;
            }
        };
        public abstract int getCode();
    }

    /**
     * 推送的状态
     */
    public enum CloseStatus {
        End {    //  关闭
            public int getCode() {
                return 1;
            }
        },
        Start {    //  进行中
            public int getCode() {
                return 0;
            }
        };
        public abstract int getCode();
    }

    /**
     * 是否接受委托类型
     */
    public enum SpareType {
        Free {    //   空闲的可以接单
            public int getCode() {
                return 0;
            }
        },
        Busy {   //  忙碌的无法接受他人委托
            public int getCode() {
                return 1;
            }
        };
        public abstract int getCode();
    }


    /**
     * 投诉的状态
     */
    public enum ReportStatus {
        Send { // 刚提交投诉信息
            public int getCode() {
                return 0;
            }
        },
        Check { // 正在审核
            public int getCode() {
                return 1;
            }
        },
        Pass { // 审核通过
            public int getCode() {
                return 2;
            }
        },
        Fail { // 审核未能通过
            public int getCode() {
                return 3;
            }
        };
        public abstract int getCode();
    }

    public enum FeedbackStatus {
        Commit { // 刚提交投诉信息
            public int getCode() {
                return 0;
            }
        },
        Pass { // 采纳
            public int getCode() {
                return 1;
            }
        },
        Fail { // 不采纳
            public int getCode() {
                return 2;
            }
        };
        public abstract int getCode();
    }
    /**
     * 推送的消息类型
     */
    public enum MessageType {
        System {    //   系统通知
            public int getCode() {
                return 0;
            }
        },
        Activity {   //  活动通知
            public int getCode() {
                return 1;
            }
        },
        Order {   //  订单通知
            public int getCode() {
                return 2;
            }
        };

        public abstract int getCode();
    }

    /**
     * 推送的消息类型
     */
    public enum PollState {
        Poll {    //   轮询中
            public int getCode() {
                return 0;
            }
        },

        ClosePoll {   //  关闭轮询
            public int getCode() {
                return 1;
            }
        };

        public abstract int getCode();
    }

    /**
     * 全局错误信息
     */
    public enum ErrorMessageType {//-1为其他错误
        /* 系统级错误信息 */
        OtherError {
            public int getCode() {
                return 10000;
            }
        },
        UnKownError {   //  未知错误
            public int getCode() {
                return 10001;
            }
        },

        Parameter { //   参数错误
            public int getCode() {
                return 10002;
            }
        },
        UnSupportCurrentVersion{
            public int getCode() {
                return 10003;
            }
        },
        JsonParseError  {
            public int getCode() {
                return 10004;
            }
        }
        ,
        /**
         * 用户级错误信息描述
         */
        AccountStop {

            public int getCode() {
                return 10005;
            }
        },
        Authorized { // 认证失败
            public int getCode() {
                return 10006;
            }
        },
        AccountDisabled {

            public int getCode() {
                return 10007;
            }
        },
        UserNoExist  {
            public int getCode() {
                return 10008;
            }
        },
        AccountUnPassAuth  {
            public int getCode() {
                return 10009;
            }
        },

        /**
         * 通知/提醒类错误信息代码
         */
        ProcessFail  {
            public int getCode() {
                return 20001;
            }
        },
        ProcessSuccess  {
            public int getCode() {
                return 20002;
            }
        },
        ParamEmpty  {
            public int getCode() {
                return 20003;
            }
        },
        DeleteSuccess  {
            public int getCode() {
                return 20004;
            }
        },
        DeleteFail  {
            public int getCode() {
                return 20005;
            }
        },
        ModifySuccess  {
            public int getCode() {
                return 20006;
            }
        },
        ModifuFail  {
            public int getCode() {
                return 20007;
            }
        },
        AddSuccess  {
            public int getCode() {
                return 20008;
            }
        },
        AddFail  {
            public int getCode() {
                return 20009;
            }
        },
        RecordUnExist  {
            public int getCode() {
                return 20010;
            }
        },
        NoMore{
            public int getCode() {
                return 20011;
            }
        },

        /* 业务错误/提醒信息代码 */
        AddressUnExist  {
            public int getCode() {
                return 30016;
            }
        },
        CityEmpt  {
            public int getCode() {
                return 30017;
            }
        },
        AreaEmpty  {
            public int getCode() {
                return 30018;
            }
        },
        DetailEmpty  {
            public int getCode() {
                return 30019;
            }
        },
        LatlngEmpty  {
            public int getCode() {
                return 30020;
            }
        },
        AddressSaveFail  {
            public int getCode() {
                return 30021;
            }
        },
        DefaultAddressFail  {
            public int getCode() {
                return 30022;
            }
        },
        DeleteAddressFail  {
            public int getCode() {
                return 30023;
            }
        },
        CollectionAddressFail  {
            public int getCode() {
                return 30024;
            }
        },
        AddressExist  {
            public int getCode() {
                return 30025;
            }
        },
        InterfaceDisabled  {
            public int getCode() {
                return 30026;
            }
        },
        /* 钱包模块错误信息 */
        WalletOpened  {
            public int getCode() {
                return 30027;
            }
        },
        WalletUnOpened  {
            public int getCode() {
                return 30028;
            }
        },
        WalletOpenFail  {
            public int getCode() {
                return 30029;
            }
        },
        /* 名片错误信息 */
        CardListAddExist  {
            public int getCode() {
                return 30030;
            }
        },
        CardListMemoEmpty {
            public int getCode() {
                return 30031;
            }
        },
        CardListAddCantSelf {
            public int getCode() {
                return 30032;
            }
        }
        /*实名认证模块*/
        ,
        CertificationFail {
            public int getCode() {
                return 30033;
            }
        },
        CertifiNameEmpty {
            public int getCode() {
                return 30034;
            }
        },
        CertifiIDCardEmpty {
            public int getCode() {
                return 30035;
            }
        },
        CertifiPictureEmpty {
            public int getCode() {
                return 30036;
            }
        },
        CertificationIDCardPassed {
            public int getCode() {
                return 30037;
            }
        },
        Certificationing {
            public int getCode() {
                return 30038;
            }
        },
        CertificationPictureUnExist {
            public int getCode() {
                return 30039;
            }
        },
        CertificationResultQueryFail {
            public int getCode() {
                return 30040;
            }
        },
        CertificationUnPass {
            public int getCode() {
                return 30041;
            }
        },
        ErrorLogContentEmpty {
            public int getCode() {
                return 30042;
            }
        },
        ReceiveSuccess {
            public int getCode() {
                return 30043;
            }
        },
        FeebackContentEmpty {
            public int getCode() {
                return 30044;
            }
        },
        FeebackIDEmpty {
            public int getCode() {
                return 30045;
            }
        },
        FeebackContentToLong {
            public int getCode() {
                return 30046;
            }
        },
        IndustryNoData {
            public int getCode() {
                return 30047;
            }
        },
        UserNameEmpty {
            public int getCode() {
                return 30048;
            }
        },
        PositionContentEmpty {
            public int getCode() {
                return 30049;
            }
        },
        PositionParentEmpty {
            public int getCode() {
                return 30050;
            }
        },
        KeyWordEmpty {
            public int getCode() {
                return 30051;
            }
        },
        LocalFail {
            public int getCode() {
                return 30052;
            }
        },
        OrderUnExist {
            public int getCode() {
                return 30053;
            }
        },
        OrderProcing {
            public int getCode() {
                return 30054;
            }
        },
        UserBuy {
            public int getCode() {
                return 30055;
            }
        },
        OrderToSelf {
            public int getCode() {
                return 30056;
            }
        },
         OrderOperateFail{
            public int getCode() {
                return 30057;
            }
        },
        OrderCancel {
            public int getCode() {
                return 30058;
            }
        },
        OrderReceiveFail {
            public int getCode() {
                return 30059;
            }
        },
        OrderCurrentDoing {
            public int getCode() {
                return 30060;
            }
        },
        OrderFinished {
            public int getCode() {
                return 30061;
            }
        },
        OrderCancelFail {
            public int getCode() {
                return 30062;
            }
        },
        OrderCancelResonEmpty {
            public int getCode() {
                return 30063;
            }
        },
        OrderFinishFail {
            public int getCode() {
                return 30064;
            }
        },
        WalletCreditLow {
            public int getCode() {
                return 30065;
            }
        },
        InitialWalletFail {
            public int getCode() {
                return 30066;
            }
        },
        OrderPayed {
            public int getCode() {
                return 30067;
            }
        },
        OrderDeleteFail {
            public int getCode() {
                return 30068;
            }
        },
        OrderRefundFail {
            public int getCode() {
                return 30069;
            }
        },
        PositionNoData {
            public int getCode() {
                return 30070;
            }
        },
        PushConfigFail {
            public int getCode() {
                return 30071;
            }
        },
        PushDeviceEmpty {
            public int getCode() {
                return 30072;
            }
        },
        PushDeviceTypeEmpty {
            public int getCode() {
                return 30073;
            }
        },
        PushOperateEmpty {
            public int getCode() {
                return 30074;
            }
        },
        PushChannelEmpty {
            public int getCode() {
                return 30075;
            }
        },
        PushTokenEmpty {
            public int getCode() {
                return 30076;
            }
        } ,
        PhoneEmpty {
            public int getCode() {
                return 30077;
            }
        }
        ,
        PhoneForamtError {
            public int getCode() {
                return 30078;
            }
        }
        ,
        PhoneDiabled {
            public int getCode() {
                return 30079;
            }
        }
        ,
        SendOften {
            public int getCode() {
                return 30080;
            }
        }
        ,
        SendSuccess {
            public int getCode() {
                return 30081;
            }
        }
        ,
        VerificationFail {
            public int getCode() {
                return 30082;
            }
        }
        ,
        PayPasswdEmpty {
            public int getCode() {
                return 30083;
            }
        },
        VerificationOverdue {
            public int getCode() {
                return 30084;
            }
        },
        VerificationError {
            public int getCode() {
                return 30085;
            }
        },
        GetVerification {
            public int getCode() {
                return 30086;
            }
        },
        PhoneAlreadBind {
            public int getCode() {
                return 30087;
            }
        },
        PhoneBindUnDiffer {
            public int getCode() {
                return 30088;
            }
        },
        LoginFail {
            public int getCode() {
                return 30089;
            }
        },
        LoginSuccess {
            public int getCode() {
                return 30090;
            }
        },
        LoginOutFail {
            public int getCode() {
                return 30091;
            }
        },
        LoginOutSuccess {
            public int getCode() {
                return 30092;
            }
        },
        UnLogin {
            public int getCode() {
                return 30093;
            }
        },
        UserImageEmpty {
            public int getCode() {
                return 30094;
            }
        },
        ImageDeleteFail {
            public int getCode() {
                return 30095;
            }
        },
        ImageModifyFail {
            public int getCode() {
                return 30096;
            }
        },
        LatlngModifyFail {
            public int getCode() {
                return 30097;
            }
        },
        BindWeChatFail {
            public int getCode() {
                return 30098;
            }
        },
        WeChatAlreadBind {
            public int getCode() {
                return 30099;
            }
        },
        ReportResonEmpty {
            public int getCode() {
                return 30100;
            }
        },
        VerificationEmpty {
            public int getCode() {
                return 30101;
            }
        },
        AccountEqSelf {
            public int getCode() {
                return 30102;
            }
        },
        BindPhoneFail {
            public int getCode() {
                return 30103;
            }
        },
        ParamUserEmpty {
            public int getCode() {
                return 30104;
            }
        },
        UserUnBind {
            public int getCode() {
                return 30105;
            }
        },
        FineMoneyToSmall {
            public int getCode() {
                return 30106;
            }
        },
        FineContentEmpty {
            public int getCode() {
                return 30107;
            }
        },
        FineMoneyMustSmallBalance {
            public int getCode() {
                return 30108;
            }
        },
        VerificationPass {
            public int getCode() {
                return 30109;
            }
        },
        UnSetPayPassWd {
            public int getCode() {
                return 30110;
            }
        },
        SetPayPassWdFail {
            public int getCode() {
                return 30111;
            }
        },
        RemoveBindMethodFail {
            public int getCode() {
                return 30112;
            }
        },
        RemoveBindMethodSuccess {
            public int getCode() {
                return 30113;
            }
        },
        PayMethodUnExist {
            public int getCode() {
                return 30114;
            }
        },
        DefaultPaySetFail {
            public int getCode() {
                return 30115;
            }
        },
        PayMethodFail {
            public int getCode() {
                return 30116;
            }
        },
        PayAccoundAlreadBind {
            public int getCode() {
                return 30117;
            }
        },
        PayAccountEmpty {
            public int getCode() {
                return 30118;
            }
        },
        WithdrawFail {
            public int getCode() {
                return 30119;
            }
        },
        CreditToSmall{
            public int getCode() {
                return 30120;
            }
        },
        WithdrawSmall{
            public int getCode() {
                return 30121;
            }
        },
        OtherLogin{
            public int getCode() {
                return 30122;
            }
        },
        PushTitleEmpty{
            public int getCode() {
                return 30123;
            }
        },
        PushContentEmpty{
            public int getCode() {
                return 30124;
            }
        },
        VersionNumberEmpty{
            public int getCode() {
                return 30125;
            }
        },
        VersionNoteEmpty{
            public int getCode() {
                return 30126;
            }
        },
        AdminNameEmpty{
            public int getCode() {
                return 30127;
            }
        },
        AdminPhoneEmpty{
            public int getCode() {
                return 30128;
            }
        },
        AdminNickNameEmpty{
            public int getCode() {
                return 30129;
            }
        },
        AdminPasswordsNotConsistent {
            public int getCode() {
                return 30130;
            }
        },
        AdminLoginError {
            public int getCode() {
                return 30131;
            }
        },
        MemoEmpty {
            public int getCode() {
                return 30131;
            }
        },
        SendNum_limit{
            public int getCode() {
                return 30132;
            }
        },

        ReSumeTitleEmpty{
            public int getCode() {
                return 30134;
            }
        },
        CurrentVersionExist{
            public int getCode() {
                return 30135;
            }
        },

        WaitPayOrder{
            public int getCode() {
                return 30137;
            }
        },
        ActivityTitleEmpty{
            public int getCode() {
                return 30138;
            }
        },
        ActivityContentEmpty{
            public int getCode() {
                return 30139;
            }
        },
        ActivityUrlEmpty{
            public int getCode() {
                return 30140;
            }
        },
        ActivityPictureEmpty{
            public int getCode() {
                return 30141;
            }
        };
        public abstract int getCode();
    }
	
}
