package com.xiaomo.mapper;

import com.xiaomo.pojo.Article;
import com.xiaomo.pojo.PageBean;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {

    @Insert("insert into article (title,content,cover_img,state,category_id,create_user,create_time,update_time)" +
            "values (#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Article article);

    //@Select("select * from article where create_user=#{userId} and category_id=#{categoryId} and state=#{state}")
    List<Article> list(Integer userId, Integer categoryId, String state);

    @Select("select * from article where id=#{id}")
    Article getArticleById(int id);

    @Delete("delete from article where id=#{id}")
    void delArticle(int id);

    @Update("update article set title=#{title},content=#{content},cover_img=#{coverImg},state=#{state}," +
            "category_id=#{categoryId},create_user=#{createUser} ,update_time=#{updateTime} where id=#{id}")
    void updateArticle(Article article);
}
