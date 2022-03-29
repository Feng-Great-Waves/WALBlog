package com.wal.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wal.domain.ResponseResult;
import com.wal.domain.entity.Category;

/**
 * (Category)表服务接口
 *
 * @author makejava
 * @since 2022-03-20 16:23:26
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}
