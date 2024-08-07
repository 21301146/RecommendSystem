package com.bjtu.product.controller;

import com.bjtu.param.*;
import com.bjtu.pojo.Collect;
import com.bjtu.product.service.ProductService;
import com.bjtu.product.service.RatingService;
import com.bjtu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;



    @PostMapping("/promo")
    public R promo(@RequestBody @Validated ProductPromoParam productPromoParam, BindingResult result){

        if (result.hasErrors()){
            return R.fail("数据查询失败!");
        }

        return  productService.promo(productPromoParam.getCategoryName());
    }


    @PostMapping("hots")
    public R hots(@RequestBody @Validated ProductHotParam productHotParam,BindingResult result){

        if (result.hasErrors()){
            return R.fail("数据查询失败!");
        }

        return productService.hots(productHotParam);
    }

    @PostMapping("category/list")
    public R clist(){

        return productService.clist();
    }

    @PostMapping("bycategory")
    public R byCategory(@RequestBody @Validated ProductIdsParam productIdsParam,BindingResult result){

        if (result.hasErrors()){
            return R.fail("类别商品查询失败!");
        }

        return productService.byCategory(productIdsParam);
    }


    @PostMapping("all")
    public R all(@RequestBody @Validated ProductIdsParam productIdsParam,BindingResult result){

        if (result.hasErrors()){
            return R.fail("类别商品查询失败!");
        }

        return productService.byCategory(productIdsParam);
    }


    @PostMapping("detail")
    public R detail(@RequestBody @Validated ProductIdParam productIdParam,BindingResult result){

        if (result.hasErrors()) {
            return R.fail("商品详情查询失败");
        }
        return productService.detail(productIdParam.getProductID());
    }

    @PostMapping("pictures")
    public R pictures(@RequestBody @Validated ProductIdParam productIdParam,BindingResult result){

        if (result.hasErrors()) {
            return R.fail("商品图片详情查询失败");
        }
        return productService.pictures(productIdParam.getProductID());
    }

    @PostMapping("search")
    public R search(@RequestBody ProductSearchParam productSearchParam){

        return productService.search(productSearchParam);
    }

}
