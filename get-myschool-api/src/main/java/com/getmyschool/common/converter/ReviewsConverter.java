package com.getmyschool.common.converter;

import org.springframework.stereotype.Component;

import com.getmyschool.common.domain.Reviews;
import com.getmyschool.common.dto.ReviewsDTO;

@Component
public class ReviewsConverter {

	/**
	 *@Converter Reviews to ReviewsDTO
	 *@Param Reviews
	 *@return
	 */
	
	public static ReviewsDTO getReviewsDTOByReviews(Reviews reviews) {
		ReviewsDTO dto =new ReviewsDTO();
		
		dto.setId(reviews.getId());
		dto.setReviewStar(reviews.getReviewStar());
		dto.setStatus(reviews.getStatus());
		dto.setCreatedBy(reviews.getCreatedBy());
		dto.setCreatedDate(reviews.getCreatedDate());
		dto.setUpdatedBy(reviews.getUpdatedBy());
		dto.setUpdatedDate(reviews.getUpdatedDate());

		return dto;
	}
	
	/**
	 *@Converter ReviewsDTO to Reviews
	 *@Param ReviewsDTO
	 *@return
	 */
	
	public static Reviews getReviewsByReviewsDTO(ReviewsDTO reviewsDTO) {
		Reviews reviews =new Reviews();
		
		reviews.setId(reviewsDTO.getId());
		reviews.setReviewStar(reviewsDTO.getReviewStar());
		reviews.setStatus(reviewsDTO.getStatus());
		reviews.setCreatedBy(reviewsDTO.getCreatedBy());
		reviews.setCreatedDate(reviewsDTO.getCreatedDate());
		reviews.setUpdatedBy(reviewsDTO.getUpdatedBy());
		reviews.setUpdatedDate(reviewsDTO.getUpdatedDate());
			
		return reviews;
	}

}
