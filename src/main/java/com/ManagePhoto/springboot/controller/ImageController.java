package com.ManagePhoto.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ManagePhoto.springboot.service.ImageService;

@Controller
public class ImageController {
	
	@Autowired
	private ImageService imageService;
	
	@PostMapping("/addP")
    public String saveImage(@RequestParam("file") MultipartFile file,
    		@RequestParam("title") String title,
    		@RequestParam("category") String category,
    		@RequestParam("date") String date)
    {
    	imageService.saveImageToDB(file, title, category, date);
    	return "redirect:/admin.html";
    }

}
