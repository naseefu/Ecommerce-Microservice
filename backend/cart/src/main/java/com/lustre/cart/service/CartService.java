package com.lustre.cart.service;

import com.lustre.cart.dto.Response;
import com.lustre.cart.entity.Cart;
import com.lustre.cart.repo.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepo cartRepo;

    public Response addToCart(Cart cart) {

        Response response = new Response();

        try{

            cart.setDate(LocalDateTime.now());
            cartRepo.save(cart);
            response.setCode(200);
            response.setMessage("Product added to cart");
            return response;

        }
        catch(Exception e){
            response.setCode(500);
            response.setMessage(e.getMessage());
            return response;
        }

    }

    public Response findCart(String id) {

        Response response = new Response();

        try{

            List<Cart> carts = cartRepo.findAllByUserId(id);

            response.setCarts(carts);
            response.setCode(200);
            response.setMessage("Cart found");
            return response;

        }
        catch(Exception e){
            response.setCode(500);
            response.setMessage(e.getMessage());
            return response;
        }

    }

    public String deleteCartOfUser(String id,String prodId) {

        try{

            cartRepo.deleteAllByUserIdAndProductId(id,prodId);
            return "success";

        }
        catch(Exception e){
            return e.getMessage();
        }

    }
}
