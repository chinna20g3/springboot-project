package com.getmyschool.common.daoimpl;

import java.util.List; 
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getmyschool.common.converter.ReviewsConverter;
import com.getmyschool.common.dao.ReviewsDao;
import com.getmyschool.common.domain.Reviews;
import com.getmyschool.common.dto.ReviewsDTO;
import com.getmyschool.common.exception.ResourceNotFoundException;
import com.getmyschool.common.repository.ReviewsRepository;

@Transactional
@Service("ReviewsDaoImpl")
public class ReviewsDaoImpl implements ReviewsDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private ReviewsRepository reviewsRepository;
	
	@Override
	public Reviews saveReviews(ReviewsDTO reviewsDTO) {
		// TODO Auto-generated method stub
		Reviews reviews =ReviewsConverter.getReviewsByReviewsDTO(reviewsDTO);
		return reviewsRepository.save(reviews);
	}

	@Override
	public List<Reviews> getAllReviews(ReviewsDTO reviewsDTO) {
		// TODO Auto-generated method stub
		List<Reviews> returnList =null;
		StringBuffer sqlQuery = new StringBuffer("from Reviews a where 1=1");
		if (null != reviewsDTO.getId())
			 sqlQuery.append("AND a.id =:id");
		if(null != reviewsDTO.getStatus())
			sqlQuery.append("AND a.status =:status");
		
		sqlQuery.append(" order by a.id DESC");
		
		Query query = entityManager.createQuery(sqlQuery.toString());
		if (null != reviewsDTO.getId())
			 query.setParameter("id", reviewsDTO.getId());
		if(null != reviewsDTO.getStatus())
			query.setParameter("status", reviewsDTO.getStatus());
			
		returnList = query.getResultList();
		return returnList;
	}

	@Override
	public Reviews getReviewsById(Long id) {
		// TODO Auto-generated method stub
		Optional<Reviews> reviews = reviewsRepository.findById(id);
		if(!reviews.isPresent())
			throw new ResourceNotFoundException("The Reviews is not found in the system.id"  +id);
		return reviews.get();
	}

}
