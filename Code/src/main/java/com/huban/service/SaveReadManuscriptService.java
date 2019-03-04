/**
 * 
 */
package com.huban.service;

import java.util.List;
import java.util.Map;

import com.huban.pojo.SaveRead;

/**
 * <p>Title: SaveReadManuscriptService.java</p>
 * <p>Description: </p>
 * <p>Company: huban</p>
 * @author Sheryl
 * @created 2017年10月26日 上午9:31:27
 */
public interface SaveReadManuscriptService {

	public int AddNewManuscriptMessage(SaveRead record);
	
	public int UpdateManuscriptMessageByRsId(SaveRead record);
	
	public List<SaveRead> QueryLackMessage(Map<String, Object> map);
	
}
