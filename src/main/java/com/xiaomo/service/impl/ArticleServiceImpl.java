package com.xiaomo.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaomo.mapper.ArticleMapper;
import com.xiaomo.pojo.Article;
import com.xiaomo.pojo.PageBean;
import com.xiaomo.service.ArticleService;
import com.xiaomo.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        Map<String,Object> claim = ThreadLocalUtil.get();
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        article.setCreateUser((Integer) claim.get("id"));
        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        // 1、创建PageBean对象
        PageBean<Article> pageBean = new PageBean<Article>();

        // 2、开启分页查询 PageHelper
        PageHelper.startPage(pageNum,pageSize);

        // 3、查询数据
        Map<String,Object> claim = ThreadLocalUtil.get();
        Integer userId = (Integer)claim.get("id");
        List<Article> list = articleMapper.list(userId,categoryId,state);

        // 4、将list数据强转Page，并封装到PageBean对象
        Page<Article> page = (Page<Article>)list;
        pageBean.setTotal(page.getTotal());
        pageBean.setItems(page.getResult());

        return pageBean;
    }

    @Override
    public Article getArticleById(int id){
        return articleMapper.getArticleById(id);
    }

    @Override
    public void delArticle(int id){
        articleMapper.delArticle(id);
    }

    @Override
    public void updateArticle(Article article){
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.updateArticle(article);
    }

}
