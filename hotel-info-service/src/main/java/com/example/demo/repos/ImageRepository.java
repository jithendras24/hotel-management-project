package com.example.demo.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Image;

@Repository
public interface ImageRepository extends MongoRepository<Image, Long> {

}
