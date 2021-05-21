package com.example.demo.repos;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.HotelInfo;

@Repository
public interface HotelInfoRepository extends MongoRepository<HotelInfo, Integer> {
	
	public HotelInfo findByName(String hotelName);
	
	public List<HotelInfo> findByMenuType(String menuType);
	
	public List<HotelInfo> findByServiceArea(String area);
	
	@Query
	public List<HotelInfo> findByServiceAreaAndMenuType(String area, String menuType);
}
