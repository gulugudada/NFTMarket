package com.c201901090124.nftmarket.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.api.R;
import com.c201901090124.nftmarket.dao.ActionMapper;
import com.c201901090124.nftmarket.dao.BlogMapper;
import com.c201901090124.nftmarket.dao.MessageMapper;
import com.c201901090124.nftmarket.entity.Action;
import com.c201901090124.nftmarket.entity.Blog;
import com.c201901090124.nftmarket.service.ActionService;
import com.c201901090124.nftmarket.service.BlogService;
import com.c201901090124.nftmarket.utils.DateUtil;
import com.c201901090124.nftmarket.utils.PicUtil;
import com.c201901090124.nftmarket.utils.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

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
    ActionMapper actionMapper;

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
    public Result getAuthorHotBlog(String account) {
        return Result.ok(blogMapper.getAuthorHotBlog(account));
    }

    //todo  获取作者的最新的博客列表
    @Override
    public Result getAuthorLatestBlog(String account) {
        return Result.ok(blogMapper.getAuthorLatestBlog(account));
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

    //Preview时通过id获取博客信息的接口
    @Override
    public Result getBlogPreview(int id) {
        Blog blog = blogMapper.getBlogById(id);
        if (blog != null) {
            Result result = Result.ok("获取成功！");
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(blog);
            jsonObject.put("collectNum", actionMapper.getBlogCollectNum(id));
            //todo  获得评论以及评论的数量的方法
            jsonObject.put("commentNum", actionMapper.getBlogCollectNum(id));
            result.put("data",jsonObject);
            //不管是谁都算访问量
            blogMapper.visitBlog(id);
            return result;
        }
        return Result.error(-1, "获取失败！");
    }

    //Edit时通过id获取博客信息的接口
    @Override
    public Result getBlogEdit(int id) {
        Blog blog = blogMapper.getBlogByIdEdit(id);
        if (blog != null) {
            return Result.ok(blog);
        }
        return Result.error(-1, "获取失败！");
    }

    @Override
    public Result saveBlog(int id,String name, String account, String content) {
        //前端如果进行博客的创建  id就会是初始值
        //前端如果进行博客的修改  id就不会是初始值
        //初始值设定为19
        Blog blog = new Blog(name,account,content);
        if (id == 19) {
            if (blogMapper.addBlog(blog) > 0) {
                Result result = Result.ok("保存成功");
                result.put("id", blog.getId());
                return result;
            }
        }
        else {
            blog.setId(id);
            if (blogMapper.updateBlog(blog) > 0) {
                return Result.ok("保存成功");
            }
        }
        return Result.error(-1,"保存失败");
    }

    private JSONObject uploadBlogCover(MultipartFile file) {
        JSONObject json = null;
        try {
            json = PicUtil.singleFileUpload(file, "/blog/cover");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    public Result publishBlog(Blog blog, MultipartFile file) {
        if (blog.getId() == 19) {
            if (blogMapper.addBlog(blog) > 0) {
                if (!Objects.isNull(file) && !file.isEmpty()) {//判断非空
                    JSONObject json = uploadBlogCover(file);
                    if (json.getInteger("code") == -1) {
                        return Result.error(-1,json.getString("msg"));
                    }
                    blog.setCover("/blog/cover/" + json.getString("msg"));
                }
                if (blogMapper.publishBlog(blog) == 1) {
                    Result result = Result.ok("发布成功");
                    result.put("id", blog.getId());
                    return result;
                }
            }
        }
        else {
            if (!Objects.isNull(file) && !file.isEmpty()) {//判断非空
                JSONObject json = uploadBlogCover(file);
                if (json.getInteger("code") == -1) {
                    return Result.error(-1,json.getString("msg"));
                }
                blog.setCover("/blog/cover/" + json.getString("msg"));
            }
            if (blogMapper.isPublished(blog.getId()) == 1) {
                if (blogMapper.updatePublishedBlog(blog) > 1) {
                    return Result.ok("发布成功");
                }
            }
            else {
                if (blogMapper.publishBlog(blog) == 1) {
                    return Result.ok("发布成功");
                }
            }
        }
        return null;
    }

    @Override
    public Result uploadBLogImage(MultipartFile file) {
        JSONObject json = null;
        try {
            json = PicUtil.singleFileUpload(file, "/blog/image");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert json != null;
        if (json.getInteger("code") == -1) {
            return Result.error(-1,json.getString("msg"));
        }
        String imageURL = "/blog/image/" + json.getString("msg");
        Result result = new Result();
        result.put("msg","上传成功");
        result.put("imageurl",imageURL);
        return result;
    }

    @Override
    public Result deleteBlog(int id) {
        if (blogMapper.deleteBlog(id) > 0) {
            return Result.ok("删除成功");
        }
        return Result.error(-1,"删除失败");
    }
}
