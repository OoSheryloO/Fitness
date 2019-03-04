package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Note;

public interface NoteMapper {
    int deleteByPrimaryKey(Long noteId);

    int insert(Note record);

    int findnote(Note note);
    
    int addpraise(Note note);
    
    int cutpraise(Note note);
    
    List<Note> queryList(Map<String, Object> map);
    
    List<Note> selectByUserId(Map<String, Object> map);
    
    int delforum(Long noteId);
    
    Map<String, Object> selectnotename(Long noteId);
//    Object selectnotename(Long noteId);
    Note queryforumbyid(Long noteId);
    
    Note SearchNoteDetail(Map<String, Object> params);
    
    long queryuseridbyid(Long noteId);
    
    int addcomnum(Long noteId);
}