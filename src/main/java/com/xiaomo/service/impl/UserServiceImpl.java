package com.xiaomo.service.impl;

import com.xiaomo.mapper.UserMapper;
import com.xiaomo.pojo.User;
import com.xiaomo.service.UserService;
import com.xiaomo.util.Md5Util;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByName(String username) {
        return userMapper.findUserByName(username);
    }

    @Override
    public void addUser(String username,String password) {
        // 加密密码
        password = Md5Util.getMD5String(password);

        // 保存用户
        userMapper.addUser(username,password);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl, int id){
        userMapper.updateAvatar(avatarUrl,id);
    }

    @Override
    public void updatePassword(String newPassword, int id){
        userMapper.updatePassword(newPassword,id);
    }
}
