package com.xiaomo.service.impl;

import com.xiaomo.mapper.CategoryMapper;
import com.xiaomo.pojo.Category;
import com.xiaomo.service.CategoryService;
import com.xiaomo.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void save(Category category){

        Map<String,Object> claim = ThreadLocalUtil.get();
        int createUser = (Integer) claim.get("id");

        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        category.setCreateUser(createUser);
        categoryMapper.save(category);

    }

    @Override
    public List<Category> list() {
        Map<String,Object> claim = ThreadLocalUtil.get();
        int createUser = (Integer) claim.get("id");
        return categoryMapper.list(createUser);
    }

    @Override
    public Category findById(int id) {
        return categoryMapper.findById(id);
    }

    @Override
    public void update(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.update(category);
    }

    @Override
    public void deleteByID(int id) {
        categoryMapper.deleteByID(id);
    }
}
