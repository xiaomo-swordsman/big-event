package com.xiaomo.controller;

import com.xiaomo.pojo.Category;
import com.xiaomo.pojo.Result;
import com.xiaomo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
// 本controller 基于 Restful风格进行开发，虽然路径一样，但是请求方式不同，一样可以区分
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // 添加文章分类
    // 也可以通过这种方法接受参数，进行存储
//    @PostMapping
//    public Result addCategory(@RequestParam String categoryName,@RequestParam String categoryAlias){
//
//        categoryService.save(categoryName,categoryAlias);
//
//        return Result.success();
//    }
    
    // 添加文章分类
    @PostMapping
    public Result addCategory(@RequestBody @Validated Category category){
        categoryService.save(category);
        return Result.success();
    }

    // 查看文章分类列表
    @GetMapping
    public Result listCategory(){
        return Result.success(categoryService.list());
    }

    // 查看文章分类详情
    @GetMapping("/detail")
    public Result detail(@RequestParam int id){
        return Result.success(categoryService.findById(id));
    }

    // 更新文章分类
    @PutMapping
    public Result updateCategory(@RequestBody @Validated(Category.Update.class) Category category){
        categoryService.update(category);
        return Result.success();
    }

    // 删除文章分类
//    @DeleteMapping
//    public Result deleteCategory(@RequestParam int id){
//        categoryService.deleteByID(id);
//        return Result.success();
//    }

    // 删除文章分类
    @DeleteMapping("{id}")
    public Result deleteCategory(@PathVariable int id){
        categoryService.deleteByID(id);
        return Result.success();
    }
}
