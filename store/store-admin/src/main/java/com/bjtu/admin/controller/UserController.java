package com.bjtu.admin.controller;

import com.bjtu.admin.service.UserService;
import com.bjtu.param.CartListParam;
import com.bjtu.param.PageParam;
import com.bjtu.pojo.User;
import com.bjtu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("list")
    public R userList(PageParam pageParam){

        return userService.userList(pageParam);
    }

    @PostMapping("remove")
    public R userList(CartListParam pageParam){

        return userService.userRemove(pageParam);
    }

    @PostMapping("update")
    public R update(User user){

        return userService.userUpdate(user);
    }

    @PostMapping("save")
    public R save(User user){

        return userService.save(user);
    }
}
