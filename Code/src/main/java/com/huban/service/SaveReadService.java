/**
 * 
 */
package com.huban.service;

import java.util.List;
import java.util.Map;

import com.huban.construct.LstDeptSave;
import com.huban.construct.ShowTaskModel;
import com.huban.pojo.SaveRead;

/**
 * <p>Title: SaveReadService.java</p>
 * <p>Description: </p>
 * <p>Company: huban </p>
 * @author Sheryl
 * @created 2017年9月23日 上午9:19:02
 */
public interface SaveReadService {

	public int AddNewMessage(SaveRead record);
	
	public int UpdateMessageByRsId(SaveRead record);
	
	public List<SaveRead> QueryLackMessage(Map<String, Object> map);
	
	public Map<String, Object> QuerySaveReadNumber(long rsID);
	
	public List<SaveRead> QuerySaveReadRecord(Map<String, Object> map);
	
	public List<LstDeptSave> QueryLstSaveReadDetail(Map<String, Object> map);
	public List<LstDeptSave> QueryLstSaveReadForAgent(Map<String, Object> map);
	
	public long QueryReadNumberByOneMonth(Map<String, Object> map);
	
	public ShowTaskModel QueryShowTaskModel(Map<String, Object> map);
	
	public long QueryReadCountByMonth(Map<String, Object> map);
	
}
