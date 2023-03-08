package com.c201901090124.nftmarket.dao;

import com.c201901090124.nftmarket.entity.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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
    List<Blog> getAuthorHotBlog(String account);
    List<Blog> getAuthorLatestBlog(String account);
    Blog getBlogById(int id);
    Blog getBlogByIdEdit(int id);
    Map<String,Integer> getBlogNum(String account);
    int isPublished(int id);
    int updateBlog(Blog blog);
    int updatePublishedBlog(Blog blog);
    void visitBlog(int id);
    int addBlog(Blog blog);
    int publishBlog(Blog blog);
    int deleteBlog(int id);
}
