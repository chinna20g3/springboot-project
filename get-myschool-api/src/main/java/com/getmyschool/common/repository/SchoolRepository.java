package com.getmyschool.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.getmyschool.common.domain.School;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long>{

}
