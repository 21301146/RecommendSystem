package com.bjtu.category.service;


import com.bjtu.param.PageParam;
import com.bjtu.param.ProductHotParam;
import com.bjtu.pojo.Category;
import com.bjtu.utils.R;

public interface CategoryService {

    /**
     * 根据类别名称,查询类别对象
     *
     * @param categoryName
     * @return
     */
    R byName(String categoryName);

    /**
     * 根据传入的热门类别名称集合!返回类别对应的id集合
     *
     * @param productHotParam
     * @return
     */
    R hotsCategory(ProductHotParam productHotParam);

    /**
     * 查询类别数据,进行返回!
     *
     * @return r 类别数据集合
     */
    R list();

    /**
     * 分页查询
     * @param pageParam
     * @return
     */
    R listPage(PageParam pageParam);

    /**
     * 添加类别信息
     * @param category
     * @return
     */
    R adminSave(Category category);

    /**
     * 删除数据
     * @param categoryId
     * @return
     */
    R adminRemove(Integer categoryId);

    /**
     * 类别修功能
     * @param category
     * @return
     */
    R adminUpdate(Category category);

    R recHots(Integer userId);

    R recByTd(Integer userId);
}
