package com.ManagePhoto.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ManagePhoto.springboot.model.Image;


@Repository
public interface ImageRepositry extends JpaRepository<Image, Long> {
	
	 @Query("SELECT e FROM Image e WHERE e.user_id = :user_id")
	 List<Image> findAllImagesByUserId(@Param("user_id") int user_id);

}
