package com.ManagePhoto.springboot.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ManagePhoto.springboot.model.Role;
import com.ManagePhoto.springboot.model.User;
import com.ManagePhoto.springboot.repository.RoleRepository;
import com.ManagePhoto.springboot.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	BCryptPasswordEncoder encoder;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setStatus("VERIFIED");
		Role userRole = roleRepository.findByRole("SITE_USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	public boolean isUserAlreadyPresent(User user) {
		return false;
	}
	
	@Override
	public User getUsername(String user_name) {
		return userRepository.findName(user_name);
	}
	@Override
	public void saveUserImage(MultipartFile file, String user_name) {
		User u = userRepository.findName(user_name);
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains(".."))
		{
			System.out.println("not a valid file");
		}
		try {
			u.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		userRepository.save(u);	
	}
	
	@Override
	public void updateUser(String userName,String firstName, String lastName,String decription) {
		User u = userRepository.findName(userName);
		u.setName(firstName);
		u.setLastName(lastName);
		u.setDecription(decription);
		userRepository.save(u);
	}
	

}
