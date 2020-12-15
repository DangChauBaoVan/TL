package com.ManagePhoto.springboot.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ManagePhoto.springboot.model.Category;
import com.ManagePhoto.springboot.model.Image;
import com.ManagePhoto.springboot.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepo;
	
	public void saveCategoryToDB(MultipartFile file ,String name) {
		Category p = new Category();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains(".."))
		{
			System.out.println("not a valid file");
		}
		try {
			p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		p.setName(name);
		categoryRepo.save(p);
	}
	
	public List<Category> getAllCategory()
	{
		return categoryRepo.findAll();
	}

}
