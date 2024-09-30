package com.getmyschool.common.daoimpl;

import java.util.List; 
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getmyschool.common.converter.GradeClassificationConverter;
import com.getmyschool.common.dao.GradeClassificationDao;
import com.getmyschool.common.domain.GradeClassification;
import com.getmyschool.common.dto.GradeClassificationDTO;
import com.getmyschool.common.exception.ResourceNotFoundException;
import com.getmyschool.common.repository.GradeClassificationRepository;


@Transactional
@Service("GradeClassificationDaoImpl")
public  class GradeClassificationDaoImpl implements  GradeClassificationDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private  GradeClassificationRepository gradeClassificationRepository;	
	
	@Override
	public GradeClassification saveGradeClassification(GradeClassificationDTO gradeClassificationsDTO) {
		GradeClassification gradeClassifications= GradeClassificationConverter.getGradeClassificationByGradeClassificationDTO(gradeClassificationsDTO);
		return gradeClassificationRepository.save(gradeClassifications);
	}

	@Override
	public List<GradeClassification> getAllGradeClassification(GradeClassificationDTO gradeClassificationsDTO) {
		List<GradeClassification> returnList = null;
		StringBuffer sqlQuery = new StringBuffer("from GradeClassification a where 1=1");

		if (null != gradeClassificationsDTO.getId())
			sqlQuery.append(" AND a.id = :id");
		if (null != gradeClassificationsDTO.getStatus())
			sqlQuery.append(" AND a.status = :status");
		
		sqlQuery.append(" order by a.id DESC");
		Query query = entityManager.createQuery(sqlQuery.toString());

		if (null != gradeClassificationsDTO.getId())
			query.setParameter("id", gradeClassificationsDTO.getId());
		if (null != gradeClassificationsDTO.getStatus())
			query.setParameter("status", gradeClassificationsDTO.getStatus());
		
		// query.setFirstResult(gradeClassificationDTO.getOffset());
		/// query.setMaxResults(gradeClassificationDTO.getLimit());

		returnList = query.getResultList();

		return returnList;
	}

	@Override
	public GradeClassification getGradeClassificationById(Long id) {
		Optional<GradeClassification> gradeClassification = gradeClassificationRepository.findById(id);
		if (!gradeClassification.isPresent())
			throw new ResourceNotFoundException("The gradeClassification is not found in the system. id:" + id);
		return gradeClassification.get();
	}
	
}
