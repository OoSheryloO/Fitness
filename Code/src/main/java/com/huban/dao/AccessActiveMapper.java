package com.huban.dao;
import java.util.*;

import com.huban.pojo.AccessActive;


/**
 * wtb_WeChatPublic
 */
public interface AccessActiveMapper {

	 /**
	 * 修改记录
	 */
	 public int updateAccessActive(AccessActive AccessActive);
	 /**
	 * 删除记录
	 */
	 public int deleteAccessActive(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addAccessActive(AccessActive AccessActive);

}