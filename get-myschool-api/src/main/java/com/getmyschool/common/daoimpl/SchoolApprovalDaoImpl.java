package com.getmyschool.common.daoimpl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getmyschool.common.converter.SchoolApprovalConverter;
import com.getmyschool.common.dao.SchoolApprovalDao;
import com.getmyschool.common.domain.SchoolApproval;
import com.getmyschool.common.dto.SchoolApprovalDTO;
import com.getmyschool.common.exception.ResourceNotFoundException;
import com.getmyschool.common.repository.SchoolApprovalRepository;

@Transactional
@Service("SchoolApprovalDaoImpl")
public class SchoolApprovalDaoImpl implements SchoolApprovalDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private SchoolApprovalRepository schoolApprovalRepository;
	
	@Override
	public SchoolApproval saveSchoolApproval(SchoolApprovalDTO schoolApprovalDTO) {
		SchoolApproval schoolApprovals = SchoolApprovalConverter.getSchoolApprovalBySchoolApprovalDTO(schoolApprovalDTO);
		return schoolApprovalRepository.save(schoolApprovals);
	}

	@Override
	public List<SchoolApproval> getAllSchoolApproval(SchoolApprovalDTO schoolApprovalDTO) {
		List<SchoolApproval> returnList =null;
		StringBuffer sqlQuery =new StringBuffer("from SchoolApproval a where 1=1 ");
		if(null != schoolApprovalDTO.getId())
			sqlQuery.append("AND a.id =:id");
		if(null != schoolApprovalDTO.getStatus())
			sqlQuery.append("AND a.status =:status");
		
		sqlQuery.append("order by a.id DESC");
		Query query =entityManager.createQuery(sqlQuery.toString());
		if(null != schoolApprovalDTO.getId())
			query.setParameter("id", schoolApprovalDTO.getId());
		if(null != schoolApprovalDTO.getStatus())
			query.setParameter("id", schoolApprovalDTO.getStatus());
			
		returnList = query.getResultList();
		return returnList;
	}

	@Override
	public SchoolApproval getSchoolApprovalById(Long id) {
		Optional<SchoolApproval> schoolApproval = schoolApprovalRepository.findById(id);	
		if(!schoolApproval.isPresent())
			throw new ResourceNotFoundException("The SchoolApproval  is not found in System. id:" +id );
		return schoolApproval.get();
	}

}
