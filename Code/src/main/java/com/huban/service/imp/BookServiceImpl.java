/**
 * 
 */
package com.huban.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.BookMapper;
import com.huban.pojo.Book;
import com.huban.service.BookService;

/**
 * @author GeJiangbo
 * @date 2017年6月1日
 */
@Service("bookService")
public class BookServiceImpl implements BookService{
     
	@Resource
	private BookMapper mapper;

	/* (non-Javadoc)
	 * @see com.huban.service.BookService#queryBooks(java.util.Map)
	 */
	@Override
	public List<Book> queryBooks(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.queryBooks(map);
	}

	@Override
	public List<String> queryAuthor(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.queryAuthor(map);
	}

	@Override
	public Book queryBook(Long bookId) {
		// TODO Auto-generated method stub
		return mapper.queryBook(bookId);
	}

	@Override
	public Book queryBookById(Long bookId) {
		// TODO Auto-generated method stub
		return mapper.shopQueryBook(bookId);
	}

	@Override
	public int addcommentcount(Long bookId) {
		// TODO Auto-generated method stub
		return mapper.addcommentcount(bookId);
	}

	@Override
	public String ShowBookName(Long bookId) {
		// TODO Auto-generated method stub
		return mapper.ShowBookName(bookId);
	}

	@Override
	public int changereadcount(Long bookId) {
		// TODO Auto-generated method stub
		return mapper.changereadcount(bookId);
	}

	@Override
	public int changepaycount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.changepaycount(map);
	}

	@Override
	public Book queryAuthorName(Long bookId) {
		// TODO Auto-generated method stub
		return mapper.queryAuthorName(bookId);
	}

	@Override
	public Integer queryreadnumber(Long bookId) {
		// TODO Auto-generated method stub
		return mapper.queryreadnumber(bookId);
	}

	@Override
	public String ShowBookMoney(Long bookId) {
		// TODO Auto-generated method stub
		return mapper.ShowBookMoney(bookId);
	}

	@Override
	public int AddNewMessage(Book record) {
		// TODO Auto-generated method stub
		return mapper.AddNewMessage(record);
	}

	
}
