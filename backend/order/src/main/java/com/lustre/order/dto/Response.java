package com.lustre.order.dto;

import com.lustre.order.client.StockDTO;

import java.util.List;

public class Response {

    private int code;
    private String message;

    private List<StockDTO> stock;

    public List<StockDTO> getStock() {
        return stock;
    }

    public void setStock(List<StockDTO> stock) {
        this.stock = stock;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
