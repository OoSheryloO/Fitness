/**
 * 
 */
package com.huban.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.NoteMapper;
import com.huban.pojo.Note;
import com.huban.service.NoteService;

/**
 * @author GeJiangbo
 * @date 2017年6月3日
 */
@Service("noteService")
public class NoteServiceImpl implements NoteService{
   
	@Resource
	private NoteMapper mapper;
	/* (non-Javadoc)
	 * @see com.huban.service.NoteService#queryList(java.util.Map)
	 */
	@Override
	public List<Note> queryList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.queryList(map);
	}
	/* (non-Javadoc)
	 * @see com.huban.service.NoteService#addforum(com.huban.pojo.Note)
	 */
	@Override
	public int addforum(Note note) {
		// TODO Auto-generated method stub
		return mapper.insert(note);
	}
	/* (non-Javadoc)
	 * @see com.huban.service.NoteService#findnote(com.huban.pojo.Note)
	 */
	@Override
	public int findnote(Note note) {
		// TODO Auto-generated method stub
		return mapper.findnote(note);
	}
	/* (non-Javadoc)
	 * @see com.huban.service.NoteService#addpraise(com.huban.pojo.Note)
	 */
	@Override
	public int addpraise(Note note) {
		// TODO Auto-generated method stub
		return mapper.addpraise(note);
	}
	/* (non-Javadoc)
	 * @see com.huban.service.NoteService#cutpraise(com.huban.pojo.Note)
	 */
	@Override
	public int cutpraise(Note note) {
		// TODO Auto-generated method stub
		return mapper.cutpraise(note);
	}
	@Override
	public List<Note> selectByUserId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectByUserId(map);
	}
	@Override
	public int delforum(Long noteId) {
		// TODO Auto-generated method stub
		return mapper.delforum(noteId);
	}
//	@Override
//	public List<Note> selectnoteid(Map<String, Object> map) {
//		// TODO Auto-generated method stub
//		return mapper.selectnoteid(map);
//	}
	@Override
	public Map<String, Object> selectnotename(Long noteId) {
//	public Object selectnotename(Long noteId) {
		// TODO Auto-generated method stub
		return mapper.selectnotename(noteId);
	}
	@Override
	public Note queryforumbyid(Long noteId) {
		// TODO Auto-generated method stub
		return mapper.queryforumbyid(noteId);
	}
	
	@Override
	public Note SearchNoteDetail(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return mapper.SearchNoteDetail(params);
	}
	
	@Override
	public long queryuseridbyid(Long noteId) {
		// TODO Auto-generated method stub
		return mapper.queryuseridbyid(noteId);
	}
	@Override
	public int addcomnum(Long noteId) {
		// TODO Auto-generated method stub
		return mapper.addcomnum(noteId);
	}

}
