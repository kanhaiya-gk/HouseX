package com.example.dbms;

//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import com.example.dbms.dao.DAO;
//import com.example.dbms.entities.House;

@SpringBootApplication
public class DbProjectApplication {
//	@Autowired
//	private static DAO<House> dao;
	
//	public DbProjectApplication(DAO<House> dao) {
//		this.dao = dao;
//	}
	
	public static void main(String[] args) {
		SpringApplication.run(DbProjectApplication.class, args);
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		Date date;
//		try {
//			date = formatter.parse("2022-10-15");
//			House house = new House(100.0,2021,"Colony","Varanasi","Uttar Pradesh","Nice",2,10000,date,1,false);
//			dao.create(house);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.out.println("\n All houses");
//		
//		List<House> houses = dao.list();
//		houses.forEach(System.out::println);
//		System.out.println("Getting houses by id");
//		Optional<House> h = dao.get(1);
//		System.out.println(h);
//		System.out.println("Updating houses by id");
//		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
//		Date date1;
//		try {
//			date1 = formatter1.parse("2022-10-15");
//			House house = new House(100.0,2021,"Colony","Kanpur","Uttar Pradesh","Bad",2,10000,date1,1,false);
//			dao.update(house, 1);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Optional<House> h1 = dao.get(1);
//		System.out.println(h1);
//		dao.delete(3);
//		List<House> houses1 = dao.list();
//		houses1.forEach(System.out::println);
//		List<House> houses2 = dao.housecity("Kanpur");
//		System.out.println("city houses");
//		houses2.forEach(System.out::println);
//		System.out.println("my house user id 1");
//		List<House> houses3 = dao.myhouse(1);
//		houses3.forEach(System.out::println);
	}
	
}
