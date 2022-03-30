package com.ycshang.article.mapper;

import com.ycshang.article.model.dto.AddArticleDto;
import com.ycshang.article.model.entity.Article;

import com.ycshang.article.task.NewArticle;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Slf4j
class ArticleMapperTest {
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private NewArticle newArticle;

    //@Test
    //public void batchInsert() throws ExecutionException, InterruptedException {
    //    ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 8, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), Executors.defaultThreadFactory());
    //    Future<List<Article>> future = executor.submit(newArticle);
    //    List<Article> articles = future.get();
    //    articleMapper.batchInsert(articles);
    //
    //}
    //
    //
    //@Test
    //public void addArticle() {
    //    AddArticleDto addArticleDto = AddArticleDto
    //            .builder().userId(1).title("Mapper 测试")
    //            .content("测试").summary("简介简介简介简介").cover("https://cdn.jsdelivr.net/gh/ycshang123/image-hosting@master/ssy.yjincc3ixy8.jpg")
    //            .category("测试").createTime(new Date()).build();
    //    int row = articleMapper.addArticle(addArticleDto);
    //    log.info("新增了" + row + "条");
    //
    //}
    //
    //@Test
    //public void selectArticleByUserId() {
    //    List<ArticleUserVo> list = articleMapper.selectArticleByUserId(1);
    //    log.info("总记录条数" + list.size());
    //}

}