package com.lustre.order.client;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

@Configuration
public interface ProductClient {

    @GetExchange("/product/each-order/{id}")
    Product getProduct(@PathVariable String id);

}
