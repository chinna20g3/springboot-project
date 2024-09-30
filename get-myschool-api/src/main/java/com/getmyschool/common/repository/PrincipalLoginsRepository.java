package com.getmyschool.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.getmyschool.common.domain.PrincipalLogins;

@Repository
public interface PrincipalLoginsRepository extends JpaRepository<PrincipalLogins, Long> {

}
