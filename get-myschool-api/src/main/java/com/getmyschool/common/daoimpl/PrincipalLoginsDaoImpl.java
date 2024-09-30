package com.getmyschool.common.daoimpl;

import java.util.List; 
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getmyschool.common.converter.PrincipalLoginsConverter;
import com.getmyschool.common.dao.PrincipalLoginsDao;
import com.getmyschool.common.domain.PrincipalLogins;
import com.getmyschool.common.dto.PrincipalLoginsDTO;
import com.getmyschool.common.exception.ResourceNotFoundException;
import com.getmyschool.common.repository.PrincipalLoginsRepository;

@Transactional
@Service("PrincipalLoginsDaoImpl")
public class PrincipalLoginsDaoImpl implements PrincipalLoginsDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private PrincipalLoginsRepository principalLoginsRepository;
	
	@Override
	public PrincipalLogins savePrincipalLogins(PrincipalLoginsDTO principalLoginsDTO) {
		PrincipalLogins principalLogins = PrincipalLoginsConverter.getPrincipalLoginsByPrincipalLoginsDTO(principalLoginsDTO);
		return principalLoginsRepository.save(principalLogins);
	}

	@Override
	public List<PrincipalLogins> getAllPrincipalLogins(PrincipalLoginsDTO principalLoginsDTO) {
		List<PrincipalLogins> returnList = null;
		StringBuffer sqlQuery = new StringBuffer("from principalLogins a where 1=1");
		
		if (null != principalLoginsDTO.getId())
			sqlQuery.append(" AND a.id = :id");
		if (null != principalLoginsDTO.getStatus())
			sqlQuery.append(" AND a.status = :status");
		if (null != principalLoginsDTO.getEmail())
			sqlQuery.append(" AND a.email = :email");
		
		sqlQuery.append(" order by a.id DESC");
		Query query =entityManager.createQuery(sqlQuery.toString());
		
		if (null != principalLoginsDTO.getId())
			query.setParameter("id", principalLoginsDTO.getId());
		if (null != principalLoginsDTO.getStatus())
			query.setParameter("status", principalLoginsDTO.getStatus());
	    if (null != principalLoginsDTO.getEmail())
			query.setParameter("email", principalLoginsDTO.getEmail());	
	    
	    returnList = query.getResultList();
	    
		return returnList;
	}

	@Override
	public PrincipalLogins getPrincipalLoginsById(Long id) {

		Optional<PrincipalLogins> principalLogins =principalLoginsRepository.findById(id);
		if(!principalLogins.isPresent())
			throw new ResourceNotFoundException("The PrincipalLogins is not Found in the system.id" + id);
		return principalLogins.get();
	}

}
