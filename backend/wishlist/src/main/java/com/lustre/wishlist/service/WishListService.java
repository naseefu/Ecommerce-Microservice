package com.lustre.wishlist.service;

import com.lustre.wishlist.dto.Response;
import com.lustre.wishlist.entity.Wishlist;
import com.lustre.wishlist.repository.WishListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishListService {

    @Autowired
    private WishListRepo wishListRepo;

    public Response addToWishList(Wishlist wishlist) {

        Response response = new Response();

        try {

            wishListRepo.save(wishlist);

            response.setCode(200);
            response.setMessage("Added to Wishlist");
            return response;

        }
        catch(Exception e) {
            response.setCode(500);
            response.setMessage(e.getMessage());
            return response;
        }

    }

    public Response removeFromWishList(String id) {

        Response response = new Response();

        try{

            wishListRepo.deleteById(id);
            response.setCode(200);
            response.setMessage("Removed From Wishlist");
            return response;

        }
        catch(Exception e) {
            response.setCode(500);
            response.setMessage(e.getMessage());
            return response;
        }

    }


}
