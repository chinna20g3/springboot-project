package com.getmyschool.common.daoimpl;

import java.util.List;    
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.getmyschool.common.converter.AmenitiesConverter;
import com.getmyschool.common.dao.AmenitiesDao;
import com.getmyschool.common.domain.Amenities;
import com.getmyschool.common.dto.AmenitiesDTO;
import com.getmyschool.common.exception.ResourceNotFoundException;
import com.getmyschool.common.repository.AmenitiesRepository;

@Transactional
@Service("AmenitiesDaoImpl")
public class AmenitiesDaoImpl implements AmenitiesDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private  AmenitiesRepository amenitiesRepository;
	
	@Override
	public Amenities saveAmenities(AmenitiesDTO amenitiesDTO) {
		Amenities amenities= AmenitiesConverter.getAmenitiesByAmenitiesDTO(amenitiesDTO);
		return amenitiesRepository.save(amenities);
	}

	@Override
	public List<Amenities> getAllAmenities(AmenitiesDTO amenitiesDTO) {
		List<Amenities> returnList = null;
		StringBuffer sqlQuery = new StringBuffer("from Amenities a where 1=1");

		if (null != amenitiesDTO.getId())
			sqlQuery.append(" AND a.id = :id");
		if (null != amenitiesDTO.getStatus())
			sqlQuery.append(" AND a.status = :status");
		
		sqlQuery.append(" order by a.id DESC");
		Query query = entityManager.createQuery(sqlQuery.toString());

		if (null != amenitiesDTO.getId())
			query.setParameter("id", amenitiesDTO.getId());
		if (null != amenitiesDTO.getStatus())
			query.setParameter("status", amenitiesDTO.getStatus());
		
		// query.setFirstResult(amenitiesDTO.getOffset());
		/// query.setMaxResults(amenitiesDTO.getLimit());

		returnList = query.getResultList();

		return returnList;
	}

	@Override
	public Amenities getAmenitiesById(Long id) {
		Optional<Amenities> amenities = amenitiesRepository.findById(id);
		if (!amenities.isPresent())
			throw new ResourceNotFoundException("The amenities is not found in the system. id:" + id);
		return amenities.get();
	}
	
}
	
	


