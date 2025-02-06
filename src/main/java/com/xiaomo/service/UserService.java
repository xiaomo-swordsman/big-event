package com.xiaomo.service;

import com.xiaomo.pojo.User;
import org.springframework.stereotype.Service;

public interface UserService {

    public User findUserByName(String username);

    public void addUser(String username,String password);

    void update(User user);

    void updateAvatar(String avatarUrl, int id);

    void updatePassword(String newPassword, int id);
}
