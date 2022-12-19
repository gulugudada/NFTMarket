package com.c201901090124.nftmarket.service.impl;

import com.c201901090124.nftmarket.dao.BlogMapper;
import com.c201901090124.nftmarket.entity.Action;
import com.c201901090124.nftmarket.entity.Blog;
import com.c201901090124.nftmarket.service.ActionService;
import com.c201901090124.nftmarket.service.BlogService;
import com.c201901090124.nftmarket.utils.DateUtil;
import com.c201901090124.nftmarket.utils.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 小小怪
 * @title BlogServiceImpl
 * @create 2022/11/2 21:16
 **/
@Service
public class BlogServiceImpl implements BlogService {

    @Resource
    BlogMapper blogMapper;

    @Resource
    ActionService actionService;

    @Override
    public Result getBlogRecommend(int pageNum) {
//        if (!redisService.exists("blogRecommend")) {
//            updateBlogRecommend();
//        }
//        List<Object> blogList = redisService.lRange("blogRecommend",(pageNum-1)* 10L,pageNum* 10L -1);
//        if (blogList == null) {
//            //如果pageNum不是第一个10页，而且还是另一组10页的第一页，则说明缓存需要加大
//            if (pageNum/10 != 0 && pageNum%10 == 1) {
//                blogList = Collections.singletonList(blogMapper.getBlogRecommend((pageNum - 1) * 10, 10));
//                for (Object blog:blogList) {
//                    redisService.lPush("blogRecommend",blog);
//                }
//            }
//        }
        List<Blog> blogList = blogMapper.getBlogRecommend((pageNum - 1) * 10, 10);
        if (blogList.isEmpty()) {
            return Result.error(-1,"已经被你掏空了");
        }
        return Result.ok(blogList);
    }

    //鉴于点赞等操作不太好更新缓存，且又需要实时的更新数据，所以放弃使用Redis保存博客推荐
//
//    /**
//     * 定时更新！
//     * 每天晚上12点更新Redis中的博客推荐
//     */
//    @Scheduled(cron = "0 0 0 * * *")
//    public void updateBlogRecommend() {
//        //获取访问量前100的账号信息
//        //多表映射获取创建账号的用户名（已经实现）
//        if (redisService.exists("blogRecommend")) {
//            //缓存数据持久化
//            List<Object> list = redisService.lRange("blogRecommend",0,1000);
//            Blog blog;
//            for (Object object:list) {
//                blog = (Blog) object;
//                blogMapper.updateBlog(blog);
//            }
//            redisService.remove("blogRecommend");
//        }
//        List<Blog> blogList = blogMapper.getBlogRecommend(0,100);
//        for (Blog blog:blogList) {
//            redisService.lPush("blogRecommend",blog);
//        }
//    }

    @Override
    public Result getBlogLatest(int pageNum) {
        return Result.ok(blogMapper.getBlogLatest((pageNum - 1) * 10, 10));
    }

    @Override
    public Result getConcernBlogLatest(String account,int pageNum) {
        return Result.ok(blogMapper.getConcernBlogLatest(account,(pageNum - 1) * 10,10));
    }

    @Override
    public void thumbsUpBlog(String account, int actionId) {
        Action action = new Action(account,actionId,"blog","点赞", DateUtil.getDate());
        actionService.thumbsUp(action);
    }

    @Override
    public void thumbsDownBlog(String account, int actionId) {
        Action action = new Action(account,actionId,"blog","踩", DateUtil.getDate());
        actionService.thumbsDown(action);
    }

    @Override
    public Result getBlog(int id,String name) {
        //返回博客内容部分未完成



        blogMapper.visitBlog(id,name);
        return null;
    }

    @Override
    public Result addBlog(String name, String account, String content) {
        Blog blog = new Blog(name,account,content,DateUtil.getDate());
        if (blogMapper.addBlog(blog) > 0) {
            return Result.ok("发布成功");
        }
        return Result.error(-1,"发布失败");
    }

    @Override
    public Result updateBlog(int id, String name, String content) {
        if (blogMapper.updateBlog(id,name,content) > 0) {
            return Result.ok("修改成功");
        }
        return Result.error(-1,"修改失败");
    }

    @Override
    public Result deleteBlog(int id) {
        if (blogMapper.deleteBlog(id) > 0) {
            return Result.ok("删除成功");
        }
        return Result.error(-1,"删除失败");
    }
}
