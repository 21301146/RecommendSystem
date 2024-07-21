package com.bjtu.admin.service;

import com.bjtu.param.CartListParam;
import com.bjtu.param.PageParam;
import com.bjtu.pojo.User;
import com.bjtu.utils.R;

public interface UserService {

    /**
     * 用户的展示业务方法
     * @param pageParam
     * @return
     */
    R userList(PageParam pageParam);

    /**
     * 删除用户数据
     * @param cartListParam
     * @return
     */
    R userRemove(CartListParam cartListParam);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    R userUpdate(User user);

    /**
     * 添加用户信息
     * @param user
     * @return
     */
    R save(User user);
}
