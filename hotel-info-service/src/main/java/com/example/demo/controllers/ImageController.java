package com.example.demo.controllers;

import java.io.IOException;

import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Image;
import com.example.demo.repos.ImageRepository;

@RestController
public class ImageController {
	
	@Autowired
	private ImageRepository repo;
	
	@PostMapping(path = "/upload", consumes = MediaType.IMAGE_JPEG_VALUE)
	public String uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
		
		Image img = new Image(repo.count() + 1, new Binary(file.getBytes()), file.getName(), file.getContentType());
		
		repo.save(img);
		
		return img.getName() + "saved";
	}
	
}
