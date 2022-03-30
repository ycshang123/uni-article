package com.ycshang.article.service.impl;

import com.ycshang.article.mapper.TagMapper;
import com.ycshang.article.model.entity.Tag;
import com.ycshang.article.service.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: uni-article-api
 * @description:
 * @author: ycshang
 * @create: 2022-03-30 21:30
 **/
@Service
public class TagServiceImpl implements TagService {
    @Resource
    private TagMapper tagMapper;

    @Override
    public List<Tag> selectAll() {
        return tagMapper.selectAll();
    }
}