package com.getmyschool.common.daoimpl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getmyschool.common.converter.IpTrackingConverter;
import com.getmyschool.common.dao.IpTrackingDao;
import com.getmyschool.common.domain.IpTracking;
import com.getmyschool.common.dto.IpTrackingDTO;
import com.getmyschool.common.exception.ResourceNotFoundException;
import com.getmyschool.common.repository.IpTrackingRepository;

@Transactional
@Service("IpTrackingDaoImpl")
public class IpTrackingDaoImpl implements IpTrackingDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private IpTrackingRepository ipTrackingRepository;

	@Override
	public IpTracking saveIpTracking(IpTrackingDTO ipTrackingDTO) {
		IpTracking ipTracking = IpTrackingConverter.getIpTrackingByIpTrackingDTO(ipTrackingDTO);
		return ipTrackingRepository.save(ipTracking);
	}

	@Override
	public List<IpTracking> getAllIpTracking(IpTrackingDTO ipTrackingDTO) {
		List<IpTracking> returnList = null;
		StringBuffer sqlQuery =new StringBuffer(" from IpTracking a where 1=1 ");		
		if(null !=ipTrackingDTO.getId())
			sqlQuery.append("AND a.id =:id");
		if(null !=ipTrackingDTO.getStatus())
			sqlQuery.append("AND a.status=:status");		
		
		sqlQuery.append("order by a DESC ");	
		
		Query query =entityManager.createQuery(sqlQuery.toString());	
		if(null !=ipTrackingDTO.getId())
			query.setParameter("id", ipTrackingDTO.getId());
		if(null !=ipTrackingDTO.getStatus())
			query.setParameter("status", ipTrackingDTO.getStatus());
		 returnList =query.getResultList();					
		return returnList;
	}

	@Override
	public IpTracking getIpTrackingById(Long id) {
		Optional<IpTracking> ipTracking = ipTrackingRepository.findById(id);
		if(!ipTracking.isPresent())
			throw new ResourceNotFoundException("the IpTracking is not found in the system.id " +id);
		return ipTracking.get();
	}
}
