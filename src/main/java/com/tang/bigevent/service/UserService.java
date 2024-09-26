package com.tang.bigevent.service;

import com.tang.bigevent.pojo.User;

public interface UserService {
    User findByuserName(String username);

    void register(String username, String password);

    void update(User user);

    void updateAvatar(String avatarUrl);



    void updateUserPassword(Integer userid, String newPwd);
}
