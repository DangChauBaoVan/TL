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

import java.util.List;

import com.ManagePhoto.springboot.service.CategoryService;
import com.ManagePhoto.springboot.service.ImageService;

@Controller
public class ImageController {
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private CategoryService cateService;
	
	
	
	@PostMapping("/addI")
    public String saveImage(@RequestParam("file") MultipartFile file,
    		@RequestParam("title") String title,
    		@RequestParam("category") String category)
    {
    	imageService.saveImageToDB(file, title, category);
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
	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public ModelAndView homeImages(Model model) {
		List<Image> images = imageService.getAllImages();

		// System.out.println(images.size());
		// int [] imageslist = new int [images.size()+1];

		// for(int i = ; i g<ry images.size()/5; ++i)
		// {
		// 	imageslist[i] = imageService.getimage(images.get(i));
		// }

		model.addAttribute("images", images); // phần get dữ liệu

		ModelAndView modelAndView = new ModelAndView(); //phần hiển thị
		modelAndView.setViewName("home"); 
		return modelAndView;
	}
	@GetMapping("/deleteImg/{id}")
    public String deleteImage(@PathVariable("id") Long id)
    {
    	
    	imageService.deleteImagetById(id);
    	return "redirect:/listImages";
    }
	
	 @GetMapping("/showUpdateImg/{id}")
	 public String showUpdateImg(@PathVariable (value = "id") long id, Model model) {
		 
		 Image image = imageService.getImageById(id);
		 model.addAttribute("image", image);
		 
		 
		 return "updateImg";
	 } 	
	 @PostMapping("/updateImage")
		public String updateImage(@RequestParam("id") Long id,
				@RequestParam("title") String title,
	    		@RequestParam("category") String category
				) {
			
			imageService.updateImage(id,title,category);
			return "redirect:/listImages";
		}
	 @PostMapping("/addCate")
	    public String saveCategory(@RequestParam("file") MultipartFile file,
	    		@RequestParam("name") String name)
	    {
	    	cateService.saveCategoryToDB(file, name);
	    	return "redirect:/listImages";
		}
	 
}
