package com.getmyschool.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.getmyschool.common.domain.NewsLetter;

@Repository
public interface NewsLetterRepository extends JpaRepository<NewsLetter, Long> {

	@Query("select n from NewsLetter n where n.email=:email")
	NewsLetter getnewsLetterByEmail(@Param("email")String email);

}
