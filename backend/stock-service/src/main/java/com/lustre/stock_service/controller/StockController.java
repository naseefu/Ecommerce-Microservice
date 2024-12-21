package com.lustre.stock_service.controller;

import com.lustre.stock_service.dto.StockDTO;
import com.lustre.stock_service.entity.Stocks;
import com.lustre.stock_service.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/get-stock/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Stocks getStock(@PathVariable String id) {
        return stockService.getStocksOfProduct(id);

    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public String saveStock(@RequestBody Stocks stock) {
        return stockService.saveStock(stock);
    }

    @GetMapping("/isExist")
    @ResponseStatus(HttpStatus.OK)
    public boolean isProductStockAvailable(@RequestBody StockDTO stockDTO) {
        return stockService.isStockAvailable(stockDTO);
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public boolean updateStock(@RequestBody StockDTO stockDTO) {
        return stockService.updateStock(stockDTO);
    }

}
