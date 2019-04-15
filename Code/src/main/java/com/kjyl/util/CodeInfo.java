package com.kjyl.util;

/**
 * <p>Title: Info</p>
 * <p>Description: Return_Code </p>
 * <p>Company: </p>
 * @author Sheryl
 * @created 2017年9月21日 下午2:25:40
 */
public class CodeInfo {//http请求的头部不区分大小写，method区分大小写
	
	/**
	 * 返回文本常量
	 */
/** info：记录*/		public static final String sRowKey		= "row";
/** info：总共*/		public static final String sTotalKey	= "total";
/** info：代码*/		public static final String sCodeKey 	= "code";
/** info：数据*/		public static final String sDataKey		= "data";
/** info：信息*/		public static final String sTokenKey  = "token";
/** info：信息*/		public static final String sMessageKey  = "message";
/** info：签名*/		public static final String sSignKey  = "sign";
	
	/**
	 * 请求是否成功 -1/60000
	 */
	public enum Code {
		NO {//请求失败
			public int getCode() {
				return -1;
			}
		},
		OK {
			public int getCode() {
				return 200;
			}
		};
		public abstract int getCode();
	}
	
	/**
	 * 缺少参数问题 600**～
	 */
	public enum Missing_Parameter {
		Phone {//缺少手机号参数
			public int getCode() {
				return 60001;
			}
		},
		Captcha {//缺少验证码参数
			public int getCode() {
				return 60002;
			}
		},
		Identity {//缺少身份信息
			public int getCode() {
				return 60003;
			}
		},
		Wechar {//微信信息不全
			public int getCode() {
				return 60004;
			}
		},
		Dept {//信息不全
			public int getCode() {
				return 60005;
			}
		},
		PushDevice {//信息不全
			public int getCode() {
				return 60006;
			}
		},
		Code {//信息不全
			public int getCode() {
				return 60007;
			}
		};
		public abstract int getCode();
	}
	
	/**
	 * 格式化问题 60101～
	 */
	public enum Error_Format {
		Date {//时间格式错误
			public int getCode() {
				return 60101;
			}
		},
		Phone {//手机格式错误
			public int getCode() {
				return 60102;
			}
		};
		public abstract int getCode();
	}
	
	/**
	 * 使用个数问题 602**～
	 */
	public enum Error_USE_COUNT {
		Other_WeChar_Use {//他人微信使用
			public int getCode() {
				return 60201;
			}
		},
		OtherUser_Use {//他人使用
			public int getCode() {
				return 60202;
			}
		},
		MoreUser_Use {//多人用户使用
			public int getCode() {
				return 60208;
			}
		},
		Other_Teacher_Use {//其他老师使用
			public int getCode() {
				return 60209;
			}
		};
		public abstract int getCode();
	}
	
	/**
	 * 短信错误 603**～
	 */
	public enum Error_Phone_Number {
		False {//验证码不正确
			public int getCode() {
				return 60301;
			}
		},
		False_Matching {//匹配失败
			public int getCode() {
				return 60302;
			}
		},
		Ban_Phone {//手机号禁用
			public int getCode() {
				return 60303;
			}
		},
		Often_SMS {//发送验证码频繁，请稍候
			public int getCode() {
				return 60304;
			}
		},
		OutTime_SMS {//验证码已过期
			public int getCode() {
				return 60305;
			}
		};
		public abstract int getCode();
	}
	
	/**
	 * 登录状态 604**～
	 */
	public enum Error_Sign_in {
		False_Matching {//匹配失败
			public int getCode() {
				return 60401;
			}
		},
		False_Identification {//缺少身份认证
			public int getCode() {
				return 60402;
			}
		},
		Error_Identity {//身份错误
			public int getCode() {
				return 60403;
			}
		};
		public abstract int getCode();
	}
	
	/**
	 * 用户状态 609**～
	 */
	public enum Error_User_Status {
		Null {//空用户 即 用户不存在
			public int getCode() {
				return 60901;
			}
		},
		Identity {//身份错误 行长
			public int getCode() {
				return 60902;
			}
		};
		public abstract int getCode();
	}
	
	/**
	 * 参数问题 610**～
	 */
	public enum Error_Parameter {
		Default {//缺少参数
			public int getCode() {
				return 61001;
			}
		};
		public abstract int getCode();
	}
	
	/**
	 * 时间问题 611**～
	 */
	public enum Error_Time_Sequence {
		Default {//默认 时间先后错误问题
			public int getCode() {
				return 61101;
			}
		};
		public abstract int getCode();
	}
	
	/**
	 * 请求问题 699**～
	 */
	public enum Error_Request_Type {
		IncreaseFalse {// 增加失败
			public int getCode() {
				return 69901;
			}
		},
		RemoveFalse {// 删除失败
			public int getCode() {
				return 69902;
			}
		},
		ModifyFalse {// 修改失败
			public int getCode() {
				return 69903;
			}
		},
		SearchFalse {// 查询失败
			public int getCode() {
				return 69904;
			}
		};
		public abstract int getCode();
	}
	
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
        },
        Residue {   //  库存不
            public int getCode() {
                return 17;
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
