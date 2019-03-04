package com.huban.service;
import java.util.*;

import com.huban.pojo.AccessActive;

/**
 * @ClassName: AccessActiveService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Sheryl
 * @date 2017年12月4日 下午5:21:04
 */
public interface AccessActiveService {

	public int addAccessActive(AccessActive obj);

	public int updateAccessActive(AccessActive obj);

	public int deleteAccessActive(Map<String,Object> params);
	
}