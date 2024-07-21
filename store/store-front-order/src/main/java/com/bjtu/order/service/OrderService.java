package com.bjtu.order.service;

import com.bjtu.param.OrderParam;
import com.bjtu.param.PageParam;
import com.bjtu.pojo.Order;
import com.bjtu.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;


public interface OrderService extends IService<Order> {


    /**
     * 进行订单数据保存业务
     * @param orderParam
     * @return
     */
    R save(OrderParam orderParam);

    /**
     * 分组查询订单数据
     * @param userId
     * @return
     */
    R list(Integer userId);

    /**
     * 检查订单中是否有商品引用
     * @param productId
     * @return
     */
    R check(Integer productId);

    /**
     * 后台管理查询订单数据
     * @param pageParam
     * @return
     */
    R adminList(PageParam pageParam);
}
