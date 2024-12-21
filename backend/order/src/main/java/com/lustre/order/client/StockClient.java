package com.lustre.order.client;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

@Configuration
public interface StockClient {

    @GetExchange("/stock/isExist")
    boolean isProductInStock(@RequestBody StockDTO stockDTO);

    @PostExchange("/stock/update")
    boolean updateStock(@RequestBody StockDTO stockDTO);

}
