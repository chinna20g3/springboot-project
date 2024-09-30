package com.getmyschool.common.dao;

import java.util.List;

import com.getmyschool.common.domain.Reviews;
import com.getmyschool.common.dto.ReviewsDTO;

public interface ReviewsDao {

	public Reviews saveReviews(ReviewsDTO reviewsDTO);
	
	public List<Reviews> getAllReviews(ReviewsDTO reviewsDTO);
	
	public Reviews getReviewsById(Long id);
}
