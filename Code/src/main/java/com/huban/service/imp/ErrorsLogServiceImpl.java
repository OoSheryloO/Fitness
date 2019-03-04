/**
 * 
 */
package com.huban.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.ErrorlogsMapper;
import com.huban.pojo.Errorlogs;
import com.huban.service.ErrorsLogService;

/**
 * @author GeJiangbo
 * @date 2017年5月12日
 */
@Service("errorsLogService")
public class ErrorsLogServiceImpl implements ErrorsLogService {
	@Resource
	private ErrorlogsMapper mapper;
	@Override
	public int addErrorLog(Errorlogs errorlogs) {
		// TODO Auto-generated method stub
		return mapper.insert(errorlogs);
	}

}
