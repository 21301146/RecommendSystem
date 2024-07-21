package com.bjtu.category.service.impl;

import com.bjtu.category.mapper.CategoryMapper;
import com.bjtu.category.mapper.ProductMapper;
import com.bjtu.category.mapper.RecMapper;
import com.bjtu.category.service.CategoryService;
import com.bjtu.clients.ProductClient;
import com.bjtu.param.PageParam;
import com.bjtu.param.ProductHotParam;
import com.bjtu.pojo.Category;
import com.bjtu.pojo.Product;
import com.bjtu.pojo.Rec;
import com.bjtu.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private RecMapper recMapper;

    @Autowired
    private ProductClient productClient;

    /**
     * 根据类别名称,查询类别对象
     *
     * @param categoryName
     * @return
     */
    @Override
    public R byName(String categoryName) {

        //封装查询参数
        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.eq("category_name", categoryName);
        //查询数据库
        Category category = categoryMapper.selectOne(categoryQueryWrapper);
        //结果封装
        if (category == null) {
            log.info("CategoryServiceImpl.byName业务结束，结果:类别查询失败");
            return R.fail("类别查询失败!");
        }
        log.info("CategoryServiceImpl.byName业务结束，结果:{}", "类别查询成功");
        return R.ok("类别查询成功!", category);
    }


    /**
     * 根据传入的热门类别名称集合!返回类别对应的id集合
     *
     * @param productHotParam
     * @return
     */
    @Override
    public R hotsCategory(ProductHotParam productHotParam) {

        //封装查询参数
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("category_name", productHotParam.getCategoryName());
        queryWrapper.select("category_id");

        //查询数据库
        List<Object> ids = categoryMapper.selectObjs(queryWrapper);

        R ok = R.ok("类别集合查询成功", ids);
        log.info("CategoryServiceImpl.hotsCategory业务结束，结果:{}", ok);
        return ok;
    }

    /**
     * 查询类别数据,进行返回!
     *
     * @return r 类别数据集合
     */
    @Override
    public R list() {

        List<Category> categoryList = categoryMapper.selectList(null);
        R ok = R.ok("类别全部数据查询成功!", categoryList);
        log.info("CategoryServiceImpl.list业务结束，结果:{}", ok);
        return ok;
    }

    /**
     * 分页查询
     *
     * @param pageParam
     * @return
     */
    @Override
    public R listPage(PageParam pageParam) {

        IPage<Category> page = new Page<>(pageParam.getCurrentPage(), pageParam.getPageSize());

        page = categoryMapper.selectPage(page, null);

        return R.ok("类别分页数据查询成功!", page.getRecords(), page.getTotal());
    }

    /**
     * 添加类别信息
     *
     * @param category
     * @return
     */
    @Override
    public R adminSave(Category category) {

        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_name", category.getCategoryName());
        Long count = categoryMapper.selectCount(queryWrapper);

        if (count > 0) {
            return R.fail("类别已经存在,添加失败!");
        }

        int insert = categoryMapper.insert(category);

        log.info("CategoryServiceImpl.adminSave业务结束，结果:{}", insert);

        return R.ok("类别添加成功!");
    }

    /**
     * 删除数据
     *
     * @param categoryId
     * @return
     */
    @Override
    public R adminRemove(Integer categoryId) {

        Long aLong = productClient.adminCount(categoryId);

        if (aLong > 0) {
            return R.fail("类别删除失败,有:" + aLong + " 件商品正在引用!");
        }

        int i = categoryMapper.deleteById(categoryId);
        log.info("CategoryServiceImpl.adminRemove业务结束，结果:{}", i);
        return R.ok("类别数据删除成功!");
    }

    /**
     * 类别修功能
     *
     * @param category
     * @return
     */
    @Override
    public R adminUpdate(Category category) {

        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_name", category.getCategoryName());
        Long count = categoryMapper.selectCount(queryWrapper);

        if (count > 0) {
            return R.fail("类别已经存在,修改失败!");
        }

        int i = categoryMapper.updateById(category);
        log.info("CategoryServiceImpl.adminUpdate业务结束，结果:{}", i);
        return R.ok("类别数据修改成功!");
    }

    @Override
    public R recHots(Integer userId) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("product_sales").last("limit 7");
        List<Product> products = productMapper.selectList(queryWrapper);
        return R.ok("推荐热门商品查询成功",products);
    }

    @Override
    public R recByTd(Integer userId) {
        QueryWrapper<Rec> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("product_id").eq("user_id", userId).orderByDesc("click_time").last("limit 7");
        List<Rec> recs = recMapper.selectList(queryWrapper);
        List<Integer> columnValues = new ArrayList<>();
        for (Rec rec : recs) {
            columnValues.add(rec.getProductId());
        }
        QueryWrapper<Product> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.in("product_id", columnValues);
        List<Product> products = productMapper.selectList(queryWrapper2);
        return R.ok("推荐商品查询成功",products);
    }
}
