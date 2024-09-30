package com.getmyschool.common.dao;

import java.util.List;

import com.getmyschool.common.domain.GenderClassification;
import com.getmyschool.common.dto.GenderClassificationDTO;

public interface GenderClassificationDao {
	
	public GenderClassification saveGenderClassification(GenderClassificationDTO genderClassificationsDTO);

	public List<GenderClassification> getAllGenderClassification(GenderClassificationDTO genderClassificationsDTO);

	public GenderClassification getGenderClassificationById(Long id);

}
