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
	 
	 @Query("SELECT e FROM Image e WHERE e.category = :name AND e.user_name = :user_name")
	 List<Image> findAllImagesByCategory(@Param("name") String name,@Param("user_name") String user_name);
	 
	 @Query("SELECT p FROM Image p WHERE p.user_name = :user_name AND p.title LIKE %:keyword% ")
	 List<Image> searchByTitle(@Param("user_name") String user_name,@Param("keyword") String keyword);
	 
	 @Query("SELECT p FROM Image p WHERE p.user_name = :user_name AND p.keyword LIKE %:keyword% ")
	 List<Image> searchByKeyword1(@Param("user_name") String user_name,@Param("keyword") String keyword);
	 
	 @Query("SELECT p FROM Image p WHERE p.user_name = :user_name AND p.like_status=1")
	 List<Image> showLikeImage(@Param("user_name") String user_name);
	 
	 @Query("SELECT p FROM Image p WHERE p.user_name = :user_name AND (p.title LIKE %:keyword%"
			 					+ " OR p.category LIKE %:keyword%"
			 					+ " OR p.keyword LIKE %:keyword%)")
	 List<Image> searchSmart(@Param("user_name") String user_name,@Param("keyword") String keyword);
	 
	 
	 @Query("SELECT COUNT(p) FROM Image p WHERE p.user_name= :user_name")
	 Long countImage(@Param("user_name") String user_name);
	 
	 @Query("SELECT COUNT(p) FROM Image p WHERE p.user_name= :user_name AND p.like_status=1" )
	 Long countLikeImage(@Param("user_name") String user_name);
	 
	

}
