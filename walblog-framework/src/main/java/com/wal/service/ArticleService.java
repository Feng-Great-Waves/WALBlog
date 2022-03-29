package com.wal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wal.domain.ResponseResult;
import com.wal.domain.entity.Article;

public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, long categoryId);

    ResponseResult getArticleDetail(long id);
}
