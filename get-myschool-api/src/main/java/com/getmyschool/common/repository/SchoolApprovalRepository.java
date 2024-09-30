package com.getmyschool.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.getmyschool.common.domain.SchoolApproval;

@Repository
public interface SchoolApprovalRepository extends JpaRepository<SchoolApproval, Long> {

}
