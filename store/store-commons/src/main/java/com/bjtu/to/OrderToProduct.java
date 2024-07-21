package com.bjtu.to;

import lombok.Data;

import java.io.Serializable;


@Data
public class OrderToProduct implements Serializable {

    public static final Long serialVersionUID = 1L;


    private Integer productId;
    private Integer num;

}
