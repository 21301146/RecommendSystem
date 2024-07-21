package com.bjtu.admin.service;

import com.bjtu.param.ProductSaveParam;
import com.bjtu.param.ProductSearchParam;
import com.bjtu.pojo.Product;
import com.bjtu.utils.R;

public interface ProductService {

    /**
     * 全部商品查询和搜索查询的方法
     * @param productSearchParam
     * @return
     */
    R search(ProductSearchParam productSearchParam);

    /**
     * 进行商品数据保存
     * @param productSaveParam
     * @return
     */
    R save(ProductSaveParam productSaveParam);

    /**
     * 更新商品数据
     * @param product
     * @return
     */
    R update(Product product);

    /**
     * 商品移除功能
     * @param productId
     * @return
     */
    R remove(Integer productId);
}
