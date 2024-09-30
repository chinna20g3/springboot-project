package com.getmyschool.common.daoimpl;

import java.util.List;  
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getmyschool.common.converter.LeadsManagerConverter;
import com.getmyschool.common.dao.LeadsManagerDao;
import com.getmyschool.common.domain.LeadsManager;
import com.getmyschool.common.dto.LeadsManagerDTO;
import com.getmyschool.common.exception.ResourceNotFoundException;
import com.getmyschool.common.repository.LeadsManagerRepository;

@Transactional
@Service("LeadsManagerDaoImpl")
public class LeadsManagerDaoImpl implements LeadsManagerDao{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private LeadsManagerRepository leadsManagerRepository;

	
	@Override
	public LeadsManager saveLeadsManager(LeadsManagerDTO leadsManagersDTO) {
		LeadsManager leadsManagers = LeadsManagerConverter.getLeadsManagerByLeadsManagerDTO(leadsManagersDTO);
		return leadsManagerRepository.save(leadsManagers);
	}

	@Override
	public List<LeadsManager> getAllLeadsManager(LeadsManagerDTO leadsManagersDTO) {
		List<LeadsManager> returnList = null;
		StringBuffer sqlQuery = new StringBuffer("from LeadsManager a where 1=1");

		if (null != leadsManagersDTO.getId())
			sqlQuery.append(" AND a.id = :id");
		if (null != leadsManagersDTO.getStatus())
			sqlQuery.append(" AND a.Status = :Status");
		if (null != leadsManagersDTO.getEmail())
			sqlQuery.append(" AND a.email = :email");

		sqlQuery.append(" order by a.id DESC");
		Query query = entityManager.createQuery(sqlQuery.toString());

		if (null != leadsManagersDTO.getId())
			query.setParameter("id", leadsManagersDTO.getId());
		if (null != leadsManagersDTO.getStatus())
			query.setParameter("Status", leadsManagersDTO.getStatus());
		if (null != leadsManagersDTO.getEmail())
			query.setParameter("email", leadsManagersDTO.getEmail());

		returnList = query.getResultList();

		return returnList;
	}

	@Override
	public LeadsManager getLeadsManagerById(Long id) {
		Optional<LeadsManager> leadsManager = leadsManagerRepository.findById(id);
		if (!leadsManager.isPresent())
			throw new ResourceNotFoundException("The leadsManager is not found in the system. id:" + id);
		return leadsManager.get();
	}

	@Override
	public LeadsManager getLeadsManagerByEmail(String email) {
		return leadsManagerRepository.getLeadsManagerByEmail(email);
	}

	
}
