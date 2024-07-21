package com.bjtu.product.controller;

import com.bjtu.param.CommentListParam;
import com.bjtu.param.CommentParam;
import com.bjtu.product.service.RatingService;
import com.bjtu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @PostMapping("/score")
    public R savescore(@RequestBody CommentParam commentparam){
        return ratingService.save(commentparam);
    }

    @PostMapping("/comment")
    public R showcomment(@RequestBody CommentListParam commentlistparam){
        return ratingService.show(commentlistparam);
    }
}
