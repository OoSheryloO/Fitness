package com.huban.publicway;

import java.util.Properties;

import com.huban.pojo.Errorlogs;
import com.huban.service.ErrorsLogService;
import com.huban.service.imp.ErrorsLogServiceImpl;
import com.huban.util.IdWorker;


public class ErrorRecords {
	/**type:
	 * 1 验证userId为空
	 * 
	 */
	public static void addErrorRecords(long userId,long type,String reasion){
		Properties props = System.getProperties(); // 系统属性
		Errorlogs errorLog = new Errorlogs();
		errorLog.setErrorlogId(IdWorker.CreateNewID());
		errorLog.setErrorlogUserid(userId);
		errorLog.setErrorlogTypeid(type);// 3
		errorLog.setErrorlogDevice(props.getProperty("os.name") + props.getProperty("os.arch") + props.getProperty("os.version"));
		errorLog.setErrorlogNote(reasion);
		ErrorsLogService errorsLogService = new ErrorsLogServiceImpl();
		errorsLogService.addErrorLog(errorLog);
	}

}
