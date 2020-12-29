package com.ManagePhoto.springboot.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ManagePhoto.springboot.model.Image;
import com.ManagePhoto.springboot.repository.ImageRepositry;

@Service
public class ImageService {
	@Autowired
	private ImageRepositry imageRepo;

	public List<Image> getAllImages()
	{
		return imageRepo.findAll();
	}
	
	public List<Image> getAllImagesByUser(String username){
		return imageRepo.findAllImagesByUserName(username);
	}
	
	public void saveImageToDB(MultipartFile file ,String title, String category,String keyword) {
		Image p = new Image();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains(".."))
		{
			System.out.println("not a valid file");
		}
		try {

			p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));

		} catch (IOException e) {
			System.out.println("not a valid file");

		}
		p.setTitle(title);
		p.setCategory(category);
		p.setKeyword(keyword);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String username = userDetails.getUsername();
		p.setUser_name(username);
		imageRepo.save(p);
	}

	public void deleteImagetById(Long id)
	{
		imageRepo.deleteById(id);
	}
	public void updateImage(Long id,String title, String category,String keyword) {
		Image image= imageRepo.findById(id).get();
		image.setCategory(category);
		image.setTitle(title);
		image.setKeyword(keyword);
		imageRepo.save(image);
	}
	public Image getImageById(long id) {
		Optional<Image> optional= imageRepo.findById(id);
		Image image =null;
		if(optional.isPresent()) {
			image = optional.get();
		}else {
			throw new RuntimeException("Image Not Found For Id :: " + id);
		}
		return image;
	}
	
	public List<Image> getImageByCategory(String name, String userName){
		return imageRepo.findAllImagesByCategory(name,userName);
	}
	
	public List<Image> listAll(String name,String keyword) {
        if (keyword != null) {
            return imageRepo.searchByKeyword(name, keyword);
        }
        
        return imageRepo.findAllImagesByUserName(name);
    }
	
	public long countImage(String username) {
		return imageRepo.countImage(username);
	}
	
	public void onLikeStatus(Long id) {
		Image image= imageRepo.findById(id).get();
		image.setLike_status(1);
		imageRepo.save(image);
	}
	
	public void offLikeStatus(Long id) {
		Image image= imageRepo.findById(id).get();
		image.setLike_status(0);
		imageRepo.save(image);
	}


}
