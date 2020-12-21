package com.ManagePhoto.springboot.service;

import com.ManagePhoto.springboot.model.User;

import org.springframework.web.multipart.MultipartFile;

public interface UserService {
	public void saveUser(User user);
	public boolean isUserAlreadyPresent(User user);
	public User getUsername(String username);
	public void saveUserImage(MultipartFile file, String user_name);
	public void updateUser(String userName,String firstName, String lastName,String decription);

}
