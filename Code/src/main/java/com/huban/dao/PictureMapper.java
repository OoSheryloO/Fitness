package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.construct.PictrueModel;
import com.huban.pojo.Picture;

public interface PictureMapper {
    int deleteByPrimaryKey(Long pictureId);

    int insert(Picture record);

    int insertSelective(Picture record);

    Picture selectByPrimaryKey(Long pictureId);

    int updateByPrimaryKeySelective(Picture record);

    int updateByPrimaryKey(Picture record);
    
    List<String> selectBanner(Long pictureBelongid);
    
    int notePicture(Picture picture);
    
    List<String> selectBy(Long noteId);
    
    int AddLink(Map<String, Object> map);
    
    List<PictrueModel> SearchBannerByCondition(Map<String, Object> map);
}