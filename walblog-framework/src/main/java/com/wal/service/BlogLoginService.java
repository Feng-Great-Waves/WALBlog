package com.wal.service;

import com.wal.domain.ResponseResult;
import com.wal.domain.entity.User;

/**
 * @author fwt
 * @version 1.0
 * @date 2022/3/22 16:47
 */
public interface BlogLoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
