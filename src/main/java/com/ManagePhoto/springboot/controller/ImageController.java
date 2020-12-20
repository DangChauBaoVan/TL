package com.ManagePhoto.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

import com.ManagePhoto.springboot.model.Category;
import com.ManagePhoto.springboot.model.Image;
import com.ManagePhoto.springboot.model.User;

import java.util.List;

import com.ManagePhoto.springboot.service.CategoryService;
import com.ManagePhoto.springboot.service.ImageService;
import com.ManagePhoto.springboot.service.UserService;

@Controller
public class ImageController {

	@Autowired
	private ImageService imageService;

	@Autowired
	private UserService userService;

	@Autowired
	private CategoryService cateService;

	@PostMapping("/addI")
	public String saveImage(@RequestParam("file") MultipartFile file, @RequestParam("title") String title,
			@RequestParam("category") String category) {
		imageService.saveImageToDB(file, title, category);
		return "redirect:/listImages";
	}

	@RequestMapping(value = { "/listImages" }, method = RequestMethod.GET)
	public ModelAndView listImages(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String username = userDetails.getUsername();

		List<Image> images = imageService.getAllImagesByUser(username);
		model.addAttribute("images", images);
		User user = userService.getUsername(username);
		model.addAttribute("user", user);
		
		List<Category> cate = cateService.GetAllCategory();
		model.addAttribute("cate", cate); 
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("listImages");
		return modelAndView;
	}


	@RequestMapping(value = { "/home"}, method = RequestMethod.GET) 
	public ModelAndView homeImages(Model model, @Param("keyword") String keyword, @Param("name") String name) {
		Authentication authentication =SecurityContextHolder.getContext().getAuthentication(); 
		UserDetails userDetails = (UserDetails) authentication.getPrincipal(); 
		String username = userDetails.getUsername();
		List<Category> cate = cateService.GetAllCategory();
		model.addAttribute("cate", cate);
		

		model.addAttribute("keyword", keyword);
		List<Image> images = imageService.listAll(username, keyword, name);
		model.addAttribute("images", images);
	
		List<Category> cate2 = cateService.getRandomElement(5);
		model.addAttribute("cate2", cate2);
		
		ModelAndView modelAndView = new ModelAndView(); 
		modelAndView.setViewName("home"); 
		return modelAndView; 
	}
	
<<<<<<< HEAD

=======
>>>>>>> 19eb06f79600d35d397ec9c228d86e78c0d85e00
	// @RequestMapping( "/home/{name}" )
	// public String homecateImages(@PathVariable("name") String name, Model model) {
	// 	Authentication authentication =SecurityContextHolder.getContext().getAuthentication(); 
	// 	UserDetails userDetails = (UserDetails) authentication.getPrincipal(); 
	// 	String username = userDetails.getUsername(); 
	// 	List<Image> images = imageService.getImageByCategory(name,username);
	// 	model.addAttribute("images", images);
	// 	List<Category> cate = cateService.GetAllCategory();
	// 	model.addAttribute("cate", cate);
	// 	return "home";
	// }

	@GetMapping("/deleteImg/{id}")
	public String deleteImage(@PathVariable("id") Long id) {
		imageService.deleteImagetById(id);
		return "redirect:/listImages";
	}

	@GetMapping("/search/{name}")
	public String showImageByCate(@PathVariable(value = "name") String name,  Model model) {

		Authentication authentication =SecurityContextHolder.getContext().getAuthentication(); 
		UserDetails userDetails = (UserDetails) authentication.getPrincipal(); 
		String username = userDetails.getUsername(); 

		List<Image> images = imageService.getImageByCategory(name, username);
		model.addAttribute("images", images);
		List<Category> cate = cateService.GetAllCategory();
		model.addAttribute("cate", cate);
		return "home";
	}


	@GetMapping("/{id}")
	public String showUpdateImg(@PathVariable(value = "id") long id, Model model) {

		Image image = imageService.getImageById(id);
		model.addAttribute("image", image);
		return "updateImg";
	}

	@PostMapping("/updateImage/{id}")
	public String updateImage(@PathVariable(value = "id") long id, @RequestParam("title") String title,
			@RequestParam("category") String category) {

		imageService.updateImage(id, title, category);
		return "redirect:/listImages";
	}
	

}
