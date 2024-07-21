package com.bjtu.admin.service.impl;

import com.bjtu.admin.service.OrderService;
import com.bjtu.clients.OrderClient;
import com.bjtu.param.PageParam;
import com.bjtu.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderClient orderClient;
    /**
     * 查询订单数据
     *
     * @param pageParam
     * @return
     */
    @Override
    public R list(PageParam pageParam) {

        R r = orderClient.list(pageParam);
        log.info("OrderServiceImpl.list业务结束，结果:{}",r);
        return r;
    }
}
