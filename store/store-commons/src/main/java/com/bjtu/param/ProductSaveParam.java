package com.bjtu.param;

import com.bjtu.pojo.Product;
import lombok.Data;


@Data
public class ProductSaveParam extends Product {

    private String pictures;
}
