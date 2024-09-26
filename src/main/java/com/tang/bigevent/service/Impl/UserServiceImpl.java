package com.tang.bigevent.service.Impl;

import com.tang.bigevent.mapper.UserMapper;
import com.tang.bigevent.pojo.User;
import com.tang.bigevent.service.UserService;
import com.tang.bigevent.utils.Md5Util;
import com.tang.bigevent.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

import static com.tang.bigevent.utils.Md5Util.getMD5String;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByuserName(String username) {
        User user=userMapper.findByuserName(username);
        return user;
    }

    @Override
    public void register(String username, String password) {
        //加密密码
        String md5Password=Md5Util.getMD5String(password);
        //插入数据库
        userMapper.add(username,md5Password);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer id= (Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl,id);
    }

    @Override
    public void updateUserPassword(Integer userid, String newPwd) {
        userMapper.updateUserPassword(userid,newPwd);
    }

}
