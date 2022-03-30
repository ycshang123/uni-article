package com.ycshang.article.model.dto;

import lombok.Data;

/**
 * @program: uni-article-api
 * @description:
 * @author: ycshang
 * @create: 2022-03-29 16:39
 **/
@Data
public class PageDto {
    private Integer pageNum;
    private Integer pageSize;
}