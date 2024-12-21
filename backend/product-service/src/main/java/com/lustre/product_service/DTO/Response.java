package com.lustre.product_service.DTO;

import com.lustre.product_service.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public class Response {

    private int code;
    private String message;

    private Page<Product> products;
    private Product product;

    private Stocks stocks;

    public Stocks getStocks() {
        return stocks;
    }

    public void setStocks(Stocks stocks) {
        this.stocks = stocks;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Page<Product> getProducts() {
        return products;
    }

    public void setProducts(Page<Product> products) {
        this.products = products;
    }
}
