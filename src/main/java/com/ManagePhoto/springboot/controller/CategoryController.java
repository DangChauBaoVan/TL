package com.ManagePhoto.springboot.controller;

import java.util.List;

import com.ManagePhoto.springboot.model.Category;
import com.ManagePhoto.springboot.model.User;
import com.ManagePhoto.springboot.service.CategoryService;
import com.ManagePhoto.springboot.service.UserService;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
public class CategoryController {
	@Autowired
	private CategoryService cateService;
	
	@Autowired
	private UserService  userService;
	
    @PostMapping("/addCate")
    public String saveCategory(@RequestParam("file") MultipartFile file,
            @RequestParam("name") String name)
    {
        cateService.saveCategoryToDB(file, name);
        return "redirect:/category";
    }
    @RequestMapping(value = { "/category" },  method = RequestMethod.GET)
    public ModelAndView getallCategory(Model model) {
        
    	Authentication authentication =SecurityContextHolder.getContext().getAuthentication(); 
		UserDetails userDetails = (UserDetails) authentication.getPrincipal(); 
		String username = userDetails.getUsername();
		User user = userService.getUsername(username);
		model.addAttribute("user", user);
        List<Category> categories = cateService.GetAllCategory();
        
        model.addAttribute("categories", categories); 

        ModelAndView modelAndView = new ModelAndView(); 
        modelAndView.setViewName("category"); 
        return modelAndView;
    }
    
   
    @GetMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable("id") int id)
    {
    	
    	cateService.deleteCategoryById(id);
    	return "redirect:/category";
    }
    @PostMapping("/updateCategory")
    public String updateCategory(@RequestParam("id") int id,
            @RequestParam("name") String name
          
            ) {
        
        cateService.updateCate(id,name);
        return "redirect:/category";
    }
}
