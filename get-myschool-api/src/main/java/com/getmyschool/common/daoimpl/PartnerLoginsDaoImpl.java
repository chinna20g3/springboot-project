package com.getmyschool.common.daoimpl;

import java.util.List; 
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getmyschool.common.converter.PartnerLoginsConverter;
import com.getmyschool.common.dao.PartnerLoginsDao;
import com.getmyschool.common.domain.PartnerLogins;
import com.getmyschool.common.dto.PartnerLoginsDTO;
import com.getmyschool.common.exception.ResourceNotFoundException;
import com.getmyschool.common.repository.PartnerLoginsRepository;

@Transactional
@Service("PartnerLoginsDaoImpl")
public class PartnerLoginsDaoImpl implements PartnerLoginsDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private PartnerLoginsRepository partnerLoginsRepository;
	
	@Override
	public PartnerLogins savePartnerLogins(PartnerLoginsDTO partnerLoginsDTO) {
		PartnerLogins partnerLogins = PartnerLoginsConverter.getPartnerLoginsByPartnerLoginsDTO(partnerLoginsDTO);
		return partnerLoginsRepository.save(partnerLogins);
	}

	@Override
	public List<PartnerLogins> getAllPartnerLogins(PartnerLoginsDTO partnerLoginsDTO) {
		List<PartnerLogins> returnList = null;
		StringBuffer sqlQuery =new StringBuffer(" from PartnerLogins a where 1=1 ");
		
		if(null!= partnerLoginsDTO.getId())
			sqlQuery.append("AND a.id = :id");
		if(null!= partnerLoginsDTO.getStatus())
			sqlQuery.append("AND a.status = :status");
		if(null!= partnerLoginsDTO.getEmail())
			sqlQuery.append("And a.email = :email");
		
		sqlQuery.append(" order by a.id DESC");
		Query query = entityManager.createQuery(sqlQuery.toString());
		
		if(null!= partnerLoginsDTO.getId())
			query.setParameter( "id", partnerLoginsDTO.getId());
		if(null!= partnerLoginsDTO.getStatus())
			query.setParameter("status", partnerLoginsDTO.getStatus());
		if(null!= partnerLoginsDTO.getEmail())
			query.setParameter("email", partnerLoginsDTO.getEmail());
			
		returnList = query.getResultList();
		return returnList;
	}

	@Override
	public PartnerLogins getPartnerLoginsById(Long id) {
		Optional<PartnerLogins> partnerLogins = partnerLoginsRepository.findById(id);
		if(!partnerLogins.isPresent())
			throw new ResourceNotFoundException("The partnerLogins is not found in the system. id:" + id);
		  	
			return partnerLogins.get();
	}
	
	@Override
	public PartnerLogins getPartnerLoginsByEmail(String email) {
		return partnerLoginsRepository.getPartnerLoginsByEmail(email);
	}

}
