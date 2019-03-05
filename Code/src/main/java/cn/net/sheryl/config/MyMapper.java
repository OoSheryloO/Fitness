package cn.net.sheryl.config;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

public interface MyMapper<T> {
	
	int Insert(T pojo);
	
	int Modify(T pojo);
	
	int RemoveBySpecial(String Id);
	
	int RemoveByCondition(Map<String, Object> map);
	
	int RecoverBySpecial(String Id);
	
	int RecoverByCondition(Map<String, Object> map);
	
	T SearchBySpecial(String Id);
	
	List<T> SearchByCondition(Map<String, Object> map);
	
	int SearchData(Map<String,Object> map);
	
	PageInfo<T> SearchPage(Map<String, Object> map, int pageNum, int pageSize);
}
