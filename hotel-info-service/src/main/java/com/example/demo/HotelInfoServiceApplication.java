package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelInfoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelInfoServiceApplication.class, args);
	}
	
//	@Bean
//	public CommandLineRunner runner() {
//		return new CommandLineRunner() {
//			
//			@Autowired
//			private HotelInfoRepository repo;
//			
//			@Override
//			public void run(String... args) throws Exception {
//				repo.save(new HotelInfo(1, "SS Hyderabad", "Tondiarpet", "ss@gmail.com", "non-veg", "11-11"));
//			}
//		};
//	}
	
}
