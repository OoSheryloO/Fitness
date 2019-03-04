/*
 * 2018/01/04 huban Creating 
 *
 * (c) Copyright huban Inc. All rights reserved.
 */
package com.huban.service;
import java.util.*;

import com.huban.pojo.Article;

/**
 * <p>Service class。</p>
 *
 * @author huban
 * @version 1.00
 */
public interface ArticleService {

    public Article FindByArticleId(long id);

    public List<Article> FindArticlesByCondition(Map<String,Object> params);
    
    public int getCountByCondition(Map<String,Object> params) ;
    
    public int addArticle(Article object);
    
    public int UpdateArticle(Article object);
    
    public int DeleteArticleById(Map<String,Object> params);
    
    public int EnabledArticleById(Map<String,Object> params);
    
}
