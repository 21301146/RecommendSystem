package com.bjtu.carousel.service.impl;

import com.bjtu.carousel.mapper.CarouselMapper;
import com.bjtu.carousel.service.CarouselService;
import com.bjtu.pojo.Carousel;
import com.bjtu.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;

    @Cacheable(value="list.carousel",key = "#root.methodName" ,cacheManager = "cacheManagerDay")
    @Override
    public R list() {

        QueryWrapper<Carousel> carouselQueryWrapper = new QueryWrapper<>();
        carouselQueryWrapper.orderByDesc("priority");

        List<Carousel> list = carouselMapper.selectList(carouselQueryWrapper);
        //jdk 1.8 stream
        List<Carousel> collect = list.stream().limit(6).collect(Collectors.toList());

        R ok = R.ok(collect);

        log.info("CarouselServiceImpl.list业务结束，结果:{}",ok);
        return ok;
    }
}
