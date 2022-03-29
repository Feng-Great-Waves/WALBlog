package com.wal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wal.constants.SystemConstants;
import com.wal.domain.ResponseResult;
import com.wal.domain.entity.Article;
import com.wal.domain.vo.ArticleDetailVo;
import com.wal.domain.vo.ArticleListVo;
import com.wal.domain.vo.HotArticleVo;
import com.wal.domain.vo.PageVo;
import com.wal.mapper.ArticleMapper;
import com.wal.service.ArticleService;
import com.wal.service.CategoryService;
import com.wal.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article > implements ArticleService {

    @Autowired
    private CategoryService categoryService;
    @Override
    public ResponseResult hotArticleList() {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL)
                .orderByDesc(Article::getViewCount);
        Page<Article> page = new Page<>(1,10);
        page(page,wrapper);
        List<Article> list = page.getRecords();
        List<HotArticleVo> vos = BeanCopyUtils.copyBeanList(list, HotArticleVo.class);
        return ResponseResult.okResult(vos);
    }

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, long categoryId) {
        Page<Article> page = new Page<>(pageNum,pageSize);
        page(page,new LambdaQueryWrapper<Article>()
                .eq(Objects.nonNull(categoryId)&&categoryId>0,Article::getCategoryId,categoryId)
                .eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORMAL)
                .orderByDesc(Article::getIsTop));
        page.getRecords().stream()
                .map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
                .collect(Collectors.toList());
        PageVo pageVo = new PageVo(BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class), page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(long id) {
        Article article = getById(id);
        String name = categoryService.getById(article.getCategoryId()).getName();
        if (name!=null){
            article.setCategoryName(name);
        }
        return ResponseResult.okResult(BeanCopyUtils.copyBean(article, ArticleDetailVo.class));
    }
}
