/**
 * 
 */
package com.huban.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.SaveReadManuscriptMapper;
import com.huban.pojo.SaveRead;
import com.huban.service.SaveReadManuscriptService;
/**
 * <p>Title: SaveReadManuscriptServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author Sheryl
 * @created 2017年10月26日 上午9:32:13
 */
@Service("saveReadManuscriptService")
public class SaveReadManuscriptServiceImpl implements SaveReadManuscriptService{
    @Resource SaveReadManuscriptMapper mapper;
	/* (non-Javadoc)
	 * @see com.huban.service.OnLineService#queryList(java.util.Map)
	 */

	@Override
	public int AddNewManuscriptMessage(SaveRead record) {
		// TODO Auto-generated method stub
		return mapper.AddNewManuscriptMessage(record);
	}

	@Override
	public int UpdateManuscriptMessageByRsId(SaveRead record) {
		// TODO Auto-generated method stub
		return mapper.UpdateManuscriptMessageByRsId(record);
	}

	@Override
	public List<SaveRead> QueryLackMessage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.QueryLackMessage(map);
	}

}