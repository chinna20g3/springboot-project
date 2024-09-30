package com.getmyschool.common.daoimpl;

import java.util.List;  
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.getmyschool.common.converter.SchoolTypeConverter;
import com.getmyschool.common.dao.SchoolTypeDao;
import com.getmyschool.common.domain.SchoolType;
import com.getmyschool.common.dto.SchoolTypeDTO;
import com.getmyschool.common.exception.ResourceNotFoundException;
import com.getmyschool.common.repository.SchoolTypeRepository;


@Transactional
@Service("SchoolTypeDaoImpl")
public class SchoolTypeDaoImpl implements SchoolTypeDao  {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private SchoolTypeRepository schoolTypeRepository;

	@Override 
	public SchoolType saveSchoolType(SchoolTypeDTO schoolTypesDTO) {
		SchoolType schoolTypes = SchoolTypeConverter.getSchoolTypeBySchoolTypeDTO(schoolTypesDTO);
		return schoolTypeRepository.save(schoolTypes);
	}

	@Override
	public List<SchoolType> getAllSchoolType(SchoolTypeDTO schoolTypesDTO) {
		List<SchoolType> returnList = null;
		StringBuffer sqlQuery = new StringBuffer("from SchoolType a where 1=1");

		if (null != schoolTypesDTO.getId())
			sqlQuery.append(" AND a.id = :id");
		if (null != schoolTypesDTO.getStatus())
			sqlQuery.append(" AND a.status = :status");
		
		sqlQuery.append(" order by a.id DESC");
		Query query = entityManager.createQuery(sqlQuery.toString());

		if (null != schoolTypesDTO.getId())
			query.setParameter("id", schoolTypesDTO.getId());
		if (null != schoolTypesDTO.getStatus())
			query.setParameter("status", schoolTypesDTO.getStatus());
		
		// query.setFirstResult(schoolTypeDTO.getOffset());
		/// query.setMaxResults(schoolTypeDTO.getLimit());

		returnList = query.getResultList();

		return returnList;	
	}

	@Override
	public SchoolType getSchoolTypeById(Long id) {
		Optional<SchoolType> schoolType = schoolTypeRepository.findById(id);
		if (!schoolType.isPresent())
			throw new ResourceNotFoundException("The schoolType is not found in the system. id:" + id);
		return schoolType.get();
	}	
	
}
