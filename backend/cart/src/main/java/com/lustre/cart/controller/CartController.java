package com.lustre.cart.controller;

import com.lustre.cart.dto.Response;
import com.lustre.cart.entity.Cart;
import com.lustre.cart.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<Response> addToCart(@RequestBody @Valid Cart cart) {
        Response r = cartService.addToCart(cart);
        return ResponseEntity.status(r.getCode()).body(r);
    }

    @GetMapping("/get-cart/{id}")
    public ResponseEntity<Response> getCart(@PathVariable String id) {
        Response r = cartService.findCart(id);
        return ResponseEntity.status(r.getCode()).body(r);
    }

    @GetMapping("/get-cart-order/{id}")
    public List<Cart> getCartForOrder(@PathVariable String id) {
        Response r = cartService.findCart(id);
        return r.getCarts();
    }

    @DeleteMapping("/user-cart/{id}/{prodId}")
    public String deleteCart(@PathVariable String id,@PathVariable String prodId) {

        return cartService.deleteCartOfUser(id,prodId);

    }

}
