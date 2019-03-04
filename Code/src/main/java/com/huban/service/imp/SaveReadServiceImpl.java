/**
 * 
 */
package com.huban.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.construct.LstDeptSave;
import com.huban.construct.ShowTaskModel;
import com.huban.dao.SaveReadMapper;
import com.huban.pojo.SaveRead;
import com.huban.service.SaveReadService;
/**
 * <p>Title: SaveReadServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Company: huban</p>
 * @author Sheryl
 * @created 2017年9月27日 下午2:28:06
 */
@Service("saveReadService")
public class SaveReadServiceImpl implements SaveReadService{
    @Resource SaveReadMapper mapper;
	/* (non-Javadoc)
	 * @see com.huban.service.OnLineService#queryList(java.util.Map)
	 */

	@Override
	public int AddNewMessage(SaveRead record) {
		// TODO Auto-generated method stub
		return mapper.AddNewMessage(record);
	}

	@Override
	public int UpdateMessageByRsId(SaveRead record) {
		// TODO Auto-generated method stub
		return mapper.UpdateMessageByRsId(record);
	}

	@Override
	public List<SaveRead> QueryLackMessage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.QueryLackMessage(map);
	}

	@Override
	public List<SaveRead> QuerySaveReadRecord(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.QuerySaveReadRecord(map);
	}

	@Override
	public Map<String, Object> QuerySaveReadNumber(long rsID) {
		// TODO Auto-generated method stub
		return mapper.QuerySaveReadNumber(rsID);
	}

	@Override
	public List<LstDeptSave> QueryLstSaveReadDetail(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.QueryLstSaveReadDetail(map);
	}
	@Override
	public List<LstDeptSave> QueryLstSaveReadForAgent(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.QueryLstSaveReadForAgent(map);
	}

	@Override
	public long QueryReadNumberByOneMonth(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.QueryReadNumberByOneMonth(map);
	}

	@Override
	public ShowTaskModel QueryShowTaskModel(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.QueryShowTaskModel(map);
	}

	@Override
	public long QueryReadCountByMonth(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.QueryReadCountByMonth(map);
	}

	
}