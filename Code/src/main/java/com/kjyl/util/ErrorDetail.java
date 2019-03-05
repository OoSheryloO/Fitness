package com.kjyl.util;

/**
 * Created by MeetLucky on 16/5/23.
 */
public class ErrorDetail {
    private static ErrorDetail instance;

    public static ErrorDetail sharedInstance() {
        if (instance == null) {
            instance = new ErrorDetail();
        }
        return instance;
    }
    
    public static String getAuthCertificationError(String errorCode){
        String error = "";
        if (errorCode == "300101") {
            error = "用户请求过期";
        } else if (errorCode == "300102") {
            error = "用户日调用量超限";
        } else if (errorCode == "300103") {
            error = "服务每秒调用量超限";
        } else if (errorCode == "300104") {
            error = "服务日调用量超限";
        } else if (errorCode == "300202") {
            error = "请求缺少apikey，登录即可获取";
        } else if (errorCode == "300203") {
            error = "服务没有取到apikey或secretkey";
        } else if (errorCode == "300204") {
            error = "apikey不存在";
        } else if (errorCode == "300205") {
            error = "api不存在";
        } else if (errorCode == "300206") {
            error = "api已关闭服务";
        } else if (errorCode == "300207") {
            error = "余额不足，请充值";
        } else if (errorCode == "300208") {
            error = "未通过实名验证";
        } else if (errorCode == "300209") {
            error = "服务商响应status非200";
        } else if (errorCode == "300301") {
            error = "内部错误";
        } else if (errorCode == "300302") {
            error = "系统繁忙稍候再试";
        } else if (errorCode == "-1") {
            error = "填写的证件号或卡号有误";
        } else if (errorCode == "-2") {
            error = "未找到身份证号码信息";
        } else {
            error = "实名认证失败,未知原因";
        }
        
        return error;
    }

    
    /**
     * 微信支付的银行卡信息
     * @param type
     * @return
     */
    public static String getWeChatPayBankType(String type){
        String bank = "";
        if (type == "ICBC_DEBIT") {
            bank = "工商银行(借记卡)";
        } else if (type == "ICBC_CREDIT") {
            bank = "工商银行(信用卡)";
        } else if (type == "ABC_DEBIT") {
            bank = "农业银行(借记卡)";
        } else if (type == "ABC_CREDIT") {
            bank = "农业银行(信用卡)";
        } else if (type == "PSBC_DEBIT") {
            bank = "邮政储蓄银行(借记卡)";
        } else if (type == "PSBC_CREDIT") {
            bank = "邮政储蓄银行(信用卡)";
        } else if (type == "CCB_DEBIT") {
            bank = "建设银行(借记卡)";
        } else if (type == "CCB_CREDIT") {
            bank = "建设银行(信用卡)";
        } else if (type == "CMB_DEBIT") {
            bank = "招商银行(借记卡)";
        } else if (type == "CMB_CREDIT") {
            bank = "招商银行(信用卡)";
        } else if (type == "BOC_DEBIT") {
            bank = "中国银行(借记卡)";
        } else if (type == "BOC_CREDIT") {
            bank = "中国银行(信用卡)";
        } else if (type == "COMM_DEBIT") {
            bank = "交通银行(借记卡)";
        } else if (type == "SPDB_DEBIT") {
            bank = "浦发银行(借记卡)";
        } else if (type == "SPDB_CREDIT") {
            bank = "浦发银行(信用卡)";
        } else if (type == "GDB_DEBIT") {
            bank = "广发银行(借记卡)";
        } else if (type == "GDB_CREDIT") {
            bank = "广发银行(信用卡)";
        } else if (type == "CMBC_DEBIT") {
            bank = "民生银行(借记卡)";
        } else if (type == "CMBC_CREDIT") {
            bank = "民生银行(信用卡)";
        } else if (type == "PAB_DEBIT") {
            bank = "平安银行(借记卡)";
        } else if (type == "PAB_CREDIT") {
            bank = "平安银行(信用卡)";
        } else if (type == "CEB_DEBIT") {
            bank = "光大银行(借记卡)";
        } else if (type == "CEB_CREDIT") {
            bank = "光大银行(信用卡)";
        } else if (type == "CIB_DEBIT") {
            bank = "兴业银行(借记卡)";
        } else if (type == "CIB_CREDIT") {
            bank = "兴业银行(信用卡)";
        } else if (type == "CITIC_DEBIT") {
            bank = "中信银行(借记卡)";
        } else if (type == "CITIC_CREDIT") {
            bank = "中信银行(信用卡)";
        } else if (type == "BOSH_DEBIT") {
            bank = "上海银行(借记卡)";
        } else if (type == "BOSH_CREDIT") {
            bank = "上海银行(信用卡)";
        } else if (type == "CRB_DEBIT") {
            bank = "华润银行(借记卡)";
        } else if (type == "HZB_DEBIT") {
            bank = "杭州银行(借记卡)";
        } else if (type == "HZB_CREDIT") {
            bank = "杭州银行(信用卡)";
        } else if (type == "BSB_DEBIT") {
            bank = "包商银行(借记卡)";
        } else if (type == "BSB_CREDIT") {
            bank = "包商银行(信用卡)";
        } else if (type == "CQB_DEBIT") {
            bank = "重庆银行(借记卡)";
        } else if (type == "SDEB_DEBIT") {
            bank = "顺德农商行(借记卡)";
        } else if (type == "SZRCB_DEBIT") {
            bank = "深圳农商银行(借记卡)";
        } else if (type == "HRBB_DEBIT") {
            bank = "哈尔滨银行(借记卡)";
        } else if (type == "BOCD_DEBIT") {
            bank = "成都银行(借记卡)";
        } else if (type == "GDNYB_DEBIT") {
            bank = "南粤银行(借记卡)";
        } else if (type == "GDNYB_CREDIT") {
            bank = "南粤银行(信用卡)";
        } else if (type == "GZCB_DEBIT") {
            bank = "广州银行(借记卡)";
        } else if (type == "GZCB_CREDIT") {
            bank = "广州银行(信用卡)";
        } else if (type == "JSB_DEBIT") {
            bank = "江苏银行(借记卡)";
        } else if (type == "JSB_CREDIT") {
            bank = "江苏银行(信用卡)";
        } else if (type == "NBCB_DEBIT") {
            bank = "宁波银行(借记卡)";
        } else if (type == "NBCB_CREDIT") {
            bank = "宁波银行(信用卡)";
        } else if (type == "NJCB_DEBIT") {
            bank = "南京银行(借记卡)";
        } else if (type == "JZB_DEBIT") {
            bank = "晋中银行(借记卡)";
        } else if (type == "KRCB_DEBIT") {
            bank = "昆山农商(借记卡)";
        } else if (type == "LJB_DEBIT") {
            bank = "龙江银行(借记卡)";
        } else if (type == "LNNX_DEBIT") {
            bank = "辽宁农信(借记卡)";
        } else if (type == "LZB_DEBIT") {
            bank = "兰州银行(借记卡)";
        } else if (type == "WRCB_DEBIT") {
            bank = "无锡农商(借记卡)";
        } else if (type == "ZYB_DEBIT") {
            bank = "中原银行(借记卡)";
        } else if (type == "ZJRCUB_DEBIT") {
            bank = "浙江农信(借记卡)";
        } else if (type == "WZB_DEBIT") {
            bank = "温州银行(借记卡)";
        } else if (type == "XAB_DEBIT") {
            bank = "西安银行(借记卡)";
        } else if (type == "JXNXB_DEBIT") {
            bank = "江西农信(借记卡)";
        } else if (type == "NCB_DEBIT") {
            bank = "宁波通商银行(借记卡)";
        } else if (type == "NYCCB_DEBIT") {
            bank = "南阳村镇银行(借记卡)";
        } else if (type == "NMGNX_DEBIT") {
            bank = "内蒙古农信(借记卡)";
        } else if (type == "SXXH_DEBIT") {
            bank = "陕西信合(借记卡)";
        } else if (type == "SRCB_CREDIT") {
            bank = "上海农商银行(信用卡)";
        } else if (type == "SJB_DEBIT") {
            bank = "盛京银行(借记卡)";
        } else if (type == "SDRCU_DEBIT") {
            bank = "山东农信(借记卡)";
        } else if (type == "SRCB_DEBIT") {
            bank = "上海农商银行(借记卡)";
        } else if (type == "SCNX_DEBIT") {
            bank = "四川农信(借记卡)";
        } else if (type == "QLB_DEBIT") {
            bank = "齐鲁银行(借记卡)";
        } else if (type == "QDCCB_DEBIT") {
            bank = "青岛银行(借记卡)";
        } else if (type == "PZHCCB_DEBIT") {
            bank = "攀枝花银行(借记卡)";
        } else if (type == "ZJTLCB_DEBIT") {
            bank = "浙江泰隆银行(借记卡)";
        } else if (type == "TJBHB_DEBIT") {
            bank = "天津滨海农商行(借记卡)";
        } else if (type == "WEB_DEBIT") {
            bank = "微众银行(借记卡)";
        } else if (type == "YNRCCB_DEBIT") {
            bank = "云南农信(借记卡)";
        } else if (type == "WFB_DEBIT") {
            bank = "潍坊银行(借记卡)";
        } else if (type == "WHRC_DEBIT") {
            bank = "武汉农商行(借记卡)";
        } else if (type == "ORDOSB_DEBIT") {
            bank = "鄂尔多斯银行(借记卡)";
        } else if (type == "XJRCCB_DEBIT") {
            bank = "新疆农信银行(借记卡)";
        } else if (type == "ORDOSB_CREDIT") {
            bank = "鄂尔多斯银行(信用卡)";
        } else if (type == "CSRCB_DEBIT") {
            bank = "常熟农商银行(借记卡)";
        } else if (type == "JSNX_DEBIT") {
            bank = "江苏农商行(借记卡)";
        } else if (type == "GRCB_CREDIT") {
            bank = "广州农商银行(信用卡)";
        } else if (type == "GLB_DEBIT") {
            bank = "桂林银行(借记卡)";
        } else if (type == "GDRCU_DEBIT") {
            bank = "广东农信银行(借记卡)";
        } else if (type == "GDHX_DEBIT") {
            bank = "广东华兴银行(借记卡)";
        } else if (type == "FJNX_DEBIT") {
            bank = "福建农信银行(借记卡)";
        } else if (type == "DYCCB_DEBIT") {
            bank = "德阳银行(借记卡)";
        } else if (type == "DRCB_DEBIT") {
            bank = "东莞农商行(借记卡)";
        } else if (type == "CZCB_DEBIT") {
            bank = "稠州银行(借记卡)";
        } else if (type == "CZB_DEBIT") {
            bank = "浙商银行(借记卡)";
        } else if (type == "CZB_CREDIT") {
            bank = "浙商银行(信用卡)";
        } else if (type == "GRCB_DEBIT") {
            bank = "广州农商银行(借记卡)";
        } else if (type == "CSCB_DEBIT") {
            bank = "长沙银行(借记卡)";
        } else if (type == "CQRCB_DEBIT") {
            bank = "重庆农商银行(借记卡)";
        } else if (type == "CBHB_DEBIT") {
            bank = "渤海银行(借记卡)";
        } else if (type == "BOIMCB_DEBIT") {
            bank = "内蒙古银行(借记卡)";
        } else if (type == "BOD_DEBIT") {
            bank = "东莞银行(借记卡)";
        } else if (type == "BOD_CREDIT") {
            bank = "东莞银行(信用卡)";
        } else if (type == "BOB_DEBIT") {
            bank = "北京银行(借记卡)";
        } else if (type == "BNC_DEBIT") {
            bank = "江西银行(借记卡)";
        } else if (type == "BJRCB_DEBIT") {
            bank = "北京农商行(借记卡)";
        } else if (type == "AE_CREDIT") {
            bank = "AE(信用卡)";
        } else if (type == "GYCB_CREDIT") {
            bank = "贵阳银行(信用卡)";
        } else if (type == "JSHB_DEBIT") {
            bank = "晋商银行(借记卡)";
        } else if (type == "JRCB_DEBIT") {
            bank = "江阴农商行(借记卡)";
        } else if (type == "JNRCB_DEBIT") {
            bank = "江南农商(借记卡)";
        } else if (type == "JLNX_DEBIT") {
            bank = "吉林农信(借记卡)";
        } else if (type == "JLB_DEBIT") {
            bank = "吉林银行(借记卡)";
        } else if (type == "JJCCB_DEBIT") {
            bank = "九江银行(借记卡)";
        } else if (type == "HXB_DEBIT") {
            bank = "华夏银行(借记卡)";
        } else if (type == "HXB_CREDIT") {
            bank = "华夏银行(信用卡)";
        } else if (type == "HUNNX_DEBIT") {
            bank = "湖南农信(借记卡)";
        } else if (type == "HSB_DEBIT") {
            bank = "徽商银行(借记卡)";
        } else if (type == "HSBC_DEBIT") {
            bank = "恒生银行(借记卡)";
        } else if (type == "HRXJB_DEBIT") {
            bank = "华融湘江银行(借记卡)";
        } else if (type == "HNNX_DEBIT") {
            bank = "河南农信(借记卡)";
        } else if (type == "HKBEA_DEBIT") {
            bank = "东亚银行(借记卡)";
        } else if (type == "HEBNX_DEBIT") {
            bank = "河北农信(借记卡)";
        } else if (type == "HBNX_DEBIT") {
            bank = "湖北农信(借记卡)";
        } else if (type == "HBNX_CREDIT") {
            bank = "湖北农信(信用卡)";
        } else if (type == "GYCB_DEBIT") {
            bank = "贵阳银行(借记卡)";
        } else if (type == "GSNX_DEBIT") {
            bank = "甘肃农信(借记卡)";
        } else if (type == "JCB_CREDIT") {
            bank = "JCB(信用卡)";
        } else if (type == "MASTERCARD_CREDIT") {
            bank = "MASTERCARD(信用卡)";
        } else if (type == "VISA_CREDIT") {
            bank = "VISA(信用卡)";
        } else {
            bank = "微信支付";
        }

        return bank;
    }

    
    public static String ErrorMessage(ConstantUtils.ErrorMessageType emType){
        String ErrorMessageString="";
        if(emType.getCode()==ConstantUtils.ErrorMessageType.AccountStop.getCode()){
            ErrorMessageString="该帐号已经被系统停用";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.AccountDisabled.getCode()){
            ErrorMessageString="该帐号已经被系统禁用";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.Parameter.getCode()){
            ErrorMessageString="提交的参数不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.OtherError.getCode()){
            ErrorMessageString="系统发生未知错误";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.JsonParseError.getCode()){
            ErrorMessageString="Json解析错误,请检查JSON是否正确";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.ProcessSuccess.getCode()){
            ErrorMessageString="处理成功";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.ProcessFail.getCode()){
            ErrorMessageString="处理失败";//Authorized  AccountUnPassAuth
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.Authorized.getCode()){
            ErrorMessageString="用户未能通过认证";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.ParamEmpty.getCode()){
            ErrorMessageString="请求参数不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.UnKownError.getCode()){
            ErrorMessageString="系统发生未知错误";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.InterfaceDisabled.getCode()){
            ErrorMessageString="您当前的版本不再支持该功能,请尽快升级!";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.UserNoExist.getCode()){
            ErrorMessageString="用户不存在";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.AddressUnExist.getCode()){
            ErrorMessageString="地址不存在";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.CityEmpt.getCode()){
            ErrorMessageString="城市不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.AreaEmpty.getCode()){
            ErrorMessageString="区域不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.DetailEmpty.getCode()){
            ErrorMessageString="详细地址不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.LatlngEmpty.getCode()){
            ErrorMessageString="经纬度不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.AddressSaveFail.getCode()){
            ErrorMessageString="地址保存失败";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.DefaultAddressFail.getCode()){
            ErrorMessageString="设置默认地址失败";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.DeleteAddressFail.getCode()){
            ErrorMessageString="地址删除失败";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.CollectionAddressFail.getCode()){
            ErrorMessageString="收藏地址失败";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.AddressExist.getCode()){
            ErrorMessageString="该地址已经保存了";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.WalletOpened.getCode()){
            ErrorMessageString="钱包功能已经开通";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.WalletUnOpened.getCode()){
            ErrorMessageString="钱包未开通";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.WalletOpenFail.getCode()){
            ErrorMessageString="开通钱包失败";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.CardListAddExist.getCode()){
            ErrorMessageString="已经是交换名片了";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.DeleteSuccess.getCode()){
            ErrorMessageString="删除成功";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.DeleteFail.getCode()){
            ErrorMessageString="删除失败";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.ModifuFail.getCode()){
            ErrorMessageString="修改失败";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.ModifySuccess.getCode()){
            ErrorMessageString="修改成功";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.RecordUnExist.getCode()){
            ErrorMessageString="该记录不存在";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.CardListMemoEmpty.getCode()){
            ErrorMessageString="备注不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.AddFail.getCode()){
            ErrorMessageString="添加失败";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.CardListAddCantSelf.getCode()){
            ErrorMessageString="您不能添加自己";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.CertificationFail.getCode()){
            ErrorMessageString="实名认证失败";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.CertifiNameEmpty.getCode()){
            ErrorMessageString="真实姓名不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.CertifiIDCardEmpty.getCode()){
            ErrorMessageString="身份证号码不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.CertifiPictureEmpty.getCode()){
            ErrorMessageString="身份证正面照或者反面照不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.CertificationIDCardPassed.getCode()){
            ErrorMessageString="该证件已经被实名认证过";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.Certificationing.getCode()){
            ErrorMessageString="审核结果将在三个工作日内通知";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.CertificationPictureUnExist.getCode()){
            ErrorMessageString="上传认证图片不存在";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.CertificationResultQueryFail.getCode()){
            ErrorMessageString="查询审核结果失败";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.CertificationUnPass.getCode()){
            ErrorMessageString="用户未进行实名审核";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.ErrorLogContentEmpty.getCode()){
            ErrorMessageString="错误内容不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.FeebackContentEmpty.getCode()){
            ErrorMessageString="反馈内容不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.FeebackIDEmpty.getCode()){
            ErrorMessageString="反馈编号不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.ReceiveSuccess.getCode()){
            ErrorMessageString="感谢您的反馈意见";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.FeebackContentToLong.getCode()){
            ErrorMessageString="反馈内容请小于200个字符";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.IndustryNoData.getCode()){
            ErrorMessageString="暂无行业类别数据";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.UserNameEmpty.getCode()){
            ErrorMessageString="姓名不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.PositionContentEmpty.getCode()){
            ErrorMessageString="岗位内容不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.PositionParentEmpty.getCode()){
            ErrorMessageString="父岗位不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.KeyWordEmpty.getCode()){
            ErrorMessageString="关键词不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.LocalFail.getCode()){
            ErrorMessageString="获取定位信息失败";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.OrderUnExist.getCode()){
            ErrorMessageString="该订单已失效";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.OrderProcing.getCode()){
            ErrorMessageString="当前已经有订单在进行中了";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.UserBuy.getCode()){
            ErrorMessageString="对方正忙无法接受您的委托申请";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.OrderToSelf.getCode()){
            ErrorMessageString="不能以自己为目标托的对象";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.OrderOperateFail.getCode()){
            ErrorMessageString="服务器正忙稍后再试";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.OrderCancel.getCode()){
            ErrorMessageString="订单已取消";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.OrderReceiveFail.getCode()){
            ErrorMessageString="订单已被抢";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.OrderCurrentDoing.getCode()){
            ErrorMessageString="订单已经进行中了";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.OrderFinished.getCode()){
            ErrorMessageString="订单已完成";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.OrderCancelFail.getCode()){
            ErrorMessageString="对方已确认完成,取消失败";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.OrderCancelResonEmpty.getCode()){
            ErrorMessageString="请输入取消原因";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.OrderFinishFail.getCode()){
            ErrorMessageString="对方已取消订单,操作失败";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.WalletCreditLow.getCode()){
            ErrorMessageString="钱包余额不足";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.InitialWalletFail.getCode()){
            ErrorMessageString="初始化钱包失败";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.OrderPayed.getCode()){
            ErrorMessageString="订单已付款";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.OrderDeleteFail.getCode()){
            ErrorMessageString="该订单正在进行中,无法删除";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.OrderRefundFail.getCode()){
            ErrorMessageString="该订单未进行付款,无法退款";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.PositionNoData.getCode()){
            ErrorMessageString="暂无职位类别数据";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.PushConfigFail.getCode()){
            ErrorMessageString="配置推送信息失败";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.PushDeviceEmpty.getCode()){
            ErrorMessageString="设备名称不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.PushDeviceTypeEmpty.getCode()){
            ErrorMessageString="设备型号不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.PushOperateEmpty.getCode()){
            ErrorMessageString="操作系统不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.PushChannelEmpty.getCode()){
            ErrorMessageString="通道编号不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.PushTokenEmpty.getCode()){
            ErrorMessageString="设备令牌不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.PhoneEmpty.getCode()){
            ErrorMessageString="手机号码不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.PhoneForamtError.getCode()){
            ErrorMessageString="号码格式不正确";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.PhoneDiabled.getCode()){
            ErrorMessageString="该手机已经被禁用登录";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.SendOften.getCode()){
            ErrorMessageString="验证码发送过于频繁,请30秒后重试";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.SendSuccess.getCode()){
            ErrorMessageString="验证码已发送";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.VerificationFail.getCode()){
            ErrorMessageString="获取验证码失败";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.PayPasswdEmpty.getCode()){
            ErrorMessageString="支付密码为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.VerificationOverdue.getCode()){
            ErrorMessageString="验证码已经过期";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.VerificationError.getCode()){
            ErrorMessageString="验证码不匹配";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.GetVerification.getCode()){
            ErrorMessageString="请先获取验证码";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.PhoneAlreadBind.getCode()){
            ErrorMessageString="该手机号码已经被绑定";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.PhoneBindUnDiffer.getCode()){
            ErrorMessageString="该微信号已经和其他手机号码绑定";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.LoginFail.getCode()){
            ErrorMessageString="登录失败";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.LoginSuccess.getCode()){
            ErrorMessageString="登录成功";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.LoginOutFail.getCode()){
            ErrorMessageString="用户注销失败";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.LoginSuccess.getCode()){
            ErrorMessageString="用户注销成功";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.UnLogin.getCode()){
            ErrorMessageString="用户未登录";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.UserImageEmpty.getCode()){
            ErrorMessageString="用户头像不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.ImageDeleteFail.getCode()){
            ErrorMessageString="删除头像失败";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.ImageModifyFail.getCode()){
            ErrorMessageString="修改头像失败";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.LatlngModifyFail.getCode()){
            ErrorMessageString="经纬度更新失败";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.BindWeChatFail.getCode()){
            ErrorMessageString="绑定微信失败";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.WeChatAlreadBind.getCode()){
            ErrorMessageString="用户已经绑定微信";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.ReportResonEmpty.getCode()){
            ErrorMessageString="投诉原由为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.VerificationEmpty.getCode()){
            ErrorMessageString="验证码不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.AccountEqSelf.getCode()){
            ErrorMessageString="和当前账号一致";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.BindPhoneFail.getCode()){
            ErrorMessageString="绑定手机号码失败";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.ParamUserEmpty.getCode()){
            ErrorMessageString="参数user不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.UserUnBind.getCode()){
            ErrorMessageString="用户未绑定微信";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.FineMoneyToSmall.getCode()){
            ErrorMessageString="处罚金额必须大于0";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.FineContentEmpty.getCode()){
            ErrorMessageString="处罚理由不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.FineMoneyMustSmallBalance.getCode()){
            ErrorMessageString="处罚金额必须小于对方账户余额";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.VerificationPass.getCode()){
            ErrorMessageString="验证通过";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.UnSetPayPassWd.getCode()){
            ErrorMessageString="未设置支付密码";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.SetPayPassWdFail.getCode()){
            ErrorMessageString="设置支付密码失败";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.RemoveBindMethodFail.getCode()){
            ErrorMessageString="解绑失败";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.RemoveBindMethodSuccess.getCode()){
            ErrorMessageString="解绑成功";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.PayMethodUnExist.getCode()){
            ErrorMessageString="付款方式不存在";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.DefaultPaySetFail.getCode()){
            ErrorMessageString="设置默认失败";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.PayMethodFail.getCode()){
            ErrorMessageString="添加付款方式失败";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.PayAccoundAlreadBind.getCode()){
            ErrorMessageString="该付款账户已经绑定";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.PayAccountEmpty.getCode()){
            ErrorMessageString="账户不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.WithdrawFail.getCode()){
            ErrorMessageString="提现失败";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.CreditToSmall.getCode()){
            ErrorMessageString="余额不足,提现失败";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.WithdrawSmall.getCode()){
            ErrorMessageString="最少提现金额为50元";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.OtherLogin.getCode()){
            ErrorMessageString="您的帐号已在其他设备上登录,请重新登录";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.PushTitleEmpty.getCode()){
            ErrorMessageString="推送的标题不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.PushContentEmpty.getCode()){
            ErrorMessageString="推送的内容不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.VersionNumberEmpty.getCode()){
            ErrorMessageString="版本号不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.VersionNoteEmpty.getCode()){
            ErrorMessageString="更新内容不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.AdminNameEmpty.getCode()){
            ErrorMessageString="帐号不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.AdminPhoneEmpty.getCode()){
            ErrorMessageString="手机号码不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.AdminNickNameEmpty.getCode()){
            ErrorMessageString="昵称不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.AdminPasswordsNotConsistent.getCode()){
            ErrorMessageString="密码不正确";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.AdminLoginError.getCode()){
            ErrorMessageString="帐号或者密码错误";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.MemoEmpty.getCode()){
            ErrorMessageString="个性签名不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.SendNum_limit.getCode()){
            ErrorMessageString="每日只能提现一次";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.NoMore.getCode()){
            ErrorMessageString="没有更多的数据";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.ReSumeTitleEmpty.getCode()){
            ErrorMessageString="简历标题不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.CurrentVersionExist.getCode()){
            ErrorMessageString="当前版本已经存在";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.UnSupportCurrentVersion.getCode()){
            ErrorMessageString="当前版本不支持该功能";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.WaitPayOrder.getCode()){
            ErrorMessageString="当前存在未付款的订单,请您先付款";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.ActivityTitleEmpty.getCode()){
            ErrorMessageString="活动标题不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.ActivityContentEmpty.getCode()){
            ErrorMessageString="活动内容不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.ActivityUrlEmpty.getCode()){
            ErrorMessageString="活动链接不能为空";
        }else if(emType.getCode()==ConstantUtils.ErrorMessageType.ActivityPictureEmpty.getCode()){
            ErrorMessageString="请先上传活动图片";
        }
        return ErrorMessageString;
    }
    
    /**
     * 检测用户的禁用期是否已经过去了
     * @param findUser
     * @param userService
     * @return
     */
//    public static Map<String, Object> CheckUserIsDisabled(User findUser, UserService userService){
//        Map<String, Object> result = null;
//        if(findUser.getUserStatus()==ConstantUtils.UserStatus.Disabled.getCode()){
//            if(findUser.getUserLimittime().equals("-1")){
//                result = ResultUtil.sharedInstance().otherError(ConstantUtils.ErrorMessageType.AccountDisabled,null);
//            }else{
////                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				Date d1 = new Date();
//				Date d2 = findUser.getUserLimittime();
//				long diff = d2.getTime() - d1.getTime();
//				if(diff>0) {
//				    long day = diff / (24 * 60 * 60 * 1000);
//				    long hour = (diff / (60 * 60 * 1000) - day * 24);
//				    long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
//				    long s = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
//				    String limitTime="";
//				    if(day>0) {
//				        limitTime = day + "天" + hour + "小时" + min + "分" + s + "秒";
//				    }else if(hour>0){
//				        limitTime = hour + "小时" + min + "分" + s + "秒";
//				    } else if(min>0){
//				        limitTime = min + "分" + s + "秒";
//				    }else if(s>0){
//				        limitTime = s + "秒";
//				    }
//				    result = ResultUtil.sharedInstance().otherErrorCusomer(ErrorMessage(ConstantUtils.ErrorMessageType.AccountDisabled)+"，"+limitTime+"后解除限制",null);
//				}else{
//				    findUser.setUserStatus(ConstantUtils.UserStatus.Enable.getCode());
//				    userService.changeUser(findUser);
//				}
//            }
//        }else if(findUser.getUserStatus()==ConstantUtils.UserStatus.Invalid.getCode()){
//            result = ResultUtil.sharedInstance().otherError(ConstantUtils.ErrorMessageType.AccountDisabled, null);
//        }else if(findUser.getUserStatus()==ConstantUtils.UserStatus.Previous.getCode()){
//            result = ResultUtil.sharedInstance().otherError(ConstantUtils.ErrorMessageType.AccountStop, null);
//        }
//        return result;
//    }

}
