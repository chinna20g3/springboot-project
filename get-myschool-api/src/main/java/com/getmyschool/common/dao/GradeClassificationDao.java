package com.getmyschool.common.dao;

import java.util.List;

import com.getmyschool.common.domain.GradeClassification;
import com.getmyschool.common.dto.GradeClassificationDTO;

public interface GradeClassificationDao {
	
	public GradeClassification saveGradeClassification(GradeClassificationDTO gradeClassificationsDTO);

	public List<GradeClassification> getAllGradeClassification(GradeClassificationDTO gradeClassificationsDTO);

	public GradeClassification getGradeClassificationById(Long id);
}
