package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Review;
import com.example.demo.repos.ReviewRepository;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository repo;
	
	public Review postReview(Review review) {
		return repo.save(review);
	}
	
	public List<Review> findAll() {
		return repo.findAll();
	}
	
	public Optional<Review> findById(int id) {
		return repo.findById(id);
	}
	
	public Review updateReview(Review review) {
		return repo.save(review);
	}
	
	public Review deleteReview(Review review) {
		repo.delete(review);
		return review;
	}
	
}
