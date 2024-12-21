package com.lustre.wishlist.controller;

import com.lustre.wishlist.dto.Response;
import com.lustre.wishlist.entity.Wishlist;
import com.lustre.wishlist.service.WishListService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishListService wishListService;

    @PostMapping("/add")
    public ResponseEntity<Response> addToWishlist(@RequestBody @Valid Wishlist wishlist) {

        Response r = wishListService.addToWishList(wishlist);
        return ResponseEntity.status(r.getCode()).body(r);

    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Response> removeFromWishlist(@PathVariable String id) {

        Response r = wishListService.removeFromWishList(id);
        return ResponseEntity.status(r.getCode()).body(r);

    }

}
