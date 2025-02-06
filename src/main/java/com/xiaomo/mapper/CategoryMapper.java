package com.xiaomo.mapper;

import com.xiaomo.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Insert("insert into category (category_name,category_alias,create_user,create_time,update_time) " +
            "values(#{categoryName},#{categoryAlias},#{createUser},#{createTime},#{updateTime}) ")
    void save(Category category);

    @Select("select * from category where create_user=#{createUser}")
    List<Category> list(int createUser);

    @Select("select * from category where id=#{id}")
    Category findById(int id);

    @Update("update category set " +
            "category_name=#{categoryName},category_alias=#{categoryAlias},update_time=#{updateTime} where id=#{id}")
    void update(Category category);

    @Delete("delete from category where id=#{id}")
    void deleteByID(int id);
}
