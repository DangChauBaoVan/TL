package com.ManagePhoto.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ManagePhoto.springboot.model.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
