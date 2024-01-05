package com.example.dbms.controllers;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dbms.entities.CustomHouse;
import com.example.dbms.entities.FilterObject;
import com.example.dbms.entities.ImageObject;
import com.example.dbms.entities.ObjectAppointments;
import com.example.dbms.entities.ReviewHouse;
import com.example.dbms.entities.Slots;
import com.example.dbms.entities.UserEntity;
import com.example.dbms.entities.appointment;
import com.example.dbms.entities.bookmarked;
import com.example.dbms.services.HouseService;
import com.example.dbms.services.MyUserDetailsService;

@RestController
public class Mycontroller{
	
	@Autowired
	private HouseService houseservice;
	
	@Autowired
	private MyUserDetailsService userDetailsService;

	@RequestMapping(value = "/myname", method = RequestMethod.GET)
    public String getName(Authentication authentication) {
        System.out.println(authentication.getName());
		
        return "";
    }
	
	//get the houses
	@GetMapping("/houses") 
	
	public List<CustomHouse> houses(){
		return this.houseservice.getHouses();
	}
	
	@GetMapping("/myhouses")
	public List<CustomHouse> my_houses(Authentication authentication){
		String email_id = authentication.getName();
		return this.houseservice.getmyHouse(email_id);
	}
	
	@GetMapping("/myprofile")
	public UserEntity my_profile(Authentication authentication){
		String email_id = authentication.getName();
		return this.userDetailsService.getMyProfile(email_id);
	}
	
	@PostMapping(path = "/myprofile/update", consumes = "application/json")
	public Map<String,String> update_profile(@RequestBody UserEntity u ) {
		this.userDetailsService.updateMyProfile(u);
		Map<String,String> body= new HashMap<>();
		body.put("message","success");
		return body;
	}
	
	@GetMapping("/houses/{house_id}")
	public ImageObject get_house(@PathVariable int house_id){
		return this.houseservice.getHouse(house_id);
	}
	
	// @GetMapping("/houses/city/{city}")
	// public List<House> get_house_city(@PathVariable String city){
	// 	return this.houseservice.getincity(city);
	// }

	// @GetMapping("/houses/state/{state}")
	// public List<House> get_house_state(@PathVariable String state){
	// 	return this.houseservice.getinstate(state);
	// }
	
	// @GetMapping("/houses/bhk/{bhk}")
	// public List<House> get_house_bhk(@PathVariable int bhk){
	// 	return this.houseservice.getbhk(bhk);
	// }
	
	// @GetMapping("/houses/rent/{rent}")
	// public List<House> get_house_rent(@PathVariable int rent){
	// 	return this.houseservice.getRentlessthan(rent);
	// }
	
	// @GetMapping("/houses/months/{num}")
	// public List<House> get_house_date(@PathVariable int num){
	// 	return this.houseservice.getdate(num);
	// }
	
	@PostMapping(path = "/houses", consumes = "application/json")
	public Map<String,String> add_house(@RequestBody ImageObject h) {
		this.houseservice.addHouse(h);
		Map<String,String> body= new HashMap<>();
		body.put("message","success");
		return body;
	}
	
	@PutMapping(path = "/myhouses/{house_id}", consumes = "application/json")
	public Map<String,String> update_house(@PathVariable int house_id, @RequestBody ImageObject h) {
		this.houseservice.updateHouse(h, house_id);
		Map<String,String> body= new HashMap<>();
		body.put("message","success");
		return body;
	} 
	
	@DeleteMapping("/houses/{house_id}")
	public Map<String,String> delete_house(@PathVariable int house_id){
		this.houseservice.deleteHouse(house_id);
		Map<String,String> body= new HashMap<>();
		body.put("message","success");
		return body;
	}
	
	@GetMapping("/appointments/seller")
	public List<ObjectAppointments> my_appointments_seller(Authentication authentication){
		String email_id = authentication.getName();
		return this.houseservice.view_appointments_seller(email_id);
	}
	
	@GetMapping("/appointments/buyer")
	public List<ObjectAppointments> my_appointments_buyer(Authentication authentication){
		String email_id = authentication.getName();
		return this.houseservice.view_appointments_buyer(email_id);
	}
	
	@PostMapping(path = "/appointments", consumes = "application/json")
	public Map<String,String> shedule_appoinmment(@RequestBody appointment a){
		return this.houseservice.shedule_appointment(a);
	}
	
	@PutMapping(path = "/appointments/{house_id}", consumes = "application/json")
	public Map<String,String> reshedule_appointment(Authentication authentication, @PathVariable int house_id, @RequestBody appointment a){
		String email_id = authentication.getName();
		this.houseservice.reshedule_appointment(email_id,  house_id, a);
		Map<String,String> body= new HashMap<>();
		body.put("message","success");
		return body;
	}
	
	// @DeleteMapping("/appointments")
	// public Map<String,String> delete_all_appointment(Authentication authentication){
	// 	String email_id = authentication.getName();
	// 	this.houseservice.delete_all_my_appointments(email_id);
	// 	Map<String,String> body= new HashMap<>();
	// 	body.put("message","success");
	// 	return body;
	// }
	
	@DeleteMapping("/appointments")
	public Map<String,String> delete_all_appointment(Authentication authentication, @RequestBody appointment a){
		String email_id = authentication.getName();
		this.houseservice.delete_appointment(email_id, a);
		Map<String,String> body= new HashMap<>();
		body.put("message","success");
		return body;
	}
	
	@GetMapping("/bookmarks")
	public List<CustomHouse> my_bookmarks(Authentication authentication) {
		String email_id = authentication.getName();
		return this.houseservice.my_bookmarks(email_id);
	}
	
	@PostMapping(path = "/houses/bookmark", consumes = "application/json")
	public Map<String,String> add_bookmark( @RequestBody bookmarked b){
		this.houseservice.add_bookmark(b);
		Map<String,String> body= new HashMap<>();
		body.put("message","success");
		return body;
	}
	
	@DeleteMapping(path = "/houses/bookmark", consumes = "application/json")
	public Map<String,String> remove_bookmark(@RequestBody bookmarked b){
		this.houseservice.remove_bookmark(b);
		Map<String,String> body= new HashMap<>();
		body.put("message","success");
		return body;
	}
	
	@GetMapping("/slots/{house_id}")
	public List<Slots> show_slots(@PathVariable int house_id){
		return this.houseservice.show_slots(house_id);
	}

	@PostMapping("/myhouses/slots")
	public Map<String,String> add_slots(@RequestBody List<Slots> s){
		this.houseservice.add_slots(s);
		Map<String,String> body= new HashMap<>();
		body.put("message","success");
		return body;
	}
	
	@DeleteMapping("/myhouses/slots")
	public Map<String,String> delete_SlotOfHouse(@RequestBody Slots slots) {
		this.houseservice.remove_slot(slots.getDate(),slots.getHouse_id());
		Map<String,String> body= new HashMap<>();
		body.put("message","success");
		return body;
	}
	
	// @DeleteMapping("/slots/{date}")
	// public void delete_Slot_onDate(@PathVariable Date date, Authentication authentication) {
	// 	String email_id = authentication.getName();
	// 	this.houseservice.delete_slot(email_id,date);
	// }
	
	@PostMapping(value="/house/review", consumes  = "application/json")
	public Map<String,String> review_house(@RequestBody ReviewHouse housereview,Authentication authentication){
		String email_id = authentication.getName();
		this.houseservice.review_house(housereview,email_id);
		Map<String,String> body= new HashMap<>();
		body.put("message","success");
		return body;
	}

	@GetMapping(value="/house/reviews/{house_id}")
	public List<ReviewHouse> reviews_house(@PathVariable int house_id) {
		return this.houseservice.get_reviews_house(house_id);
	}

	@PostMapping("/houses/filter")
	public List<CustomHouse> filterHouse(@RequestBody FilterObject filter){
		return this.houseservice.filter_house(filter);
	}

}