package com.lustre.cart.dto;

import com.lustre.cart.entity.Cart;

import java.util.List;

public class Response {

    private String message;
    private int code;

    private List<Cart> carts;

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
