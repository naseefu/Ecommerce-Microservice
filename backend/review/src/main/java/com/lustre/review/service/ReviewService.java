package com.lustre.review.service;

import com.lustre.review.dto.Response;
import com.lustre.review.entity.Review;
import com.lustre.review.repository.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepo reviewRepo;

    public Response addReview(Review review) {

        Response response = new Response();

        try{

            review.setDate(LocalDateTime.now());
            reviewRepo.save(review);
            response.setCode(200);
            response.setMessage("Review Added");
            return response;

        }
        catch(Exception e){
            response.setCode(500);
            response.setMessage(e.getMessage());
            return response;
        }

    }

    public Response deleteReview(String userId, String reviewId) {

        Response response = new Response();

        try {

            if(reviewRepo.existsByIdAndUserId(reviewId,userId)) {

                reviewRepo.deleteByIdAndUserId(reviewId,userId);
                response.setCode(200);
                response.setMessage("Review Deleted");

            }
            else{
                response.setCode(404);
                response.setMessage("Review Not Found");
            }
            return response;
        }
        catch(Exception e){
            response.setCode(500);
            response.setMessage(e.getMessage());
            return response;
        }

    }

    public Response getAllReview(String productId) {

        Response response = new Response();

        try{

            List<Review> reviews = reviewRepo.findAllByProductId(productId);
            response.setCode(200);
            response.setMessage("Reviews Found");
            response.setReviews(reviews);
            return response;

        }
        catch(Exception e){
            response.setCode(500);
            response.setMessage(e.getMessage());
            return response;
        }

    }

}
