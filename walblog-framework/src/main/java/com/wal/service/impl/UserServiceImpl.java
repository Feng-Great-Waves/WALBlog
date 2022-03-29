package com.wal.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wal.domain.ResponseResult;
import com.wal.domain.entity.User;
import com.wal.domain.vo.UserInfoVo;
import com.wal.mapper.UserMapper;
import com.wal.service.UserService;
import com.wal.utils.BeanCopyUtils;
import com.wal.utils.SecurityUtils;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author fwt
 * @since 2022-03-23 14:09:21
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public ResponseResult userInfo() {
        User user = getById(SecurityUtils.getUserId());
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        return ResponseResult.okResult(userInfoVo);
    }
}
