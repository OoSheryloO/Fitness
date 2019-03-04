/*
 * 2018/01/04 huban Creating 
 *
 * (c) Copyright huban Inc. All rights reserved.
 */
package com.huban.service.imp;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huban.dao.ArticleMapper;
import com.huban.pojo.Article;
import com.huban.service.ArticleService;

/**
 * <p>Service class。</p>
 *
 * @author huban
 * @version 1.00
 */
 @Service("articleService")
public class ArticleServiceImpl implements ArticleService{

    /**
     * <p>default constants</p>
     */
     @Autowired
	private ArticleMapper mapper;
    
  /**
	 * <p>根据ID查询记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
     @Override
    public Article FindByArticleId(long id) {
		return mapper.FindByArticleId(id);
	}

	/**
	 * <p>根据条件查询记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
     @Override
    public List<Article> FindArticlesByCondition(Map<String,Object> params) {
		return mapper.FindArticlesByCondition(params);
	}

	/**
	 * <p>根据条件查询记录数</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
     @Override
    public int getCountByCondition(Map<String,Object> params) {
		return mapper.getCountByCondition(params);
	}

	/**
	 * <p>添加记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
     @Override
    public int addArticle(Article object){
		return mapper.addArticle(object);
	}
	
	/**
	 * <p>修改记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
     @Override
    public int UpdateArticle(Article object){
		return mapper.UpdateArticle(object);
	}
    
    /**
	 * <p>根据ID删除记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
     @Override
    public int DeleteArticleById(Map<String,Object> params){
		return mapper.DeleteArticleById(params);
	}
	
	/**
	 * <p>根据ID恢复记录</p>
	 *
	 * @author huban
	 * @version 1.00
	 */
     @Override
    public int EnabledArticleById(Map<String,Object> params){
		return mapper.EnabledArticleById(params);
	}
}
