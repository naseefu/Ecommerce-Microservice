package com.lustre.review.controller;

import com.lustre.review.dto.Response;
import com.lustre.review.entity.Review;
import com.lustre.review.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/add")
    public ResponseEntity<Response> addReview(@RequestBody @Valid Review review) {

        Response r = reviewService.addReview(review);
        return ResponseEntity.status(r.getCode()).body(r);

    }

    @DeleteMapping("/delete/{id1}/{id2}")
    public ResponseEntity<Response> deleteReview(@PathVariable String id1, @PathVariable String id2) {

        Response r1 = new Response();
        r1.setCode(500);
        r1.setMessage("error");

        Response r = !id1.isBlank()&&!id2.isBlank() ? reviewService.deleteReview(id1, id2) : r1;
        return ResponseEntity.status(r.getCode()).body(r);

    }

    @GetMapping("/all/{id}")
    public ResponseEntity<Response> getAllReviews(@PathVariable String id) {

        Response r1 = new Response();
        r1.setCode(500);
        r1.setMessage("error");

        Response r = id!=null? reviewService.getAllReview(id):r1;
        return ResponseEntity.status(r.getCode()).body(r);

    }

}
