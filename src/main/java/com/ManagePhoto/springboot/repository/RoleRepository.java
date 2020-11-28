package com.ManagePhoto.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ManagePhoto.springboot.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	public Role findByRole(String role);

}
