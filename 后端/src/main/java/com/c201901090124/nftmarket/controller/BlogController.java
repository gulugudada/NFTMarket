package com.c201901090124.nftmarket.controller;

import com.c201901090124.nftmarket.service.BlogService;
import com.c201901090124.nftmarket.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @PostMapping("thumbsUpBlog")
    public void thumbsUpBlog(@RequestBody Map<String, String> map) {
        blogService.thumbsUpBlog(map.get("account"),Integer.parseInt(map.get("actionId")));
    }

    @PostMapping("thumbsDownBlog")
    public void thumbsDownBlog(@RequestBody Map<String, String> map) {
        blogService.thumbsDownBlog(map.get("account"),Integer.parseInt(map.get("actionId")));
    }

    @PostMapping("getBlog")
    public Result getBlog(@RequestBody Map<String, String> map){
        return blogService.getBlog(Integer.parseInt(map.get("id")),map.get("name"));
    }

    @PostMapping("addBlog")
    public Result addBlog(@RequestBody Map<String, String> map){
        return blogService.addBlog(map.get("name"),map.get("account"),map.get("content"));
    }

    @PostMapping("updateBlog")
    public Result updateBlog(@RequestBody Map<String, String> map){
        return blogService.updateBlog(Integer.parseInt(map.get("id")),map.get("name"),map.get("content"));
    }

    @PostMapping("deleteBlog")
    public Result deleteBlog(@RequestBody Map<String, String> map){
        return blogService.deleteBlog(Integer.parseInt(map.get("id")));
    }
}
