package com.tang.bigevent.service.Impl;

import com.tang.bigevent.mapper.CategoryMapper;
import com.tang.bigevent.pojo.Category;
import com.tang.bigevent.service.CategoryService;
import com.tang.bigevent.utils.ThreadLocalUtil;
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
    public void add(Category category) {
        //更新时间
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());

        //设置用户id
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer userid=(Integer) map.get("id");
        category.setCreateUser(userid);

        //执行sql
        categoryMapper.add(category);
    }

    @Override
    public List<Category> list() {
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer userid=(Integer) map.get("id");
        return categoryMapper.list(userid);
    }

    @Override
    public Category findById(Integer id) {
        return categoryMapper.findById(id);
    }

    @Override
    public void update(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.update(category);
    }

    @Override
    public void delete(Integer id) {
        categoryMapper.delete(id);
    }
}
