package com.JobApplication.review.impl;

import com.JobApplication.company.Company;
import com.JobApplication.company.CompanyService;
import com.JobApplication.review.Review;
import com.JobApplication.review.ReviewRepository;
import com.JobApplication.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    ReviewRepository repository;
    CompanyService companyService;
    ReviewServiceImpl(ReviewRepository repository,CompanyService companyService){
        this.companyService=companyService;
        this.repository=repository;
    }
    @Override
    public List<Review> getAllReviews(Long id) {
        return repository.findByCompanyId(id);
    }

    @Override
    public Boolean addReview(Long id, Review review) {
        Company company=companyService.findCompanyBYId(id);
        if(company!=null){
            review.setCompany(company);
            repository.save(review);
            return true;
        }else return false;
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        List<Review> list=repository.findByCompanyId(companyId);
        return list.stream()
                .filter(review ->reviewId.equals(review.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Boolean updateReview(Long companyId, Long reviewId, Review review1) {
        List<Review> list=repository.findByCompanyId(companyId);
        Review review2= list.stream()
                .filter(review ->reviewId.equals(review.getId()))
                .findFirst()
                .orElse(null);
        if(review2==null) return false;
        review2.setRating(review1.getRating());
        review2.setDescription(review1.getDescription());
        repository.save(review2);
        return true;
    }

    @Override
    public Boolean deleteReview(Long companyId, Long reviewId) {
       Company company= companyService.findCompanyBYId(companyId);
       if(company!=null&&repository.existsById(reviewId)){
          repository.deleteById(reviewId);
          return true;
       }return false;
    }
}
