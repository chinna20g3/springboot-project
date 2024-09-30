package com.getmyschool.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.getmyschool.common.domain.Amenities;

@Repository
public interface AmenitiesRepository extends JpaRepository<Amenities, Long> {

}
