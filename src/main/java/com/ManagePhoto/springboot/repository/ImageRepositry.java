package com.ManagePhoto.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ManagePhoto.springboot.model.Image;


@Repository
public interface ImageRepositry extends JpaRepository<Image, Long> {

}
