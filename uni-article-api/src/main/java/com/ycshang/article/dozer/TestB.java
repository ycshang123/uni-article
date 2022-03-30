package com.ycshang.article.dozer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: uni-article-api
 * @description:
 * @author: ycshang
 * @create: 2022-03-28 10:51
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestB {
    public String name;
    public Date cDate;
}