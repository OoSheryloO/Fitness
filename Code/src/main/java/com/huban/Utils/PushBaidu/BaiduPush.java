package com.huban.Utils.PushBaidu;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
import com.huban.util.ConstantUtil;

/**
 * 推送单播通知
 * @author
 * @Date 2017年3月2日
 * @Desc
 */
public class BaiduPush {
	public String[] pushNotificationByUser(String channelId, Integer deviceType, String message, Map<String, Object> map) {
		String[] results = new String[3];
		String retCode = "1";
		String retMsg = "";
		try {
			// 1. 设置developer平台的ApiKey/SecretKey，需到百度推送注册
			String apiKey = "";
			String secretKey = "";
			if (deviceType != null && deviceType.intValue() == 4) {// ios
				apiKey = ConstantUtil.BDPush_IOSApiKey;
				secretKey = ConstantUtil.BDPush_IOSSecretKey;
			} else if (deviceType == 3) {// Android
				apiKey = ConstantUtil.BDPush_AndroidApiKey;
				secretKey = ConstantUtil.BDPush_AndroidSecretKey;
			}
			// 2. 创建PushKeyPair
			PushKeyPair pair = new PushKeyPair(apiKey, secretKey);
			// 创建BaiduPushClient，访问SDK接口
			BaiduPushClient pushClient = new BaiduPushClient(pair, BaiduPushConstants.CHANNEL_REST_URL);

			// 3. 注册YunLogHandler，获取本次请求的交互信息
			pushClient.setChannelLogHandler(new YunLogHandler() {
				@Override
				public void onHandle(YunLogEvent event) {
					System.out.println(event.getMessage());
				}
			});
			JSONObject notification = new JSONObject();
			if (deviceType == 4) {
				JSONObject jsonAPS = new JSONObject();
				jsonAPS.put("alert", message);
				jsonAPS.put("sound", "default");
				notification.put("aps", jsonAPS);
				notification.put("title", "default");
				// notification.put("key2", "value2");这里用于用户自定义

				if (map != null && !map.isEmpty()) {
					Set<String> customContentMapKeys = map.keySet();
					Iterator<String> irt = customContentMapKeys.iterator();
					while (irt.hasNext()) {
						String key = (String) irt.next();
						String value = (String) map.get(key);
						notification.put(key, value);
					}
				}
			} else if (deviceType == 3) {
				notification.put("title", "default");
				notification.put("description", message);
				notification.put("notification_builder_id", 0);//通知的基本样式？notification_basic_style：只有notification_builder_id为0时有效
				notification.put("notification_basic_style", 4);//(响铃：0x04；振动：0x02；可清除：0x01；)如果想选择任意两种或三种通知样式，notification_basic_style的值即为对应样式数值相加后的值。
				notification.put("open_type", 2); // 1 表示打开 URL , 2 表示打开应用
				notification.put("url", "http://push.baidu.com");// app上点开推送之后跳转的链接
				JSONObject jsonCustormCont = new JSONObject();
				jsonCustormCont.put("name", "test"); // 自定义内容，key-value
				notification.put("custom_content", jsonCustormCont);
				if (map != null && !map.isEmpty()) {// map中放的是用户自定义内容
					Set<String> customContentMapKeys = map.keySet();
					Iterator<String> irt = customContentMapKeys.iterator();
					while (irt.hasNext()) {
						String key = (String) irt.next();
						String value = (String) map.get(key);
						notification.put(key, value);
					}
				}
			}
			// 4. 创建请求类对象
			PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest().addChannelId(channelId)
					.addTopicId(apiKey).addMsgExpires(new Integer(3600)). // message有效时间
					addMessageType(1).// 1：通知,0:透传消息. 默认为0 注：IOS只有通知.
					addMessage(notification.toString()).addDeviceType(deviceType);// deviceType
																					// =>
																					// 3:android,
																					// 4:ios

			// 5. 调用pushMessage接口
			PushMsgToSingleDeviceResponse response = pushClient.pushMsgToSingleDevice(request);

			// 6. 认证推送成功
			System.out.println("msgId: " + response.getMsgId() + ",sendTime: " + response.getSendTime());
		} catch (PushClientException e) {
			// ERROROPTTYPE 用于设置异常的处理方式 -- 抛出异常和捕获异常,
			// 'true' 表示抛出, 'false' 表示捕获。
			if (BaiduPushConstants.ERROROPTTYPE) {
				try {
					throw e;
				} catch (PushClientException e1) {
					e1.printStackTrace();
				}
			} else {
				e.printStackTrace();
			}
		} catch (PushServerException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				try {
					throw e;
				} catch (PushServerException e1) {
					e1.printStackTrace();
				}
			} else {
				System.out.println(String.format("requestId: %d, errorCode: %d, errorMsg: %s", e.getRequestId(),
						e.getErrorCode(), e.getErrorMsg()));
			}
		} finally {
			results[0] = retCode;
			results[1] = retMsg;
		}

		return results;
	}

	public static void main(String[] args) {
		BaiduPush baiduPush = new BaiduPush();
		Map<String, Object> map = new HashMap<>();
		map.put("adc", "因为爱");
		baiduPush.pushNotificationByUser("3647798426971026832", 3, "今天天气哼不错", map);
	}

}
