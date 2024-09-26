package com.tang.bigevent.controller;

import com.tang.bigevent.pojo.Result;
import com.tang.bigevent.pojo.User;
import com.tang.bigevent.service.UserService;
import com.tang.bigevent.utils.JwtUtil;
import com.tang.bigevent.utils.Md5Util;
import com.tang.bigevent.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    //注册
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$")String username, @Pattern(regexp = "^\\S{5,16}$")String password){
        User user=userService.findByuserName(username);
        if (user==null){
            userService.register(username,password);
            return Result.success();
        }else {
            return Result.error("用户已存在");
        }
    }

    //登陆
    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$")String username,@Pattern(regexp = "^\\S{5,16}$")String password){
        User loginUser=userService.findByuserName(username);
        if (loginUser==null){
            return Result.error("用户名错误");
        }
        if (Md5Util.getMD5String(password).equals(loginUser.getPassword())){
            //登陆成功，写入token
            Map<String,Object> claims=new HashMap<>();
            claims.put("id",loginUser.getId());
            claims.put("username",loginUser.getUsername());
            String token= JwtUtil.genToken(claims);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }

    //查询用户基本信息
    @GetMapping("/userInfo")
    public Result<User> userInfo(@RequestHeader(name = "Authorization") String token){
        Map<String,Object> claims= ThreadLocalUtil.get();
        String username=(String) claims.get("username");
        User user=userService.findByuserName(username);
        return Result.success(user);
    }

    //更新用户信息
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }

    //更新用户头像
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    //更改用户密码
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> pwd){
        //获取参数
        String old_pwd=pwd.get("old_pwd");
        String new_pwd=pwd.get("new_pwd");
        String re_pwd=pwd.get("re_pwd");

        //判断是否合法
        Map<String,Object> map=ThreadLocalUtil.get();
        String username= (String) map.get("username");
        User loginUser=userService.findByuserName(username);
        //System.out.println(loginUser);
        if (loginUser.getPassword().equals(Md5Util.getMD5String(old_pwd)) && new_pwd.equals(re_pwd)){
            new_pwd=Md5Util.getMD5String(new_pwd);
            userService.updateUserPassword(loginUser.getId(),new_pwd);
            return Result.success();
        }
int a=1;
        if (!StringUtils.hasLength(old_pwd) || !StringUtils.hasLength(new_pwd) || !StringUtils.hasLength(re_pwd)){
            return Result.error("参数无效");
        }
        if (!loginUser.getPassword().equals(Md5Util.getMD5String(old_pwd))){
            return Result.error("旧密码错误");
        }
        if (!new_pwd.equals(re_pwd)){
            return Result.error("两次密码输入不一致");
        }
        return Result.success();

    }
}
