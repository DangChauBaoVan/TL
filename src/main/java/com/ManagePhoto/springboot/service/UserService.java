package com.ManagePhoto.springboot.service;

import com.ManagePhoto.springboot.model.User;

public interface UserService {
	public void saveUser(User user);
	public boolean isUserAlreadyPresent(User user);
	public User getUsername(String username);
	

}
