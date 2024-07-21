package com.bjtu.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjtu.param.CommentListParam;
import com.bjtu.param.CommentParam;
import com.bjtu.pojo.Product;
import com.bjtu.pojo.Rating;
import com.bjtu.product.mapper.RatingMapper;
import com.bjtu.product.service.RatingService;
import com.bjtu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingMapper ratingMapper;

    public R save(CommentParam commentparam) {

        //1.先查询是否存在
        QueryWrapper<Rating> queryWrapper =
                new QueryWrapper<>();

        queryWrapper.eq("user_id", commentparam.getUserId());
        queryWrapper.eq("product_id", commentparam.getProductId());

        Long count = ratingMapper.selectCount(queryWrapper);

        Rating rating = new Rating();
        rating.setUserId(commentparam.getUserId());
        rating.setProductId(commentparam.getProductId());
        rating.setComment(commentparam.getComment());
        rating.setScore(commentparam.getScore());
        rating.setRatingTime(System.currentTimeMillis());

        if (count > 0) {
            UpdateWrapper<Rating> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("user_id", commentparam.getUserId()); // 设置更新条件
            updateWrapper.eq("product_id", commentparam.getProductId());
            int updateResult = ratingMapper.update(rating, updateWrapper);

            return R.ok("评分修改成功!");
        } else {
            int rows = ratingMapper.insert(rating);
        }

        return R.ok("评分成功!");
    }

    @Override
    public R show(CommentListParam commentlistparam) {
        Integer productId = commentlistparam.getProductId();

        QueryWrapper<Rating> queryWrapper = new QueryWrapper<>();

        queryWrapper.in("product_id", productId);


        IPage<Rating> page = new Page<>(commentlistparam.getCurrentPage(), commentlistparam.getPageSize());

        page = ratingMapper.selectPage(page, queryWrapper);
        R ok = R.ok("查询成功!", page.getRecords(), page.getTotal());
        return ok;

    }
}
