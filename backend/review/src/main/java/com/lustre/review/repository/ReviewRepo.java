package com.lustre.review.repository;

import com.lustre.review.entity.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo extends MongoRepository<Review,String> {
    void deleteByIdAndUserId(String reviewId, String userId);

    boolean existsByIdAndUserId(String reviewId, String userId);

    List<Review> findAllByProductId(String productId);
}
