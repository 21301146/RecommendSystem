package com.bjtu.product.service;

import com.bjtu.param.CommentListParam;
import com.bjtu.param.CommentParam;
import com.bjtu.utils.R;
import org.springframework.stereotype.Service;

public interface RatingService {
    R save(CommentParam commentparam);

    R show(CommentListParam commentlistparam);
}
