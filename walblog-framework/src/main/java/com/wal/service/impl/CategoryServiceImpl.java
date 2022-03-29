package com.wal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wal.constants.SystemConstants;
import com.wal.domain.ResponseResult;
import com.wal.domain.entity.Article;
import com.wal.domain.entity.Category;
import com.wal.domain.vo.CategoryVo;
import com.wal.mapper.CategoryMapper;
import com.wal.service.ArticleService;
import com.wal.service.CategoryService;
import com.wal.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * (Category)表服务实现类
 *
 * @author makejava
 * @since 2022-03-20 16:23:27
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ArticleService articleService;

    @Override
    public ResponseResult getCategoryList() {
        List<Article> articles = articleService.list(new LambdaQueryWrapper<Article>()
                .eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL));
        Set<Long> categoryIds = articles.stream()
                .map(article -> article.getCategoryId())
                .collect(Collectors.toSet());
        List<Category> categories = listByIds(categoryIds).stream()
                .filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());
        return ResponseResult.okResult(BeanCopyUtils.copyBeanList(categories, CategoryVo.class));

    }
}
