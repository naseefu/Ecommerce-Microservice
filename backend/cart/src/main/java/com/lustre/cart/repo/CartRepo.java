package com.lustre.cart.repo;

import com.lustre.cart.entity.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CartRepo extends MongoRepository<Cart, String> {
    List<Cart> findAllByUserId(String id);

    void deleteAllByUserId(String id);

    void deleteAllByUserIdAndProductId(String id, String prodId);

    List<Cart> findAllByDate(LocalDateTime date);

    @Query("{ 'date' : { $gte: ?0 } }")
    List<Cart> findAllByDateMore(LocalDateTime date);
}
