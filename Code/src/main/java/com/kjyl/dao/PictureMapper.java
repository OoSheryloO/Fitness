package com.kjyl.dao;

import com.kjyl.config.Mybaties.MyMapper;
import com.kjyl.pojo.Picture;

/**
 * <p> Mapper Class</p>
 * @author sheryl
 * 
 */
public interface PictureMapper extends MyMapper<Picture>{
	String SearchUrlBySpecial(String id);
	
	Picture SearchByModel(Picture model);
	
	int RemoveBySpecialLogicId(String id);
}
