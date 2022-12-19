package com.c201901090124.nftmarket.dao;

import com.c201901090124.nftmarket.entity.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 小小怪
 * @title BlogMapper
 * @create 2022/11/3 11:54
 **/
@Mapper
public interface BlogMapper {
    List<Blog> getBlogRecommend(int start,int count);
    List<Blog> getBlogLatest(int start,int count);
    List<Blog> getConcernBlogLatest(@Param("account") String account,@Param("start") int start,@Param("count") int count);
    int updateBlog(@Param("id") int id,@Param("name") String name,@Param("content") String content);
    void visitBlog(@Param("id") int id,@Param("name") String name);
    int addBlog(Blog blog);
    int deleteBlog(int id);
}
