package com.tang.bigevent.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tang.bigevent.mapper.ArticleMapper;
import com.tang.bigevent.pojo.Article;
import com.tang.bigevent.pojo.PageBean;
import com.tang.bigevent.service.ArticleService;
import com.tang.bigevent.utils.ThreadLocalUtil;
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
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        Map<String,Object> map= ThreadLocalUtil.get();
        Integer userid=(Integer) map.get("id");
        article.setCreateUser(userid);

        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        //新建PageBean对象
        PageBean<Article> pageBean=new PageBean<>();

        //设置分页以及条数
        PageHelper.startPage(pageNum,pageSize);
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer userid=(Integer) map.get("id");
        //开始查询并且转成Page类型，为了方便调用Page的方法并且塞到PageBean里
        List<Article> list=articleMapper.list(userid,categoryId,state);
        Page<Article> page=(Page<Article>) list;
        //给pageBean塞数据
        pageBean.setTotal(page.getTotal());
        pageBean.setItems(page.getResult());
        return pageBean;
    }

    @Override
    public Article findById(Integer id) {
        return articleMapper.findById(id);
    }

    @Override
    public void update(Article article) {
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.update(article);
    }

    @Override
    public void delete(Integer id) {
        articleMapper.delete(id);
    }
}
