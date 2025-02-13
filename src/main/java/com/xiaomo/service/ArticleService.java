package com.xiaomo.service;

import com.xiaomo.pojo.Article;
import com.xiaomo.pojo.PageBean;

public interface ArticleService {
    void add(Article article);

    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    Article getArticleById(int id);

    void delArticle(int id);

    void updateArticle(Article article);
}
