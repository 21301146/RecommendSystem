package com.bjtu.clients;

import com.bjtu.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient("cart-service")
public interface CartClient {

    @PostMapping("/cart/remove/check")
    R check(@RequestBody Integer productId);
}
