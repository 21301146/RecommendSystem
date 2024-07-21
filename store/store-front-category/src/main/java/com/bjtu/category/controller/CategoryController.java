package com.bjtu.category.controller;

import com.bjtu.category.service.CategoryService;
import com.bjtu.param.ProductHotParam;
import com.bjtu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/promo/{categoryName}")
    public R byName(@PathVariable String categoryName){

        if (StringUtils.isEmpty(categoryName)){
            return R.fail("类别名称为null,无法查询类别数据!");
        }

        return categoryService.byName(categoryName);
    }

    @GetMapping("/rec/{userId}")
    public R byName(@PathVariable Integer userId){

        if (StringUtils.isEmpty(userId)){
            return categoryService.recHots(userId);
        }

        return categoryService.recByTd(userId);
    }


    /**
     * 热门类别id查询!
     * @param productHotParam
     * @param result
     * @return
     */
    @PostMapping("hots")
    public R hotsCategory(@RequestBody @Validated ProductHotParam productHotParam, BindingResult result){

        if (result.hasErrors()){
            return R.fail("类别集合查询失败!");
        }

        return categoryService.hotsCategory(productHotParam);
    }

    @GetMapping("list")
    public R list(){

        return categoryService.list();
    }


}
