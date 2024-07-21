package com.bjtu.admin.service.impl;

import com.bjtu.admin.mapper.AdminUserMapper;
import com.bjtu.admin.param.AdminUserParam;
import com.bjtu.admin.pojo.AdminUser;
import com.bjtu.admin.service.AdminUserService;
import com.bjtu.constants.UserConstants;
import com.bjtu.utils.MD5Util;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    /**
     * 登录业务方法
     *
     * @param adminUserParam
     * @return
     */
    @Override
    public AdminUser login(AdminUserParam adminUserParam) {

        QueryWrapper<AdminUser> queryWrapper
                   = new QueryWrapper<>() ;

        queryWrapper.eq("user_account",adminUserParam.getUserAccount());
        //密码
        queryWrapper.eq("user_password", MD5Util.encode(adminUserParam.getUserPassword()+ UserConstants.USER_SLAT));

        log.info(MD5Util.encode(adminUserParam.getUserPassword()+ UserConstants.USER_SLAT));
        AdminUser user = adminUserMapper.selectOne(queryWrapper);
        log.info("AdminUserServiceImpl.login业务结束，结果:{}",user);
        return user;
    }
}
