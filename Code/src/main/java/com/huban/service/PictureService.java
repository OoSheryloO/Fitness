/**
 * 
 */
package com.huban.service;

import java.util.List;
import java.util.Map;

import com.huban.construct.PictrueModel;
import com.huban.pojo.Picture;

/**
 * @author GeJiangbo
 * @date 2017年5月19日
 */
public interface PictureService {
     
	 int addPictue(Picture picture);
	 
     public List<String> selectBanner(Long pictureBelongid);
     //增加 帖子 图片
     public int notePicture(Picture picture);
     
     public List<String> selectBy(Long noteId);
     
     public int AddLink(Map<String, Object> map);
     
     public List<PictrueModel> SearchBannerByCondition(Map<String, Object> map);

}
