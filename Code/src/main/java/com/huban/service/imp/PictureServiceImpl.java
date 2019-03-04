/**
 * 
 */
package com.huban.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.construct.PictrueModel;
import com.huban.dao.PictureMapper;
import com.huban.pojo.Picture;
import com.huban.service.PictureService;

/**
 * @author GeJiangbo
 * @date 2017年5月19日
 */
@Service("pictureService")
public class PictureServiceImpl implements PictureService{

	@Resource
	private PictureMapper mapper;
	
	@Override
	public int addPictue(Picture picture) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(picture);
	}

	@Override
	public List<String> selectBanner(Long pictureBelongid) {
		// TODO Auto-generated method stub
		return mapper.selectBanner(pictureBelongid);
	}

	@Override
	public int notePicture(Picture picture) {
		// TODO Auto-generated method stub
		return mapper.notePicture(picture);
	}

	@Override
	public List<String> selectBy(Long noteId) {
		// TODO Auto-generated method stub
		return mapper.selectBy(noteId);
	}

	@Override
	public int AddLink(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.AddLink(map);
	}

	/* (non - Javadoc)
	* @param map
	* @return
	* @see com.huban.service.PictureService#SearchBannerByCondition(java.util.Map)
	*/
	@Override
	public List<PictrueModel> SearchBannerByCondition(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.SearchBannerByCondition(map);
	}

}
