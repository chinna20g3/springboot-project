package com.getmyschool.common.daoimpl;

import java.util.List; 
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getmyschool.common.converter.NewsLetterConverter;
import com.getmyschool.common.dao.NewsLetterDao;
import com.getmyschool.common.domain.NewsLetter;
import com.getmyschool.common.dto.NewsLetterDTO;
import com.getmyschool.common.exception.ResourceNotFoundException;
import com.getmyschool.common.repository.NewsLetterRepository;

@Transactional
@Service("NewsLetterDaoImpl")

public class NewsLetterDaoImpl implements NewsLetterDao{
		
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private NewsLetterRepository newsLetterRepository;	

	@Override
	public NewsLetter saveNewsLetter(NewsLetterDTO newsPapersDTO) {
		NewsLetter newsLetters = NewsLetterConverter.getNewsLetterByNewsLetterDTO(newsPapersDTO);
		return newsLetterRepository.save(newsLetters);
	}

	@Override
	public List<NewsLetter> getAllNewsLetter(NewsLetterDTO newsLettersDTO) {
		List<NewsLetter> returnList = null;
		StringBuffer sqlQuery = new StringBuffer("from NewsLetter a where 1=1");

		if (null != newsLettersDTO.getId())
			sqlQuery.append(" AND a.id = :id");
		if (null != newsLettersDTO.getStatus())
			sqlQuery.append(" AND a.status = :status");
		if (null != newsLettersDTO.getEmail())
			sqlQuery.append(" AND a.email = :email");
		
		sqlQuery.append(" order by a.id DESC");
		Query query = entityManager.createQuery(sqlQuery.toString());

		if (null != newsLettersDTO.getId())
			query.setParameter("id", newsLettersDTO.getId());
		if (null != newsLettersDTO.getStatus())
			query.setParameter("status", newsLettersDTO.getStatus());

		if (null != newsLettersDTO.getEmail())
			query.setParameter("email", newsLettersDTO.getEmail());

		returnList = query.getResultList();

		return returnList;
	}

	@Override
	public NewsLetter getNewsLetterById(Long id) {
	Optional<NewsLetter> newsLetter =newsLetterRepository.findById(id);
	if (!newsLetter.isPresent())
		throw new ResourceNotFoundException("The newsLetter is not found in the system. id:" + id);
       
	return newsLetter.get();
	}

	@Override
	public NewsLetter getNewsLetterByEmail(String email) {
		return newsLetterRepository.getnewsLetterByEmail(email);
	}

}
