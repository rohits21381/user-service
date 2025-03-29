package com.user.app.restcontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.app.entity.User;
import com.user.app.exception.AddressNotFoundException;
import com.user.app.services.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user/api/v1")
@Slf4j
public class UserRestController {

	private UserService userService;

	public UserRestController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping("/save")
	public ResponseEntity<User> saveUser(@RequestBody User user){
		User saveUser = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
		
	}
	
	@PutMapping("/update-userid-{userId}")
	public ResponseEntity<User> updateUser(@RequestBody User user,@PathVariable("userId") Long userId){ 
		User updateUser = userService.updateUser(user, userId);
		return ResponseEntity.status(HttpStatus.OK).body(updateUser);
		
	}
	
	@GetMapping("/getuserbyid-{userId}")
	public ResponseEntity<User> getUserByUserId(@PathVariable("userId") Long useerId){
		User userByUserId = userService.getUserByUserId(useerId);
		return new ResponseEntity<>(userByUserId,HttpStatus.OK);
	}
	
	@GetMapping("/getalluser")
	public ResponseEntity<List<User>> getAllUser(){
		List<User> allUser = userService.getAllUser();
		return new ResponseEntity<>(allUser,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteuserbyid-{userId}")
	public ResponseEntity<String> deleteUserByUserId(@PathVariable("userId") Long useerId){
		String deleteUser = userService.deleteUser(useerId);
		return new ResponseEntity<>(deleteUser,HttpStatus.OK);
	}
	
	@GetMapping("/getuserebyemail-{email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email){
		log.info("getUserByEmail controller start");
		User userByEmail = userService.getUserByEmail(email);
		System.out.println("***"+userByEmail);
		log.info("getUserByEmail controller end");
		return new ResponseEntity<>(userByEmail,HttpStatus.OK); 
	}
	
	@GetMapping("/getalluserbyaddress-{search}")
	public ResponseEntity<List<User>> getUserByAddresSearch(@PathVariable("search") String search){
		List<User> findAllUserByAddress = userService.findAllUserByAddress(search);
		if(findAllUserByAddress.isEmpty()) {
			 throw  new AddressNotFoundException(search+" addres not found.");
		}
		return new ResponseEntity<>(findAllUserByAddress,HttpStatus.OK);
	}
}
