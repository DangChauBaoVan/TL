package com.ManagePhoto.springboot.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.ManagePhoto.springboot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	 @Query("SELECT e FROM User e WHERE e.email = :email")
	 User findName(@Param("email") String email);

}
