package com.getmyschool.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.getmyschool.common.domain.PartnerLogins;
@Repository
public interface PartnerLoginsRepository extends JpaRepository<PartnerLogins, Long>  {
	
	@Query("select p from PartnerLogins p where p.email=:email")
	PartnerLogins getPartnerLoginsByEmail(@Param("email") String email);
}
