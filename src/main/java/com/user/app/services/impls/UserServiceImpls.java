package com.user.app.services.impls;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.app.entity.Product;
import com.user.app.entity.User;
import com.user.app.exception.EmailNotFoundException;
import com.user.app.exception.UserNotFoundException;
import com.user.app.externalservice.ProductService;
import com.user.app.repo.UserRepository;
import com.user.app.services.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpls implements UserService{
	
	private UserRepository userRepository;
	private RestTemplate restTemplate;
	private ProductService productService;
	
	public UserServiceImpls(UserRepository userRepository, RestTemplate restTemplate,
			ProductService productService) {
		this.userRepository = userRepository;
		this.restTemplate = restTemplate;
		this.productService = productService;
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user, Long userId) {
		User userByDb = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found with userId: "+userId));
		userByDb.setAddress(user.getAddress());
		userByDb.setEmail(user.getEmail());
		userByDb.setName(user.getName());
		userByDb.setPassword(user.getPassword());
		userByDb.setPinCode(user.getPinCode());
		return userRepository.save(userByDb);
	}

	@Override
	public User getUserByUserId(Long userId) {
		User orElseThrow = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found with userId: "+userId));
//		Product[] forObject = restTemplate.getForObject("http://PRODUCTSERVICE/product/api/v1/getallproductbyuserid/"+orElseThrow.getUserId(), Product[].class);
//		List<Product> asList = Arrays.asList(forObject);
		ResponseEntity<List<Product>> findProductByUserId = productService.findProductByUserId(userId);
		List<Product> asList = findProductByUserId.getBody();
		orElseThrow.setProducts(asList);
		return orElseThrow;
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public String deleteUser(Long userId) {
		User orElseThrow = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found with userId: "+userId));
		userRepository.deleteById(orElseThrow.getUserId());
		return "delete successful userId: "+orElseThrow.getUserId();
	}

	@Override
	public User getUserByEmail(String email) {
		log.info("getUserByEmail servive start");
		User user =  userRepository.findByEmail(email).orElseThrow(()-> new EmailNotFoundException("Email not found email Id: "+email));
		log.info("getUserByEmail servive end");
		return user;
	}

	@Override
	public List<User> findAllUserByAddress(String search) {
		return userRepository.findAllBySearch(search);
	}

}
