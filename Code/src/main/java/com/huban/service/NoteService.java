/**
 * 
 */
package com.huban.service;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Note;

/**
 * @author GeJiangbo
 * @date 2017年6月3日
 */
public interface NoteService {
   
	public List<Note> queryList(Map<String, Object> map);
	
	public int addforum(Note note);
	
	public int findnote(Note note);
	
	public int addpraise(Note note);
	
	public int cutpraise(Note note);
	
	public List<Note> selectByUserId(Map<String, Object> map);
	
	public int delforum(Long noteId);
	
	public  Map<String, Object> selectnotename(Long noteId);
//	public  Object selectnotename(Long noteId);
	public Note queryforumbyid(Long noteId);
	
	public Note SearchNoteDetail(Map<String, Object> params);
	
	public long queryuseridbyid(Long noteId);
	
	public int addcomnum(Long noteId);
}
