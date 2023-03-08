package com.c201901090124.nftmarket.service;

import com.c201901090124.nftmarket.entity.Blog;
import com.c201901090124.nftmarket.utils.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
    Result getAuthorHotBlog(String account);
    Result getAuthorLatestBlog(String account);
    void thumbsUpBlog(String account,int actionId);
    void thumbsDownBlog(String account,int actionId);
    Result getBlogPreview(int id);
    Result getBlogEdit(int id);
    Result saveBlog(int id,String name,String account,String content);
    Result publishBlog(Blog blog, MultipartFile file);
    Result uploadBLogImage(MultipartFile file) throws IOException;
    Result deleteBlog(int id);
}
