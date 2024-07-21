package com.bjtu.order.mapper;

import com.bjtu.pojo.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjtu.vo.AdminOrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 查询后台管理的数据方法
     * @param offset
     * @param pageSize
     * @return
     */
    List<AdminOrderVo> selectAdminOrder(@Param("offset") int offset, @Param("pageSize") int pageSize);
}
