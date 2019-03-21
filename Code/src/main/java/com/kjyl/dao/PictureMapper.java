package com.kjyl.dao;

import java.util.List;

import com.kjyl.config.Mybaties.MyMapper;
import com.kjyl.pojo.Picture;

/**
 * <p> Mapper Class</p>
 * @author sheryl
 * 
 */
public interface PictureMapper extends MyMapper<Picture>{
	List<String> SearchUrlBySpecial(long id);
}
