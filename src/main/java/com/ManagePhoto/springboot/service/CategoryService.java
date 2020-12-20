package com.ManagePhoto.springboot.service;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.ManagePhoto.springboot.model.Category;
import com.ManagePhoto.springboot.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepo;

	public List<Category> GetAllCategory() {
		return categoryRepo.findAll();
	}

	public void saveCategoryToDB(MultipartFile file, String name) {
		Category p = new Category();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if (fileName.contains("..")) {
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

	public List<Category> getAllCategory() {
		return categoryRepo.findAll();
	}

	public void deleteCategoryById(int id) {
		categoryRepo.deleteById(id);
	}

	public void updateCate(int id, String name) {
		Category category = categoryRepo.findById(id).get();
		category.setName(name);
		categoryRepo.save(category);
	}

	public List<Category> getRandomElement(int totalItems) {
		Random rand = new Random();
		List<Category> cateo = categoryRepo.findAll();
		List<Category> newList = new ArrayList<>(); 
		for (int i = 0; i < totalItems; i++) {
			int randomIndex = rand.nextInt(cateo.size());
			newList.add(cateo.get(randomIndex));
			cateo.remove(randomIndex); 
		}
		
		return newList;
		
	}

}
