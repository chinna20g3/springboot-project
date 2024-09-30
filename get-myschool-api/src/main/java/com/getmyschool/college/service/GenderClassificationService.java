package com.getmyschool.college.service;

import java.util.List;

import com.getmyschool.common.dto.GenderClassificationDTO;

public interface GenderClassificationService {
	
	public void saveGenderClassification(GenderClassificationDTO genderClassificationDTO);

	public List<GenderClassificationDTO> getAllGenderClassifications(GenderClassificationDTO genderClassificationDTO);

	public GenderClassificationDTO getGenderClassificationById(GenderClassificationDTO genderClassificationDTO);

	public void updateGenderClassification(GenderClassificationDTO genderClassificationDTO);
}
