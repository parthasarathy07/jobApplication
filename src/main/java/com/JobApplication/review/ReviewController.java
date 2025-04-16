package com.JobApplication.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company/{companyId}/reviews")
public class ReviewController {
    ReviewService service;
    ReviewController(ReviewService service){
        this.service=service;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        return new ResponseEntity<>(service.getAllReviews(companyId), HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId,@PathVariable Long reviewId){
        Review review=service.getReviewById(companyId,reviewId);
        if(review!=null){
            return new ResponseEntity<>(review,HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> addReview(@PathVariable Long companyId,@RequestBody Review review){
        Boolean isInserted=service.addReview(companyId, review);
        if(isInserted){
            return new ResponseEntity<>("insertion success",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("insertion fails companyId not found",HttpStatus.FOUND);
        }
    }
    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,@PathVariable Long reviewId
                                                  ,@RequestBody Review review){
        if(service.updateReview(companyId,reviewId,review)){
            return new ResponseEntity<>("update success",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("update fails",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId,@PathVariable Long reviewId){
        Boolean isDeleted =service.deleteReview(companyId,reviewId);
        if(isDeleted){
            return new ResponseEntity<>("deleted successfully",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("deletion failed",HttpStatus.NOT_FOUND);
        }
    }
}
