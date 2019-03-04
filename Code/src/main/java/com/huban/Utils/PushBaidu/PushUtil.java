package com.huban.Utils.PushBaidu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;
import com.baidu.yun.push.model.PushMsgToSingleDeviceResponse;
import com.huban.Utils.ArgumentsUtils;
import com.huban.Utils.ConstantUtils;
import com.huban.Utils.RandomUtil;
import com.huban.pojo.Devices;
import com.huban.pojo.PushDrives;
import com.huban.pojo.PushRecords;
import com.huban.service.DevicesService;
import com.huban.service.PushDrivesService;
import com.huban.service.PushRecordsService;
import com.huban.util.BaseUtil;
import com.huban.util.ConstantUtil;

/**
 * Created by MeetLucky on 16/6/26.
 */
public class PushUtil {
    private static PushUtil instance;
    public static PushUtil sharedInstance() {
        if (instance == null) {
            instance = new PushUtil();
        }
        return instance;
    }

    /**
     * 根据设备类别返回不同的百度推送认证信息
     *
     * @param typeCode 设备类型
     * @return 百度推送认证信息
     */
    public static BaiduPushClient pushClient(int typeCode) {
        PushKeyPair pair;
        String apiKey = ConstantUtil.BDPush_AndroidApiKey;
        String secretKey = ConstantUtil.BDPush_AndroidSecretKey;
        if(typeCode==BaseUtil.DeviceType.iOS.getCode()){
            apiKey = ConstantUtil.BDPush_IOSApiKey;
            secretKey = ConstantUtil.BDPush_IOSSecretKey;
        }
        pair = new PushKeyPair(apiKey, secretKey);
        BaiduPushClient pushClient = new BaiduPushClient(pair);
        return pushClient;
    }

    /**
     * Android 安卓单台设备推送通知, Android 消息不大于 4KB
     *
     * @param channel      百度推送通道编号
     * @param notification 推送通知的 JSON 数据
     * @param MessageType 消息类型:0 表示透传消息(默认), 1 表示通知
     * @return 是否推送成功
     * @throws PushClientException 客户端异常
     * @throws PushServerException 服务端异常
     */
    public static long singlePushForAndroid(String channel, JSONObject notification, int MessageType) throws PushClientException, PushServerException {
        long result = 0;
        BaiduPushClient client = pushClient(BaseUtil.DeviceType.Android.getCode());
        //  注册YunLogHandler，获取本次请求的交互信息
        client.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });
        try {
            PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest()
                    .addChannelId(channel) //  客户端通道编号
                    .addMsgExpires(18000) // 消息有效时间为5分钟:默认为 3600*5 = 18000
                    .addMessageType(MessageType) // 消息类型:0 表示透传消息(默认), 1 表示通知
                    .addMessage(notification.toString()) // 推送消息,接收外面传递的参数
                    .addDeviceType(3);//设备类型 1 表示 Web , 2 表示 PC , 3 表示 Android , 4 表示 iOS , 5 标识 WP

            PushMsgToSingleDeviceResponse response = client
                    .pushMsgToSingleDevice(request);
            result=Long.parseLong(response.getMsgId());
//      System.out.println("消息编号:" + response.getMsgId() + ",推送发送时间: "  + response.getSendTime());
        } catch (PushClientException e) {
            result = 0;
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            result = 0;
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                result=e.getRequestId();
//       System.out.println(String.format("请求编号:%d , 错误码:%d , 错误信息:%s",  e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
        return result;
    }

    /**
     * iOS 单台设备推送通知
     *
     * @param channel      百度推送通道编号
     * @param notification 推送通知的 JSON 数据
     * @return 是否推送成功
     * @throws PushClientException 客户端异常
     * @throws PushServerException 服务端异常
     */
    public static long singlePushForIOS(String channel, JSONObject notification) throws PushClientException, PushServerException {
        long result = 0;
        BaiduPushClient client = pushClient(BaseUtil.DeviceType.iOS.getCode());
        //  注册YunLogHandler，获取本次请求的交互信息
        client.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
//     System.out.println(event.getMessage());
            }
        });
        try {
            PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest()
                    .addChannelId(channel) // 客户端通道编号
                    .addMsgExpires(18000) // 消息有效时间为五分钟:默认为 3600*5 = 18000
                    .addMessageType(1) // 1：通知,0:透传消息.默认为0 注：IOS只有通知.
                    .addMessage(notification.toString()).addDeployStatus(ConstantUtil.IOSPublicStatus) //  iOS 证书状态 // 1: 开发环境,测试环境 // 2: 生成环境,正式环境.
                    .addDeviceType(4); // 设备类型 1 表示 Web , 2 表示 PC , 3 表示 Android , 4 表示 iOS , 5 表示 WP
            //  发起请求
            PushMsgToSingleDeviceResponse response = client.pushMsgToSingleDevice(request);
            result=Long.parseLong(response.getMsgId());
//     System.out.println("消息编号:" + response.getMsgId() + ", 发送时间:" + response.getSendTime());
        } catch (PushClientException e) {
            result = 0;
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;//表示抛出
            } else {
                e.printStackTrace();//表示捕获
            }
        } catch (PushServerException e) {
            result = 0;
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                result=e.getRequestId();
//       System.out.println(String.format("请求编号:%d , 错误码:%d , 错误信息:%s",e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }

        return result;
    }

    /**
     * 获取默认的配置信息
     * @param title
     * @param Childjsobj
     * @return
     */
    public static JSONObject getDefaultPushInfo(String title, JSONObject Childjsobj, boolean pkg_content,Integer iTypeId, Long lBelongId, Devices device, Long lToPushUserId, PushRecordsService pushRecordsService){
        JSONObject jsobj=new JSONObject();
        jsobj.put("title",title);
        jsobj.put("description",title);
        jsobj.put("notification_builder_id",0);
        jsobj.put("notification_basic_style",7);
        jsobj.put("open_type",2);
        jsobj.put("url","");
        if(device!=null && device.getDeviceTypeid()==BaseUtil.DeviceType.iOS.getCode()){
            JSONObject jsobjTemp = new JSONObject();
            int iUnRead = 1;
            if (lToPushUserId != null) {
            		Map<String, Object> mapQuery = new HashMap<String, Object>();
            		mapQuery.put(ArgumentsUtils.sUIDKey, lToPushUserId);
            		mapQuery.put(ArgumentsUtils.sRandomKey, RandomUtil.generateString(6));
            		mapQuery.put(ArgumentsUtils.sTypeKey, 0);
            		iUnRead = pushRecordsService.QueryCountByCondition(mapQuery);
			}
            jsobjTemp.put("content-available", iUnRead);
            jsobj.put("aps",jsobjTemp);
        }
		if (pkg_content) {
			if (null != iTypeId) {//判断进行什么跳转
				switch (iTypeId) {
				case ConstantUtils.iReadBank_PushType_BookValue://跳转书籍
					jsobj.put("pkg_content","#Intent;action=cc.whohelp.readbank.bookdetail;launchFlags=0x20000000;l.bookId="+lBelongId+";B.isShop=false;end");
					break;
				case ConstantUtils.iReadBank_PushType_ActivityValue://跳转活动
					jsobj.put("pkg_content","#Intent;action=cc.whohelp.readbank.activity;i.type=1;S.activityId="+lBelongId+";end");
					break;

				default:
					jsobj.put("pkg_content","#Intent;launchFlags=0x20000000;component=cc.whohelp.readbank/.modules.personal.activity.MyMessageActivity;end");
					break;
				}
			} else {//跳转消息列表
				jsobj.put("pkg_content","#Intent;launchFlags=0x20000000;component=cc.whohelp.readbank/.modules.personal.activity.MyMessageActivity;end");
			}
		} else {
			jsobj.put("pkg_content", "");
		}
        jsobj.put("custom_content",Childjsobj);
        return jsobj;
    }
    
    /**
     * 发送手机的推送信息
     * @param title
     * @param json
     * @param UserID
     * @param MessateType 消息类型 0为透传消息 1为普通消息
     * @return
     */
    public static int sendPushInfo(String title, String content, JSONObject json, long UserID, long ToPushID, int MessateType, Boolean pkg_content, Integer iType, Long lBelongId, PushDrivesService pushDriveService, DevicesService deviceService, PushRecordsService pushRecordService){
        int result=0;
        Map<String, Object> mapQuery = new HashMap<String, Object>();
        mapQuery.put(ArgumentsUtils.sUIDKey,ToPushID);//
        mapQuery.put(ArgumentsUtils.sStatusKey, BaseUtil.PushStatus.Open.getCode());//0
        List<PushDrives> lstPushDrives = pushDriveService.QueryLstPushDrives(mapQuery);
        
        if(lstPushDrives.size()>0){
            PushRecords pushRecord=new PushRecords();
            pushRecord.setPushrecordTitle(title);
            pushRecord.setPushrecordContent(content);
            pushRecord.setPushrecordUserid(UserID);
            pushRecord.setPushrecordPushuserid(ToPushID);
            mapQuery.clear();
            mapQuery.put(ArgumentsUtils.sDBIDKey, lstPushDrives.get(0).getPushdriveDeviceid());
            Devices device = deviceService.QueryDevice(mapQuery);
            long pushID=0;
            JSONObject jsobj = getDefaultPushInfo(title,json,pkg_content, iType, lBelongId, device, ToPushID, pushRecordService);
            if(device!=null){
                if(device.getDeviceTypeid()==BaseUtil.DeviceType.Android.getCode()){
                    try {
                        pushRecord.setPushrecordTypeid(0);
                        pushID = singlePushForAndroid(lstPushDrives.get(0).getPushdriveChannel(), jsobj, MessateType);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else if(device.getDeviceTypeid()==BaseUtil.DeviceType.iOS.getCode()){
                    try {
                        pushRecord.setPushrecordTypeid(1);
                        pushID = singlePushForIOS(lstPushDrives.get(0).getPushdriveChannel(), jsobj);
                    } catch (Exception e1) {
                        pushRecord.setPushrecordContent("推送失败:" + e1.getMessage());
                        e1.printStackTrace();
                    }
                }
            }else {//为了避免设备信息不存在导致无法推送
                try {
                    pushRecord.setPushrecordTypeid(0);
                    pushID = singlePushForAndroid(lstPushDrives.get(0).getPushdriveChannel(), jsobj, MessateType);
                } catch (Exception e) {
                    try {
                        pushRecord.setPushrecordTypeid(1);
                        pushID = singlePushForIOS(lstPushDrives.get(0).getPushdriveChannel(), jsobj);
                    } catch (Exception e1) {
                        pushRecord.setPushrecordContent("推送失败:" + e1.getMessage());
                        e1.printStackTrace();
                    }
                    e.printStackTrace();
                }
            }
            pushRecord.setPushrecordPushid(pushID);
            
			mapQuery.clear();
			mapQuery.put(ArgumentsUtils.sUIDKey, ToPushID);
			mapQuery.put(ArgumentsUtils.sStatusKey, BaseUtil.PushStatus.Open.getCode());// 0
			List<PushDrives> lstToPushDrive = pushDriveService.QueryLstPushDrives(mapQuery);

			PushDrives pjPushDrive = new PushDrives();
			pjPushDrive.setPushdriveDeviceid(lstToPushDrive.get(0).getPushdriveDeviceid());
			pjPushDrive.setPushdriveVersion(1);
			pushDriveService.UpdateMessage(pjPushDrive);
            
            result = pushRecordService.AddNewMessage(pushRecord);
        }
        return result;
    }
    
}
