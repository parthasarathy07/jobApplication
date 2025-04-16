package com.JobApplication.review;

import java.util.List;

public interface ReviewService {
     List<Review> getAllReviews(Long id);
     Boolean addReview(Long id,Review review);
     Review getReviewById(Long companyId, Long reviewId);
     Boolean updateReview(Long companyId, Long reviewId,Review review);
     Boolean deleteReview(Long companyId, Long reviewId);
}
