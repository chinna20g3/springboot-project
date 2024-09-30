package com.getmyschool.college.service;

import java.util.List;

import com.getmyschool.common.dto.GradeClassificationDTO;

public interface GradeClassificationService {
	
	public void saveGradeClassification(GradeClassificationDTO gradeClassificationDTO);

	public List<GradeClassificationDTO> getAllGradeClassifications(GradeClassificationDTO gradeClassificationDTO);

	public GradeClassificationDTO getGradeClassificationById(GradeClassificationDTO gradeClassificationDTO);

	public void updateGradeClassification(GradeClassificationDTO gradeClassificationDTO);

	
}
