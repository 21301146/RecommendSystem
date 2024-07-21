package com.bjtu.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CommentListParam extends PageParam{

    @JsonProperty("product_id")
    @NotNull
    private Integer productId;
}

