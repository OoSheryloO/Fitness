/**
 * 
 */
package com.huban.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.OnlinesMapper;
import com.huban.pojo.Onlines;
import com.huban.service.OnLineService;

/**
 * @author GeJiangbo
 * @date 2017年5月16日
 */
@Service("onLineService")
public class OnLineServiceImpl implements OnLineService{
    @Resource OnlinesMapper mapper;
	/* (non-Javadoc)
	 * @see com.huban.service.OnLineService#queryList(java.util.Map)
	 */
	@Override
	public List<Onlines> queryList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.queryList(map);
	}
	/* (non-Javadoc)
	 * @see com.huban.service.OnLineService#changeOnline(com.huban.pojo.Onlines)
	 */
	@Override
	public int changeOnline(Onlines onlines) {
		// TODO Auto-generated method stub
		return mapper.updateOnline(onlines);
	}
	/* (non-Javadoc)
	 * @see com.huban.service.OnLineService#addOnline(com.huban.pojo.Onlines)
	 */
	@Override
	public int addOnline(Onlines onlines) {
		// TODO Auto-generated method stub
		return mapper.insert(onlines);
	}

}
