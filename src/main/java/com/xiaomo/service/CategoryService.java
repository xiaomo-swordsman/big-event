package com.xiaomo.service;

import com.xiaomo.pojo.Category;

import java.util.List;

public interface CategoryService {
    public void save(Category category);

    List<Category> list();

    Category findById(int id);

    void update(Category category);

    void deleteByID(int id);
}
