package com.getmyschool.common.daoimpl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getmyschool.common.converter.SchoolConverter;
import com.getmyschool.common.dao.SchoolDao;
import com.getmyschool.common.domain.School;
import com.getmyschool.common.dto.SchoolDTO;
import com.getmyschool.common.exception.ResourceNotFoundException;
import com.getmyschool.common.repository.SchoolRepository;

@Transactional
@Service("SchoolDaoImpl")
public class SchoolDaoImpl  implements SchoolDao{

	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private SchoolRepository  schoolRepository;
	
	@Override
	public School saveSchool(SchoolDTO schoolsDTO) {
		School schools = SchoolConverter.getSchoolBySchoolDTO(schoolsDTO);
		return schoolRepository.save(schools);
	}

	@Override
	public List<School> getAllSchool(SchoolDTO schoolsDTO) {
		List<School> returnList = null;
		StringBuffer sqlQuery = new StringBuffer("from School a where 1=1");

		if (null != schoolsDTO.getId())
			sqlQuery.append(" AND a.id = :id");
		if (null != schoolsDTO.getStatus())
			sqlQuery.append(" AND a.status = :status");
		
		sqlQuery.append(" order by a.id DESC");
		Query query = entityManager.createQuery(sqlQuery.toString());

		if (null != schoolsDTO.getId())
			query.setParameter("id", schoolsDTO.getId());
		if (null != schoolsDTO.getStatus())
			query.setParameter("status", schoolsDTO.getStatus());

		returnList = query.getResultList();

		return returnList;
	}

	@Override
	public School getSchoolById(Long id) {
		Optional<School> school = schoolRepository.findById(id);
		if (!school.isPresent())
			throw new ResourceNotFoundException("The school is not found in the system. id:" + id);
		return school.get();
	}

}
