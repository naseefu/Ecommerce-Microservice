package com.lustre.product_service.config;

import com.lustre.product_service.DTO.Stocks;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

public interface StockClient {

    @PostExchange("/stock/save")
    String saveStock(@RequestBody Stocks stocks);

    @GetExchange("/stock/get-stock/{id}")
    Stocks getStock(@PathVariable String id);

}
