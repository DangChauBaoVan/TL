package com.ManagePhoto.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.ManagePhoto.springboot.model.Image;
import com.ManagePhoto.springboot.repository.ImageRepositry;

import java.util.List;

import com.ManagePhoto.springboot.service.ImageService;

@Controller
public class ImageController {
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private ImageRepositry imageRepo;
	
	@PostMapping("/addI")
    public String saveImage(@RequestParam("file") MultipartFile file,
    		@RequestParam("title") String title,
    		@RequestParam("category") String category,
			@RequestParam("date") String date
		
			)
    {
    	imageService.saveImageToDB(file, title, category, date);
    	return "redirect:/listImages";
	}
	@RequestMapping(value = { "/listImages" }, method = RequestMethod.GET)
	public ModelAndView listImages(Model model) {
		List<Image> images = imageService.getAllImages();
		model.addAttribute("images", images);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("listImages"); 
		return modelAndView;
	}
	@GetMapping("/deleteImg/{id}")
    public String deleteImage(@PathVariable("id") Long id)
    {
    	
    	imageService.deleteImagetById(id);
    	return "redirect:/listImages";
    }
	
	
	  @GetMapping("/editImg/{id}") 
	  public String showUpdateForm(@PathVariable("id") long id,Model model) {
		  
		  return "redirect:/updateImg"; 
	  
	  }
	  
	  @PostMapping("/updateImg/{id}")
	  public String updateImage(@RequestParam("id") Long id ) {
		  
		  return "redirect:/listImages"; 
	  }
	 
	

}
