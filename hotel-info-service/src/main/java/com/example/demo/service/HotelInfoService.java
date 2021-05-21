package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.HotelInfo;
import com.example.demo.repos.HotelInfoRepository;

@Service
public class HotelInfoService {

	@Autowired
	private HotelInfoRepository repo;
	
	public HotelInfo save(HotelInfo hotel) {
		hotel.setId((int) (repo.count() + 1));
		return repo.save(hotel);
	}
	
	public Optional<HotelInfo> findById(int id) {
		return repo.findById(id);
	}

	public List<HotelInfo> findAll() {
		return repo.findAll();
	}
	
	public HotelInfo findByName(String hotelName) {
		return repo.findByName(hotelName);
	}
	
	public List<HotelInfo> findByMenuType(String type) {
		return repo.findByMenuType(type);
	}
	
	public List<HotelInfo> findByServiceArea(String area) {
		return repo.findByServiceArea(area);
	}
}
