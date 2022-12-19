package com.c201901090124.nftmarket.service;

import com.c201901090124.nftmarket.utils.Result;
import org.springframework.stereotype.Service;

/**
 * @author 小小怪
 * @title BlogService
 * @create 2022/11/2 21:15
 **/
@Service
public interface BlogService {
    Result getBlogRecommend(int pageNum);
    Result getBlogLatest(int pageNum);
    Result getConcernBlogLatest(String account,int pageNum);
    void thumbsUpBlog(String account,int actionId);
    void thumbsDownBlog(String account,int actionId);
    Result getBlog(int id,String name);
    Result addBlog(String name,String account,String content);
    Result updateBlog(int id,String name,String content);
    Result deleteBlog(int id);
}
