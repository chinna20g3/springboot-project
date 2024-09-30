package com.getmyschool.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.getmyschool.common.domain.IpTracking;

@Repository
public interface IpTrackingRepository extends JpaRepository<IpTracking, Long> {

}
