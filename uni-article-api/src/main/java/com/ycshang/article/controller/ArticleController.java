package com.ycshang.article.controller;

import com.github.pagehelper.PageInfo;
import com.ycshang.article.common.ResponseResult;
import com.ycshang.article.model.entity.Article;
import com.ycshang.article.model.vo.ArticleVo;
import com.ycshang.article.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @program: uni-article-api
 * @description:
 * @author: ycshang
 * @create: 2022-03-29 16:44
 **/
@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @PostMapping("page")
    public ResponseResult getArtcleByPage(@RequestParam(name = "pageNum", defaultValue = "1", required = false) int pageNum,
                                          @RequestParam(name = "pageSize", defaultValue = "6", required = false) int pageSize) {
        PageInfo<ArticleVo> articleVoPageInfo = articleService.selectByPage(pageNum, pageSize);
        return ResponseResult.success(articleVoPageInfo);
    }

    @GetMapping("{id}")
    public ResponseResult getArticleDetail(@PathVariable int id) {
        ArticleVo detail = articleService.getDetail(id);
        return ResponseResult.success(detail);
    }

    @PostMapping("post")
    public ResponseResult postArticle(@RequestBody Article article) {
        return ResponseResult.success(articleService.postArticle(article));
    }


}