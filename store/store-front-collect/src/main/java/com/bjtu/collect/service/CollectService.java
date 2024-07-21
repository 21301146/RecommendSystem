package com.bjtu.collect.service;

import com.bjtu.pojo.Collect;
import com.bjtu.utils.R;


public interface CollectService {

    /**
     * 收藏添加的方法
     * @param collect
     * @return 001 004
     */
    R save(Collect collect);

    /**
     * 根据用户id查询商品信息集合
     * @param userId
     * @return
     */
    R list(Integer userId);

    /**
     * 根据用户id和商品id删除收藏数据
     * @param collect userId productId
     * @return 001 003
     */
    R remove(Collect collect);

    /**
     * 删除根据商品id
     * @param productId
     * @return
     */
    R removeByPid(Integer productId);
}
