package com.c201901090124.nftmarket.controller;

import com.alibaba.fastjson.JSONObject;
import com.c201901090124.nftmarket.entity.Blog;
import com.c201901090124.nftmarket.service.BlogService;
import com.c201901090124.nftmarket.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * @author 小小怪
 * @title BlogController
 * @create 2022/11/2 21:14
 **/
@Api(tags="博客接口类")
@RestController
@CrossOrigin
public class BlogController {

    @Resource
    BlogService blogService;

    /**
     * 获得博客推荐
     */
    @PostMapping("getBlogRecommend")
    public Result getBlogRecommend(@RequestParam("pageNum") Integer pageNum) {
        return blogService.getBlogRecommend(pageNum);
    }

    /**
     * 获得博客最新
     */
    @PostMapping("getBlogLatest")
    public Result getBlogLatest(@RequestBody Map<String, String> map) {
        return blogService.getBlogLatest(Integer.parseInt(map.get("pageNum")));
    }

    /**
     * 获得关注的账号的最新的博客
     * @param map
     * @return
     */
    @PostMapping("getConcernBlogLatest")
    public Result getConcernBlogLatest(@RequestBody Map<String, String> map) {
        return blogService.getConcernBlogLatest(map.get("account"),Integer.parseInt(map.get("pageNum")));
    }

    @PostMapping("getAuthorHotBlog")
    public Result getAuthorHotBlog(@RequestBody Map<String, String> map) {
        return blogService.getAuthorHotBlog(map.get("account"));
    }

    @PostMapping("getAuthorLatestBlog")
    public Result getAuthorLatestBlog(@RequestBody Map<String, String> map) {
        return blogService.getAuthorLatestBlog(map.get("account"));
    }

    @PostMapping("thumbsUpBlog")
    public void thumbsUpBlog(@RequestBody Map<String, String> map) {
        blogService.thumbsUpBlog(map.get("account"),Integer.parseInt(map.get("actionId")));
    }

    @PostMapping("thumbsDownBlog")
    public void thumbsDownBlog(@RequestBody Map<String, String> map) {
        blogService.thumbsDownBlog(map.get("account"),Integer.parseInt(map.get("actionId")));
    }

    @PostMapping("getBlogPreview")
    public Result getBlogPreview(@RequestBody Map<String, String> map){
        return blogService.getBlogPreview(Integer.parseInt(map.get("id")));
    }

    @PostMapping("getBlogEdit")
    public Result getBlogEdit(@RequestBody Map<String, String> map){
        return blogService.getBlogEdit(Integer.parseInt(map.get("id")));
    }

    @PostMapping("saveBlog")
    public Result saveBlog(@RequestBody Map<String, String> map) {
        return blogService.saveBlog(Integer.parseInt(map.get("id")), map.get("name"), map.get("account"), map.get("content"));
    }

    @PostMapping("publishBlog")
    public Result publishBlog(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("account") String account,  @RequestParam("content") String content,  @RequestParam("abstractContent") String abstractContent, @RequestParam(value = "file", required=false)  MultipartFile file) {
        return blogService.publishBlog(new Blog(Integer.parseInt(id), name, account, content, abstractContent), file);
    }

    @PostMapping("uploadBlogImage")
    public Result uploadBlogImage(@RequestParam("file") MultipartFile file) throws IOException {
        return blogService.uploadBLogImage(file);
    }

    @PostMapping("deleteBlog")
    public Result deleteBlog(@RequestBody Map<String, String> map){
        return blogService.deleteBlog(Integer.parseInt(map.get("id")));
    }
}
