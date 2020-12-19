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
public class UserController {
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = { "/userInfo" }, method = RequestMethod.GET)
    public ModelAndView getUserInfo(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String username = userDetails.getUsername();

		User user = userService.getUsername(username);
		model.addAttribute("user",user); 
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("userInfo");
		return modelAndView;
	}
}
