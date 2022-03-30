package com.ycshang.article.service;

import com.ycshang.article.model.entity.Tag;

import java.util.List;

public interface TagService {
    /**
     * 查询所有标签
     * @return
     */
    List<Tag> selectAll();
}
