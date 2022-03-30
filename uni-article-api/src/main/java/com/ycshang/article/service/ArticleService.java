package com.ycshang.article.service;


import com.github.pagehelper.PageInfo;
import com.ycshang.article.model.entity.Article;
import com.ycshang.article.model.vo.ArticleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: uni-article-api
 * @description:
 * @author: ycshang
 * @create: 2022-03-29 16:38
 **/
public interface ArticleService {
    /**
     * 批量新增
     *
     * @param articles
     */
    void insertArticles(List<Article> articles);

    /**
     * 查询文章数据并分页
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<ArticleVo> selectByPage(int pageNum, int pageSize);

    /**
     * 根据文章id查找文章详情
     *
     * @param id
     * @return
     */
    ArticleVo getDetail(@Param(value = "id") int id);

    /**
     * 分布文章
     *
     * @param article
     * @return
     */
    Article postArticle(Article article);
}