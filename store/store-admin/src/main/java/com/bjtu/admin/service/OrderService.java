package com.bjtu.admin.service;

import com.bjtu.param.PageParam;
import com.bjtu.utils.R;

public interface OrderService {

    /**
     * 查询订单数据
     * @param pageParam
     * @return
     */
    R list(PageParam pageParam);
}
