package com.wal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wal.domain.ResponseResult;
import com.wal.domain.entity.User;


/**
 * 用户表(User)表服务接口
 *
 * @author fwt
 * @since 2022-03-23 14:09:21
 */
public interface UserService extends IService<User> {

    ResponseResult userInfo();

    ResponseResult register(User user);

    ResponseResult updateUserInfo(User user);
}
