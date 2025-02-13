package com.xiaomo.controller;

import com.github.pagehelper.PageHelper;
import com.xiaomo.pojo.Article;
import com.xiaomo.pojo.PageBean;
import com.xiaomo.pojo.Result;
import com.xiaomo.service.ArticleService;
import com.xiaomo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    // 这里写法不推荐，如果多个验证，需要每个都写，可以把这里验证token 写入拦截器
    @PostMapping("/list")
    public Result list(@RequestHeader(name="Authorization")String token, HttpServletResponse response){
        // 验证token(迭代：在拦截器中做了拦截校验)
//        try{
//            Map<String, Object> claim = JwtUtil.parseRSAToken(token);
//        }catch (Exception e){
//            // http响应401
//            response.setStatus(401);
//            return Result.error("用户未登录");
//        }
        return Result.success("显示所有文章");
    }

    // 新增文章
    @RequestMapping
    public Result add(@RequestBody @Validated Article article){
        articleService.add(article);
        return Result.success();
    }

    // 文章列表（条件分页）
    @GetMapping
    public Result list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state){
        PageBean<Article> list = articleService.list(pageNum, pageSize, categoryId, state);
        return Result.success(list);
    }


    // 获取文章详情
    // localhost:9009/dev/article/1
    @GetMapping("{id}")
    public Result getArticleById(@PathVariable int id){

        Article article = articleService.getArticleById(id);

        if(null == article){
            return Result.error("未找到文章");
        }
        return Result.success(article);
    }

    // 更新文章
    // localhost:9009/dev/article
    @PutMapping
    public Result updateArticle(@RequestBody @Validated Article article){
        articleService.updateArticle(article);
        return Result.success();
    }

    // 删除文章
    // localhost:9009/dev/article?id=2
    @DeleteMapping
    public Result deleteArticle(@RequestParam int id){
        articleService.delArticle(id);
        return Result.success();
    }
}
