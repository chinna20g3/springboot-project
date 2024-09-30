package com.getmyschool.college.serviceimpl;

import java.util.ArrayList; 
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.getmyschool.college.service.ReviewsService;
import com.getmyschool.common.converter.ReviewsConverter;
import com.getmyschool.common.dao.ReviewsDao;
import com.getmyschool.common.domain.Reviews;
import com.getmyschool.common.domain.Role;
import com.getmyschool.common.dto.ReviewsDTO;
import com.getmyschool.common.exception.UnAuthorizedException;
import com.getmyschool.common.service.LoginService;
import com.getmyschool.common.validator.RoleEnum;

@Service("ReviewsServiceImpl")
public class ReviewsServiceImpl implements ReviewsService{
	
	
	@Resource(name = "LoginServiceImpl")
	private LoginService loginService;
	
	@Resource(name  = "ReviewsDaoImpl")
	private ReviewsDao reviewsDao;

	private static final Logger LOGGER = LoggerFactory.getLogger(SchoolApprovalServiceImpl.class);

	@Override
	public void saveReviews(ReviewsDTO reviewsDTO) {
		List<Role> roles = loginService.getAllUserRoles(reviewsDTO.getCreatedBy());
		boolean adminFlag= roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if(!(adminFlag)) {
			throw new UnAuthorizedException("Login Reviews does not  have permission to save SchoolType Details.");
		}
		Reviews reviews = reviewsDao.saveReviews(reviewsDTO);
		LOGGER.info("The Reviews is added Successfully with saveReview :: " + reviews.getReviewStar());
					
	}

	@Override
	public List<ReviewsDTO> getAllReviews(ReviewsDTO reviewsDTO) {
		List<Role> roles = loginService.getAllUserRoles(reviewsDTO.getUpdatedBy());
		boolean adminAccess =roles.stream().anyMatch(x ->x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if(!adminAccess)
			throw new UnAuthorizedException("Login Reviews does not have permission to save Reviews Details.");
		List<Reviews> reviewsList = reviewsDao.getAllReviews(reviewsDTO);
	    List<ReviewsDTO> returnList = new ArrayList<>();
			    
	    for (Reviews reviews : reviewsList) {
	    	ReviewsDTO dbReviewsDTO = ReviewsConverter.getReviewsDTOByReviews(reviews);
	    	returnList.add(dbReviewsDTO);
	    }
	    return returnList;
	}		

	@Override
	public ReviewsDTO getReviewsById(ReviewsDTO reviewsDTO) {

		List<Role> roles = loginService.getAllUserRoles(reviewsDTO.getUpdatedBy());
		boolean adminAccess =roles.stream().anyMatch(x ->x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if(!adminAccess) {
			if (!reviewsDTO.getId().equals(reviewsDTO.getUpdatedBy()))
				throw new UnAuthorizedException("LogedIn reviews does't have permission to get Reviews Details.");
		}
		Reviews reviews = reviewsDao.getReviewsById(reviewsDTO.getId());
		return ReviewsConverter.getReviewsDTOByReviews(reviews);
	}

	@Override
	public void updateReviews(ReviewsDTO reviewsDTO) {
     
		List<Role> roles  =loginService.getAllUserRoles(reviewsDTO.getUpdatedBy());
		boolean adminAccess =roles.stream().anyMatch(x ->x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if (!adminAccess) 
			if (!reviewsDTO.getId().equals(reviewsDTO.getUpdatedBy()))
				throw new UnAuthorizedException("LogedIn School does't have permission to update School Details.");
		Reviews reviews =reviewsDao.getReviewsById(reviewsDTO.getId());
		ReviewsDTO dbReviewsDTO = ReviewsConverter.getReviewsDTOByReviews(reviews);
		
		if (null != reviewsDTO.getReviewStar())
			dbReviewsDTO.setReviewStar(reviewsDTO.getReviewStar());
		
		dbReviewsDTO.setUpdatedBy(reviewsDTO.getUpdatedBy());
		dbReviewsDTO.setUpdatedDate(reviewsDTO.getUpdatedDate());

		reviewsDao.saveReviews(dbReviewsDTO);
		LOGGER.info("Reviews details for reviews id " + reviewsDTO.getId() + " are updated successfully.");
	}

	@Override
	public void deleteReviews(ReviewsDTO reviewsDTO) {
		List<Role> roles = loginService.getAllUserRoles(reviewsDTO.getUpdatedBy());
	    boolean adminAccess = roles.stream().anyMatch(x ->
	            x.getRole().equals(RoleEnum.ADMIN.getRole()));

	    if (!adminAccess) {
	        // Check if the logged-in partnerLogins has permission to delete status
	        if (!reviewsDTO.getId().equals(reviewsDTO.getUpdatedBy())) 
	            throw new UnAuthorizedException("Logged-in Reviews doesn't have permission to delete status.");
	    }
	    
	    Reviews reviews =reviewsDao.getReviewsById(reviewsDTO.getId());
		ReviewsDTO dbReviewsDTO = ReviewsConverter.getReviewsDTOByReviews(reviews);
		
	    // Perform deletion logic here, such as updating the status field in the database
		dbReviewsDTO.setStatus(reviewsDTO.getStatus());
		dbReviewsDTO.setUpdatedBy(reviewsDTO.getUpdatedBy());
		dbReviewsDTO.setUpdatedDate(reviewsDTO.getUpdatedDate());
	    LOGGER.info("Status for reviews with ID " + reviewsDTO.getStatus() + " is deleted successfully.");
	}

}		
	
