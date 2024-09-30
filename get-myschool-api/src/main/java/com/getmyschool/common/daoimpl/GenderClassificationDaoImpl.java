package com.getmyschool.common.daoimpl;

import java.util.List; 
import java.util.Optional;

import javax.persistence.EntityManager; 
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getmyschool.common.converter.GenderClassificationConverter;
import com.getmyschool.common.dao.GenderClassificationDao;
import com.getmyschool.common.domain.GenderClassification;
import com.getmyschool.common.dto.GenderClassificationDTO;
import com.getmyschool.common.exception.ResourceNotFoundException;
import com.getmyschool.common.repository.GenderClassificationRepository;

@Transactional
@Service("GenderClassificationDaoImpl")

public class GenderClassificationDaoImpl  implements GenderClassificationDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private  GenderClassificationRepository genderClassificationRepository;
	
	@Override
	public GenderClassification saveGenderClassification(GenderClassificationDTO genderClassificationsDTO) {
		GenderClassification genderClassifications= GenderClassificationConverter.getGenderClassificationByGenderClassificationDTO(genderClassificationsDTO);
        return genderClassificationRepository.save(genderClassifications);
	}

	@Override
	public List<GenderClassification> getAllGenderClassification(GenderClassificationDTO genderClassificationsDTO) {
		List<GenderClassification> returnList = null;
		StringBuffer sqlQuery = new StringBuffer("from GenderClassification a where 1=1");

		if (null != genderClassificationsDTO.getId())
			sqlQuery.append(" AND a.id = :id");
		if (null != genderClassificationsDTO.getStatus())
			sqlQuery.append(" AND a.status = :status");
		
		sqlQuery.append(" order by a.id DESC");
		Query query = entityManager.createQuery(sqlQuery.toString());

		if (null != genderClassificationsDTO.getId())
			query.setParameter("id", genderClassificationsDTO.getId());
		if (null != genderClassificationsDTO.getStatus())
			query.setParameter("status", genderClassificationsDTO.getStatus());
		
		// query.setFirstResult(genderClassificationDTO.getOffset());
		/// query.setMaxResults(genderClassificationDTO.getLimit());

		returnList = query.getResultList();

		return returnList;
	}

	@Override
	public GenderClassification getGenderClassificationById(Long id) {
		Optional<GenderClassification> genderClassification = genderClassificationRepository.findById(id);
		if (!genderClassification.isPresent())
			throw new ResourceNotFoundException("The genderClassification is not found in the system. id:" + id);
		return genderClassification.get();
	}

}
