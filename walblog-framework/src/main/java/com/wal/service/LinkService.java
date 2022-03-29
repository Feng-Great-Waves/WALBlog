package com.wal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wal.domain.ResponseResult;
import com.wal.domain.entity.Link;



/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2022-03-21 22:56:45
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
}
