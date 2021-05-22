package com.example.demo.controllers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.HotelInfo;
import com.example.demo.service.HotelInfoService;

@RestController
@CrossOrigin(origins = "*")
public class HotelInfoController {

	@Autowired
	private HotelInfoService service;
	
	//add new hotel
	@PostMapping("/addHotel")
	public HotelInfo addHotel(@RequestBody HotelInfo hotel) {
		if(service.findById(hotel.getId()).isPresent()) {
			throw new DuplicateKeyException("Hotel with id: " +hotel.getId()+ " already found");
		}
		return service.save(hotel);
	}
	
	//find hotel by id
	@GetMapping("/id/{id}")
	public HotelInfo findById(@PathVariable("id") int id) {
		Optional<HotelInfo> info = this.service.findById(id);
		
		System.out.println(info.isPresent());
		
		if(info.isEmpty()) {
			throw new NoSuchElementException("hotel not found with id: " + id);
		}
		
		return info.get();
	}
	
	//find all hotels
	@GetMapping("/hotels")
	public List<HotelInfo> findAll() {
		return service.findAll();
	}
	
	//find by hotel name
	@GetMapping("/hotel/{hotelName}")
	public HotelInfo findByName(@PathVariable("hotelName") String hotelName) throws NoSuchElementException {
		HotelInfo hotel = service.findByName(hotelName);
		
		if(hotel == null) {
			throw new NoSuchElementException("Hotel - " +hotelName+ " not found");
		}

		return hotel;
	}
	
	//find by menu type
	@GetMapping("/menu/{type}")
	public List<HotelInfo> findByMenuType(@PathVariable("type") String type) {
		List<HotelInfo> hotels = service.findByMenuType(type);

		if(hotels == null) {
			throw new NoSuchElementException("Menu type - " +type+ " not found");
		}

		return hotels;
	}
	
	//find by service area
	@GetMapping("/area/{area}")
	public List<HotelInfo> findByServiceArea(@PathVariable("area") String area) {
		List<HotelInfo> hotels = service.findByServiceArea(area);

		if(hotels == null) {
			throw new NoSuchElementException("Service area - " +area+ " not found");
		}

		return hotels;
	}
	
	//find by service area and menu type
	@GetMapping("/{area}/{menu}")
	public List<HotelInfo> findByServiceAreaAndMenuType(@PathVariable("area") String area, @PathVariable("menu") String menuType) {
		List<HotelInfo> hotels = service.findByServiceAreaAndMenuType(area, menuType);

		if(hotels == null) {
			throw new NoSuchElementException("Nothing found");
		}

		return hotels;
	}
	
}
