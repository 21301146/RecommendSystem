package com.bjtu.admin.service;

import com.bjtu.admin.param.AdminUserParam;
import com.bjtu.admin.pojo.AdminUser;

public interface AdminUserService {

    /**
     * 登录业务方法
     * @param adminUserParam
     * @return
     */
    AdminUser login(AdminUserParam adminUserParam);
}
