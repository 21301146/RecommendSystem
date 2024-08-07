package com.bjtu.user.service.impl;

import com.bjtu.constants.UserConstants;
import com.bjtu.param.PageParam;
import com.bjtu.param.UserCheckParam;
import com.bjtu.param.UserLoginParam;
import com.bjtu.pojo.User;
import com.bjtu.user.mapper.UserMapper;
import com.bjtu.user.service.UserService;
import com.bjtu.utils.MD5Util;
import com.bjtu.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 检查账号是否可用业务
     *
     * @param userCheckParam 账号参数 已经校验完毕
     * @return 检查结果 001  004
     */
    @Override
    public R check(UserCheckParam userCheckParam) {

        //参数封装
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userCheckParam.getUserName());
        //数据库查询
        Long total = userMapper.selectCount(queryWrapper);
        //查询结果处理
        if (total == 0) {
            //数据库中不存在,可用
            log.info("UserServiceImpl.check业务结束，结果:{}", "账号可以使用!");
            return R.ok("账号不存在,可以使用!");
        }

        log.info("UserServiceImpl.check业务结束，结果:{}", "账号不可使用!");

        return R.fail("账号已经存在,不可注册!");
    }

    /**
     * 注册业务
     * @param user 参数已经校验,但是密码是明文!
     * @return 结果 001 004
     */
    @Override
    public R register(User user) {

        //1.检查账号是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", user.getUserName());
        //数据库查询
        Long total = userMapper.selectCount(queryWrapper);

        if (total > 0) {
            log.info("UserServiceImpl.register业务结束，结果:{}", "账号存在,注册失败!");
            return R.fail("账号已经存在,不可注册!");
        }
        //2.密码加密处理,注意要加盐
        String newPwd = MD5Util.encode(user.getPassword() + UserConstants.USER_SLAT);
        user.setPassword(newPwd);

        //3.插入数据库数据
        int rows = userMapper.insert(user);
        //4.返回封装结果
        if (rows == 0) {
            log.info("UserServiceImpl.register业务结束，结果:{}", "数据插入失败!注册失败!");
            return R.fail("注册失败!请稍后再试!");
        }

        log.info("UserServiceImpl.register业务结束，结果:{}", "注册成功!");

        return R.ok("注册成功!");
    }

    /**
     * 登录业务
     *
     * @param userLoginParam 账号和密码 已经校验 但是密码是明文!
     * @return 结果 001 004
     */
    @Override
    public R login(UserLoginParam userLoginParam) {

        //1.密码处理
        String newPwd = MD5Util.encode(userLoginParam.getPassword() + UserConstants.USER_SLAT);

        //2.数据库查询
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userLoginParam.getUserName());
        queryWrapper.eq("password", newPwd);

        User user = userMapper.selectOne(queryWrapper);

        //3.结果处理
        if (user == null) {
            log.info("UserServiceImpl.login业务结束，结果:{}", "账号和密码错误!");
            return R.fail("账号或者密码错误!");
        }

        log.info("UserServiceImpl.login业务结束，结果:{}", "登录成功!");
        //不返回password属性!
        user.setPassword(null);
        return R.ok("登录成功!", user);
    }

    /**
     * 后台管理调用,查询全部用户数据
     *
     * @param pageParam
     * @return
     */
    @Override
    public R listPage(PageParam pageParam) {

        IPage<User> page = new Page<>(pageParam.getCurrentPage(),pageParam.getPageSize());

        page = userMapper.selectPage(page,null);

        List<User> records = page.getRecords();
        long total = page.getTotal();

        return R.ok("用户管理查询成功!",records,total);
    }

    /**
     * 根据用户id删除数据
     *
     * @param userId
     * @return
     */
    @Override
    public R remove(Integer userId) {

        int i = userMapper.deleteById(userId);

        log.info("UserServiceImpl.remove业务结束，结果:{}",i);

        return R.ok("用户数据删除成功!");
    }


    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @Override
    public R update(User user) {

        //判断密码是否是原来的!
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user.getUserId());
        queryWrapper.eq("password",user.getPassword());
        Long aLong = userMapper.selectCount(queryWrapper);

        if (aLong == 0){
            //明文需要密码加密
            user.setPassword(MD5Util.encode(user.getPassword()+UserConstants.USER_SLAT));
        }

        int i = userMapper.updateById(user);

        log.info("UserServiceImpl.update业务结束，结果:{}",i);

        return R.ok("用户信息修改成功!");
    }


    /**
     * 添加用户信息
     *
     * @param user
     * @return
     */
    @Override
    public R save(User user) {
        //1.检查账号是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",user.getUserName());
        //数据库查询
        Long total = userMapper.selectCount(queryWrapper);

        if (total > 0){
            log.info("UserServiceImpl.register业务结束，结果:{}","账号存在,添加失败!");
            return R.fail("账号已经存在,不可添加!");
        }
        //2.密码加密处理
        String newPwd = MD5Util.encode(user.getPassword() + UserConstants.USER_SLAT);
        user.setPassword(newPwd);

        //3.插入数据库数据
        int rows = userMapper.insert(user);
        //4.返回封装结果
        if (rows == 0){
            log.info("UserServiceImpl.register业务结束，结果:{}","数据插入失败!添加失败!");
            return R.fail("添加失败!请稍后再试!");
        }

        log.info("UserServiceImpl.register业务结束，结果:{}","添加成功!");

        return R.ok("添加成功!");
    }

}
