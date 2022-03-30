package com.wal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wal.domain.ResponseResult;
import com.wal.domain.entity.User;
import com.wal.domain.vo.UserInfoVo;
import com.wal.enums.AppHttpCodeEnum;
import com.wal.exception.SystemException;
import com.wal.mapper.UserMapper;
import com.wal.service.UserService;
import com.wal.utils.BeanCopyUtils;
import com.wal.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * 用户表(User)表服务实现类
 *
 * @author fwt
 * @since 2022-03-23 14:09:21
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseResult userInfo() {
        User user = getById(SecurityUtils.getUserId());
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        return ResponseResult.okResult(userInfoVo);
    }

    @Override
    public ResponseResult register(User user) {
        //对数据进行非空判断
        if(!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getPassword())){
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getEmail())){
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);
        }
        //对数据进行是否存在的判断
        if(userNameExist(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if(nickNameExist(user.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_EXIST);
        }
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        save(user);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult updateUserInfo(User user) {
        updateById(user);
        return ResponseResult.okResult();
    }

    private boolean nickNameExist(String nickName) {
        return count(new LambdaQueryWrapper<User>()
                .eq(User::getNickName,nickName))>0;
    }

    private boolean userNameExist(String userName) {
        return count(new LambdaQueryWrapper<User>()
                .eq(User::getUserName, userName))>0;
    }
}
