package com.bjtu.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CommentParam {

    @JsonProperty("user_id")
    @NotNull
    private Integer userId;

    @JsonProperty("product_id")
    @NotNull
    private Integer productId;

    @NotNull
    private Double score;

    private String comment;

}
