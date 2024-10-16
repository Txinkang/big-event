package com.tang.bigevent.controller;

import com.tang.bigevent.pojo.Article;
import com.tang.bigevent.pojo.PageBean;
import com.tang.bigevent.pojo.Result;
import com.tang.bigevent.service.ArticleService;
import com.tang.bigevent.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    //增加文章
    @PostMapping("/add")
    public Result add(@RequestBody @Validated Article article){
        articleService.add(article);
        return Result.success();
    }

    //分页查询文章
    @GetMapping("/list")
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ){
        PageBean<Article> pb=articleService.list(pageNum,pageSize,categoryId,state);
        return Result.success(pb);
    }

    //获取文章详情
    @GetMapping("/detail")
    public Result<Article> detail(Integer id){
        Article article=articleService.findById(id);
        return Result.success(article);
    }

    //更新文章
    @PutMapping("/update")
    public Result update(@RequestBody @Validated(Article.Update.class) Article article){
        articleService.update(article);
        return Result.success();
    }

    //删除文章
    @DeleteMapping("/delete")
    public Result delete(Integer id){
        articleService.delete(id);
        return Result.success();
    }
}
