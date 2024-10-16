package com.tang.bigevent.mapper;

import com.tang.bigevent.pojo.Article;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleMapper {
    @Insert("insert into article(title,content,cover_img,state,category_id,create_user,create_time,update_time)"+
    "values(#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Article article);

    List<Article> list(Integer userid, Integer categoryId, String state);

    @Select("select * from article where id=#{id}")
    Article findById(Integer id);

    void update(Article category);

    @Delete("delete from article where id=#{id}")
    void delete(Integer id);
}
