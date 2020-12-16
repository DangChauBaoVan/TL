package com.ManagePhoto.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ManagePhoto.springboot.model.Image;


@Repository
public interface ImageRepositry extends JpaRepository<Image, Long> {
	
	 @Query("SELECT e FROM Image e WHERE e.user_name = :user_name")
	 List<Image> findAllImagesByUserName(@Param("user_name") String user_name);

}
