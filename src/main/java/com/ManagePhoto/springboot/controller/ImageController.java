package com.ManagePhoto.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ManagePhoto.springboot.model.Category;
import com.ManagePhoto.springboot.model.Image;
import com.ManagePhoto.springboot.model.User;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
	public String listImages(Model model,HttpServletRequest request
			,RedirectAttributes redirect) {
		request.getSession().setAttribute("imageList", null);
		
		/*
		 * Authentication authentication =
		 * SecurityContextHolder.getContext().getAuthentication(); UserDetails
		 * userDetails = (UserDetails) authentication.getPrincipal(); String username =
		 * userDetails.getUsername();
		 * 
		 * List<Image> images = imageService.getAllImagesByUser(username);
		 * model.addAttribute("images", images);
		 * 
		 * User user = userService.getUsername(username); model.addAttribute("user",
		 * user);
		 * 
		 * List<Category> cate = cateService.GetAllCategory();
		 * model.addAttribute("cate", cate);
		 * 
		 * ModelAndView modelAndView = new ModelAndView();
		 * modelAndView.setViewName("listImages");
		 */
		return "redirect:/listImages/page/1";
	}
	
	@GetMapping("/listImages/page/{pageNumber}")
	public String showImagePage(HttpServletRequest request, 
			@PathVariable int pageNumber, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String username = userDetails.getUsername();
		
		User user = userService.getUsername(username);
		model.addAttribute("user", user);
		
		List<Category> cate = cateService.GetAllCategory();
		model.addAttribute("cate", cate); 
		
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("imageList");
		int pagesize = 5;
		List<Image> list =(List<Image>) imageService.getAllImagesByUser(username);
		System.out.println(list.size());
		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pagesize);
		} else {
			final int goToPage = pageNumber - 1;
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
		request.getSession().setAttribute("imageList", pages);
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		int end = Math.min(begin + 5, pages.getPageCount());
		int totalPageCount = pages.getPageCount();
		String baseUrl = "/listImages/page/";

		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("images", pages);

		return "listImages";
	}
	
	@RequestMapping(value = { "/home"}, method = RequestMethod.GET) 
	public ModelAndView homeImages(Model model, @Param("keyword") String keyword, @Param("name") String name) {
		Authentication authentication =SecurityContextHolder.getContext().getAuthentication(); 
		UserDetails userDetails = (UserDetails) authentication.getPrincipal(); 
		String username = userDetails.getUsername();
		List<Category> cate = cateService.GetAllCategory();
		model.addAttribute("cate", cate);

		User user = userService.getUsername(username);
		model.addAttribute("user", user);

		model.addAttribute("keyword", keyword);
		List<Image> images = imageService.listAll(username, keyword, name);
		model.addAttribute("images", images);
	
		List<Category> cate2 = cateService.getRandomElement(5);
		model.addAttribute("cate2", cate2);
		
		ModelAndView modelAndView = new ModelAndView(); 
		modelAndView.setViewName("home"); 
		return modelAndView; 
	}


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
		User user = userService.getUsername(username);
		model.addAttribute("user", user);
		List<Image> images = imageService.getImageByCategory(name, username);
		model.addAttribute("images", images);
		List<Category> cate = cateService.GetAllCategory();
		model.addAttribute("cate", cate);
		return "home";
	}


	@GetMapping("/image/edit/{id}")
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
