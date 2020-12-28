package com.ManagePhoto.springboot.controller;

import java.io.Console;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.bytebuddy.matcher.ModifierMatcher.Mode;
import org.springframework.ui.Model;
import com.ManagePhoto.springboot.model.Image;
import com.ManagePhoto.springboot.model.User;
import com.ManagePhoto.springboot.service.ImageService;
import com.ManagePhoto.springboot.service.UserService;

@Controller
public class AuthenticationController {

	@Autowired
	UserService userService;
	ImageService imageService;


	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value = { "/register" }, method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("register");
		return modelAndView;
	}
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView registerUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
	
		if(bindingResult.hasErrors()) {
			modelAndView.addObject("successMessage", "Please correct the errors in form!");
			modelMap.addAttribute("bindingResult", bindingResult);
		}
		else if(userService.isUserAlreadyPresent(user)){
			modelAndView.addObject("successMessage", "user already exists!");			
		}
	
		else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User is registered successfully!");
		}
		modelAndView.addObject("user", new User());
		modelAndView.setViewName("register");
		return modelAndView;
	}
	
	// @RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	// public ModelAndView home() {
	// 	ModelAndView modelAndView = new ModelAndView();
	// 	modelAndView.setViewName("home");
	// 	return modelAndView;
	// }

	
	// @GetMapping("/listImages.html")
	// public String listImages(Model model) {
	// 	List<Image> images = imageService.getAllImages();
	// 	model.addAttribute("images", images);
	// 	return "/listImages.html";
	// }
	@RequestMapping(value = {"/addImage"}, method = RequestMethod.GET)
	public ModelAndView addImage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("addImage"); 
		return modelAndView;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }
	
	
}
