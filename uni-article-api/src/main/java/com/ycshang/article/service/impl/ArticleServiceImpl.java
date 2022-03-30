package com.ycshang.article.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ycshang.article.mapper.ArticleMapper;
import com.ycshang.article.mapper.ArticleTagMapper;
import com.ycshang.article.model.entity.Article;
import com.ycshang.article.model.entity.ArticleTag;
import com.ycshang.article.model.vo.ArticleVo;
import com.ycshang.article.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @program: uni-article-api
 * @description:
 * @author: ycshang
 * @create: 2022-03-29 16:40
 **/
@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ArticleTagMapper articleTagMapper;

    @Override
    public void insertArticles(List<Article> articles) {
        articleMapper.batchInsert(articles);
        assert articles != null;
        articles.forEach(article -> {
            if (article.getTagList() != null) {
                articleTagMapper.batchInsert(article.getTagList());
            }
        });
    }

    @Override
    public PageInfo<ArticleVo> selectByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<ArticleVo> articleVoPage = articleMapper.selectAll();
        return new PageInfo<>(articleVoPage);
    }

    @Override
    public ArticleVo getDetail(int id) {
        return articleMapper.getDetail(id);
    }

    @Override
    public Article postArticle(Article article) {
        article.setCover("https://i.picsum.photos/id/1038/1920/1080?random&rand=" + Math.random());
        article.setCreateTime(LocalDateTime.now());
        articleMapper.insertArticle(article);
        List<ArticleTag> tagList = article.getTagList();
        tagList.forEach(tag -> tag.setArticleId(article.getId()));
        articleTagMapper.batchInsert(tagList);
        return article;
    }
}