package com.lustre.wishlist.repository;

import com.lustre.wishlist.entity.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepo extends MongoRepository<Wishlist,String> {
}
