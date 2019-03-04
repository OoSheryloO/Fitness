/**
 * 
 */
package com.huban.service;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Book;

/**
 * @author GeJiangbo
 * @date 2017年6月1日
 */
public interface BookService {
    
	public List<Book> queryBooks(Map<String, Object> map);
	
	public List<String> queryAuthor(Map<String, Object> map);
	
	public Book queryBook(Long bookId);
	
	public Book queryBookById(Long bookId);
	
	public int addcommentcount(Long bookId);
	
	public String ShowBookName(Long bookId);
	
	public int changereadcount(Long bookId);
    
    public int changepaycount(Map<String,Object> map);
    
    public Book queryAuthorName(Long bookId);
    
    public Integer queryreadnumber(Long bookId);
    
    public String ShowBookMoney(Long bookId);
    
    public int AddNewMessage(Book record);
	
}
