package com.huban.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.hadoop.hbase.filter.Filter.ReturnCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
//import com.core.model.Address;
//import com.core.model.Device;
//import com.core.model.Information;
//import com.core.model.PushDrive;
//import com.core.model.PushRecord;
import com.huban.Utils.ArgumentsUtils;
import com.huban.Utils.ReturnCodeUtils;
import com.huban.Utils.ReturnMessageUtils;
import com.huban.pojo.Devices;
import com.huban.pojo.PushDrives;
import com.huban.pojo.PushRecords;
import com.huban.pojo.User;
import com.huban.util.BaseUtil;
import com.huban.util.IdWorker;
import com.huban.util.ResultUtil;
//import com.util.PushUtil;
//import com.util.VerifyCode;

/**
 * <p> Title: PushController.java </p>
 * <p> Description: 推送控制器 </p>
 * <p> Company: huban </p>
 * 
 * @author Sheryl
 * @created 2017年10月12日 下午4:34:14
 */
@Controller
@RequestMapping("/Push")
public class PushController extends BaseController {
	/**
	 * 定义默认方法处理没有传递参数的错误处理
	 * 
	 * @return 返回参数列表错误的处理
	 */
	@RequestMapping(value = { "/", "/Interface/", "/Interface/V1/", "/Interface/V2/" })
	public @ResponseBody Map<String, Object> defaultMethod() {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}

	// 获取用户信息数据
	@RequestMapping(value = "/Interface/V2/Acquire", method = { RequestMethod.POST, RequestMethod.GET })
	public 
	@ResponseBody Map<String, Object> AcquireMethod(@RequestParam Long userId, @RequestParam(required = false) String keyWord, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size, @RequestParam(required=false) Integer type, HttpServletRequest request) {
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		if (page == null) {
			page = 1;
		}
		if (size == null) {
			size = 10;
		}
		if (keyWord != null && !keyWord.equals("")) {
			mapQuery.put(ArgumentsUtils.sKeyWordKey, keyWord);
		}
		if (type != null) {
			mapQuery.put(ArgumentsUtils.STypeKey, type);
		}
		mapQuery.put(ArgumentsUtils.sStartKey, size * (page - 1));
		mapQuery.put(ArgumentsUtils.sSizeKey, size);
		mapQuery.put(ArgumentsUtils.sUIDKey, userId);
		List<PushRecords> lstPushRecords = pushRecordsService.LstQueryByCondition(mapQuery);
		return ResultUtil.sharedInstance().TrueData(lstPushRecords, ReturnMessageUtils.trueReturnMessageKey, ReturnCodeUtils.Code.OK.getCode());
	}

	// 查看信息
	@RequestMapping(value = "/Interface/V2/Examine", method = { RequestMethod.POST, RequestMethod.GET })
	public 
	@ResponseBody Map<String, Object> ExamineMethod(@RequestParam String userId, Long pushId, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		PushRecords pjPushRecords = new PushRecords();
		pjPushRecords.setPushrecordId(pushId);
		pjPushRecords = pushRecordsService.QueryByModel(pjPushRecords);
		if (pjPushRecords != null) {
			pjPushRecords.setPushrecordStatus((byte) 1);
			int ErrorLogflag = pushRecordsService.ChangeMessage(pjPushRecords);
			if (ErrorLogflag > 0) {
				result = ResultUtil.sharedInstance().TrueData(null, ReturnMessageUtils.trueReturnMessageKey, ReturnCodeUtils.Code.OK.getCode());
			} else {
				result = ResultUtil.sharedInstance().FalseData("删除出错", ReturnCodeUtils.Error_Request_Type.ModifyFalse.getCode());
			}
		} else {
			result = ResultUtil.sharedInstance().FalseData("查询出错", ReturnCodeUtils.Error_Request_Type.SearchFalse.getCode());
		}
		return result;
	}

	/**
	 * 推送设备信息收集
	 * 
	 * @created 2017年10月14日 下午5:43:28
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/Interface/V2/Collocate", method = { RequestMethod.POST, RequestMethod.GET })
	public 
	@ResponseBody Map<String, Object> CollocateMethod(HttpServletRequest request) {
		Map<String, Object> mapQuery = new HashMap<String, Object>();
		String param = (String) request.getAttribute(BaseUtil.paramKey);
		if (param == null || param.equals("")) {
			param = request.getParameter(BaseUtil.paramKey);
		}
		JSONObject jsonObject = JSON.parseObject(param);
		String sUser = jsonObject.getString(User.sUserClass);
		User pjUser = JSON.parseObject(sUser, User.class);

		int findUserFlag = userService.findUser(pjUser);
		if (findUserFlag == 0) {
			return ResultUtil.sharedInstance().FalseData(ReturnMessageUtils.sError_User_NotFound, ReturnCodeUtils.Error_User_Status.Null.getCode());
		} else {
			pjUser = userService.selectUserByUserId(pjUser.getUserId());

			String sDevice = jsonObject.getString(Devices.sDeviceClass);
			Devices pjDevice = JSON.parseObject(sDevice, Devices.class);
			pjDevice.setDeviceUserid(pjUser.getUserId());

			if (pjDevice.getDeviceName().length() == 0) {
				return ResultUtil.sharedInstance().FalseData(ReturnMessageUtils.sError_PushName_NotFound, ReturnCodeUtils.Error_Parameter.Default.getCode());
			}

			if (pjDevice.getDeviceModel().length() == 0) {
				return ResultUtil.sharedInstance().FalseData(ReturnMessageUtils.sError_PushModel_NotFound, ReturnCodeUtils.Error_Parameter.Default.getCode());
			}

			if (pjDevice.getDeviceSystem().length() == 0) {
				return ResultUtil.sharedInstance().FalseData(ReturnMessageUtils.sError_PushSystem_NotFound, ReturnCodeUtils.Error_Parameter.Default.getCode());
			}

			mapQuery.put(ArgumentsUtils.sIDKey, pjUser.getUserId());
//			mapQuery.put(ArgumentsUtils.sStatusKey, BaseUtil.PushStatus.Open.getCode());

			List<Devices> lstDevices = devicesService.QueryLstDevices(mapQuery);

			int deviceFlag = 0, pushDriveFlag = 0;
			Devices currentDevice = null;
			pjDevice.setDeviceStatus((byte) 0);

			if (lstDevices != null && lstDevices.size() > 0) {
				boolean isFindDevice = false;
				for (int i = 0; i < lstDevices.size(); i++) {
					Devices deviceItem = lstDevices.get(i);
					if (deviceItem.getDeviceUdid() != null && pjDevice.getDeviceUdid() != null && deviceItem.getDeviceUdid().equals(pjDevice.getDeviceUdid())) {
						currentDevice = deviceItem;
						isFindDevice = true; 
						pjDevice.setDeviceId(currentDevice.getDeviceId());
						lstDevices.remove(i);
						break;
					}
				}
				if (!isFindDevice) {
					deviceFlag = devicesService.addAPPmessage(pjDevice);
				} else {// 个人觉得要在这加上 setUserId ，但是model里面好像有了，待检测
					currentDevice.setDeviceName(pjDevice.getDeviceName());
					currentDevice.setDeviceSystem(pjDevice.getDeviceSystem());
					deviceFlag = devicesService.UpdateMessage(currentDevice);
				}
			} else {
				deviceFlag = devicesService.addAPPmessage(pjDevice);
			}

			String pushDriveString = jsonObject.getString(PushDrives.sPushDriveClass);
			PushDrives pjPushDrive = JSON.parseObject(pushDriveString, PushDrives.class);

			pjPushDrive.setPushdriveUserid(pjUser.getUserId());
			pjPushDrive.setPushdriveStatus((byte) BaseUtil.PushStatus.Open.getCode());// 接收推送状态 0 表示不接受推送状态

			if (pjPushDrive.getPushdriveChannel().length() == 0) {
				return ResultUtil.sharedInstance().FalseData(ReturnMessageUtils.sError_PushChannel_NotFound, ReturnCodeUtils.Error_Parameter.Default.getCode());
			}

			if (pjDevice.getDeviceTypeid() == BaseUtil.DeviceType.iOS.getCode() && pjPushDrive.getPushdriveToken().length() == 0) {
				if (pjPushDrive.getPushdriveToken().length() == 0) {
					return ResultUtil.sharedInstance().FalseData(ReturnMessageUtils.sError_PushToken_NotFound, ReturnCodeUtils.Error_Parameter.Default.getCode());
				}
			}

			mapQuery.clear();
			mapQuery.put(ArgumentsUtils.sUIDKey, pjUser.getUserId());
			mapQuery.put(ArgumentsUtils.sStatusKey, BaseUtil.PushStatus.Open.getCode());//0

			List<PushDrives> lstPushDrives = pushDrivesService.QueryLstPushDrives(mapQuery);

			if (lstPushDrives != null && lstPushDrives.size() > 0) {
				for (int i = 0; i < lstPushDrives.size(); i++) {
					PushDrives pushDriveItem = lstPushDrives.get(i);
					
					if (pushDriveItem.getPushdriveDeviceid() == pjDevice.getDeviceId() || pushDriveItem.getPushdriveDeviceid().equals(pjDevice.getDeviceId())) {
						pjPushDrive = pushDriveItem;

						pushDriveFlag = pushDrivesService.UpdateMessage(pjPushDrive);
					} else {
						pjPushDrive.setPushdriveDeviceid(pjDevice.getDeviceId());
						if (lstDevices.size() > 0) {
							boolean flag = true;
							for (int j = 0; j < lstDevices.size(); j++) {
								Devices deviceItem = lstDevices.get(j);
								if (pushDriveItem.getPushdriveDeviceid() != deviceItem.getDeviceId()) {
									pushDriveItem.setPushdriveStatus((byte) BaseUtil.PushStatus.Close.getCode());//1
									//把原来的driver关闭推送 修改pushdrive的driveid
									pushDriveFlag = pushDrivesService.UpdateMessage(pushDriveItem);
									if (flag) {
										pushDriveItem.setPushdriveStatus((byte) BaseUtil.PushStatus.Open.getCode());//0
										pushDriveItem.setPushdriveId(IdWorker.CreateNewID());
										pushDriveItem.setPushdriveId(deviceItem.getDeviceId());
										pushDriveFlag = pushDrivesService.AddNewMessage(pjPushDrive);
									}
									flag = false;
								}else {
									pushDriveFlag = 1;
								}
							}
						} else {
							pushDriveFlag = 1;
						}
					}
				}
			} else {
				pjPushDrive.setPushdriveUserid(pjUser.getUserId());
				pjPushDrive.setPushdriveDeviceid(pjDevice.getDeviceId());
				pjPushDrive.setPushdriveId(IdWorker.CreateNewID());
				pushDriveFlag = pushDrivesService.AddNewMessage(pjPushDrive);
			}

			if (deviceFlag > 0 && pushDriveFlag > 0) {
				return ResultUtil.sharedInstance().TrueData(null, ReturnMessageUtils.trueReturnMessageKey, ReturnCodeUtils.Code.OK.getCode());
			} else {
				return ResultUtil.sharedInstance().FalseData("推送信息配置失败", ReturnCodeUtils.Code.NO.getCode());
			}
		}
	}
}
