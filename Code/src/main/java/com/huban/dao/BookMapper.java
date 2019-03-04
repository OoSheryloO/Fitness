package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Book;

public interface BookMapper {
    int insert(Book record);

    int insertSelective(Book record);
    
    
    List<Book> queryBooks(Map<String, Object> map);
    
    List<String> queryAuthor(Map<String, Object> map);
    
    Book queryBook(Long bookId);
    
    Book shopQueryBook(Long bookId);
    
    int addcommentcount(Long bookId);
    
    String ShowBookName(Long bookId);
    
    int changereadcount(Long bookId);
    
    int changepaycount(Map<String, Object> map);
    
    Book queryAuthorName(Long bookId);
    
    Integer queryreadnumber(Long bookId);
    
    String ShowBookMoney(Long bookId);
    
    int AddNewMessage(Book record);
    
}