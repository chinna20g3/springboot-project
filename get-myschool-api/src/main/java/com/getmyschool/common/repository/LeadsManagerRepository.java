package com.getmyschool.common.repository;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.getmyschool.common.domain.LeadsManager;

@Repository
public interface LeadsManagerRepository extends JpaRepository<LeadsManager, Long>{

	@Query("select l from LeadsManager l where l.email=:email")
	LeadsManager getLeadsManagerByEmail(@Param("email") String email);

}
