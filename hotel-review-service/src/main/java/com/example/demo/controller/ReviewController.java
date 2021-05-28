package com.example.demo.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Review;
import com.example.demo.service.ReviewService;

@RestController
@CrossOrigin(origins = "*")
@RefreshScope
@RequestMapping(path = "/review")
public class ReviewController {

	@Autowired
	private ReviewService service;
	
	//post new review
	@PostMapping("/addReview")
	public Review postReview(@RequestBody Review review) {
		
		Optional<Review> optReview = service.findById(review.getId());
		
		if(optReview.isPresent()) {
			throw new DuplicateKeyException("Review with id " +review.getId()+ "already found");
		}
		
		return service.postReview(review);
	}
	
	//find all reviews
	@GetMapping("/reviews")
	public List<Review> findAll() {
		return service.findAll();
	}
	
	//find review by id
	@GetMapping("/id/{id}")
	public Review findById(@PathVariable("id") int id) {
		Optional<Review> optrev = service.findById(id);
		
		if(optrev.isEmpty()) {
			throw new NoSuchElementException("No review find with id: " +id);
		}
		
		return optrev.get();
	}
	
	//update review with id
	@PutMapping("/update/{id}")
	public Review updateReview(@PathVariable("id") int id, @RequestBody Review review) {
		
		findById(id);
		return service.updateReview(review);
	}
	
	//delete review with id
	@DeleteMapping("/delete/{id}")
	public Review deleteReview(@PathVariable("id") int id) {
		
		Review review = findById(id);
		service.deleteReview(review);
		return review;
	}
	
}