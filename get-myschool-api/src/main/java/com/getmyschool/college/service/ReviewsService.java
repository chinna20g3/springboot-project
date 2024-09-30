package com.getmyschool.college.service;

import java.util.List;

import com.getmyschool.common.dto.ReviewsDTO;

public interface ReviewsService {

	public void  saveReviews(ReviewsDTO reviewsDTO)   ;
	
	public List<ReviewsDTO>  getAllReviews(ReviewsDTO reviewsDTO);

	public ReviewsDTO getReviewsById(ReviewsDTO reviewsDTO);
	
	public void updateReviews(ReviewsDTO reviewsDTO);
	
	public void deleteReviews(ReviewsDTO reviewsDTO);
}
