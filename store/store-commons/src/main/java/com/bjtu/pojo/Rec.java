package com.bjtu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@TableName("rec")
@Data
public class Rec implements Serializable {
    public static final Long serialVersionUID = 1L;
    @JsonProperty("id") //jackson的注解,用于进行属性格式化!
    @TableId(type = IdType.INPUT)
    private Integer Id;
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("product_id")
    private Integer productId;
    @JsonProperty("click_time")
    private Integer clickTime;
}
