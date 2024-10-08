package com.tang.bigevent.controller;

import com.tang.bigevent.pojo.Category;
import com.tang.bigevent.pojo.Result;
import com.tang.bigevent.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //添加文章分类
    @PostMapping("/add")
    public Result add(@RequestBody @Validated Category category){
        categoryService.add(category);
        return Result.success();
    }

    //用户查看文章分类列表
    @GetMapping("/list")
    public Result<List<Category>> list(){
        List<Category> list=categoryService.list();
        return Result.success(list);
    }

    //查看文章分类
    @GetMapping("/detail")
    public Result<Category> detail(Integer id){
        Category category=categoryService.findById(id);
        return Result.success(category);
    }

    //更新文章分类
    @PutMapping("/update")
    public Result update(@RequestBody @Validated Category category){
        categoryService.update(category);
        return Result.success();
    }

    //删除文章分类
    @DeleteMapping("/delete")
    public Result delete(Integer id){
        categoryService.delete(id);
        return Result.success();
    }
}
