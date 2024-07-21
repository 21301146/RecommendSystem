package com.bjtu.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductIdParam {

    @NotNull
    private Integer productID;
}
