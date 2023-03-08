package com.c201901090124.nftmarket.service.impl;

import com.c201901090124.nftmarket.dao.AccountMapper;
import com.c201901090124.nftmarket.dao.BlogMapper;
import com.c201901090124.nftmarket.dao.MessageMapper;
import com.c201901090124.nftmarket.entity.Account;
import com.c201901090124.nftmarket.entity.Comment;
import com.c201901090124.nftmarket.entity.VO.CommentVO;
import com.c201901090124.nftmarket.service.MessageService;
import com.c201901090124.nftmarket.utils.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 小小怪
 * @title MessageServiceImpl
 * @create 2022/11/28 11:50
 **/
@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    MessageMapper messageMapper;

    @Resource
    AccountMapper accountMapper;

    @Resource
    BlogMapper blogMapper;

    private CommentVO comment2CommentVO(Comment comment) {
        CommentVO commentVO = new CommentVO(comment.getId(), comment.getAccount(), comment.getContent(), comment.getType(), comment.getCommentAccount(), comment.getCommentTime());
        if (Objects.equals(comment.getType(), "blog")) {
            commentVO.setObject(blogMapper.getBlogById(comment.getCommentContentId()));
        }
        else if (Objects.equals(comment.getType(), "dynamics")) {
//                commentVO.setObject();
        }
        else {
            commentVO.setObject(getCommentById(comment.getCommentContentId()));
        }
        return commentVO;
    }

    @Override
    public Result getCommentMessage(String account, int pageNum) {
        List<Comment> commentList = messageMapper.getCommentMessage(account, (pageNum - 1) * 5 ,5);
        List<CommentVO> commentVOList = new ArrayList<>();
        for (Comment comment : commentList) {
            CommentVO commentVO = comment2CommentVO(comment);
            Account account1 = accountMapper.findAccount(commentVO.getCommentAccount());
            commentVO.setUsername(account1.getUsername());
            commentVO.setAvatar(account1.getAvatar());
            commentVOList.add(commentVO);
        }
        Result result = Result.ok(commentVOList);
        result.put("total", messageMapper.getCommentMessageNum(account) / 5 + (messageMapper.getCommentMessageNum(account) % 5  == 0? 0 : 1));
        return result;
    }

    @Override
    public void clearCommentMessage(String account) {
        messageMapper.clearCommentMessage(account);
    }

    @Override
    public void deleteCommentMessage(int id) {
        messageMapper.deleteCommentMessage(id);
    }

    public Object getCommentById(int id) {
        Comment comment = messageMapper.getCommentById(id);
        while (comment.getType().equals("comment")) {
            comment = messageMapper.getCommentById(comment.getCommentContentId());
        }
        if (comment.getType().equals("blog")) {
            return blogMapper.getBlogById(comment.getCommentContentId());
        }
        else {
            //通过id返回动态
            return comment;
        }
    }

}
