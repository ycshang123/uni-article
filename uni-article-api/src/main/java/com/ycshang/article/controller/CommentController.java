package com.ycshang.article.controller;

import com.ycshang.article.common.ResponseResult;
import com.ycshang.article.model.entity.Comment;
import com.ycshang.article.model.vo.CommentVo;
import com.ycshang.article.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: uni-article-api
 * @description:
 * @author: ycshang
 * @create: 2022-03-30 21:42
 **/
@RestController
@RequestMapping(value = "/api/v1/comments")
@Slf4j
public class CommentController {
    @Resource
    private CommentService commentService;

    @GetMapping("{id}")
    public ResponseResult selectByArticleId(@PathVariable int id) {
        List<CommentVo> commentVoList = commentService.selectByArticleId(id);
        return ResponseResult.success(commentVoList);
    }

    @PostMapping("add")
    public ResponseResult addComment(@RequestBody Comment comment) {
        List<CommentVo> commentVoList = commentService.addComment(comment);
        return ResponseResult.success(commentVoList);
    }
}