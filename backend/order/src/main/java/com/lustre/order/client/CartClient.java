package com.lustre.order.client;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

@Component
public interface CartClient {

    @GetExchange("/cart/get-cart-order/{id}")
    List<StockDTO> getCartOrders(@PathVariable String id);

    @DeleteExchange("/cart/user-cart/{id}/{prodId}")
    String deleteCart(@PathVariable String id,@PathVariable String prodId);

}
