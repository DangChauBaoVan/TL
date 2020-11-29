package com.ManagePhoto.springboot.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ManagePhoto.springboot.model.Image;
import com.ManagePhoto.springboot.repository.ImageRepositry;

@Service
public class ImageService {
	@Autowired
	private ImageRepositry imageRepo;

	public List<Image> getAllImages()
	{
		return imageRepo.findAll();
	}
	public void saveImageToDB(MultipartFile file ,String title, String category, String date) {
		Image p = new Image();
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
		p.setTitle(title);
		p.setCategory(category);
		p.setDate(date);
		imageRepo.save(p);
	}
}
