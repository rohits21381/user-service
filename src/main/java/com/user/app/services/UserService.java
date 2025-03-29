package com.user.app.services;

import java.util.List;

import com.user.app.entity.User;

public interface UserService {

	User saveUser(User user);
	User updateUser(User user, Long userId);
	User getUserByUserId(Long userId);
	List<User> getAllUser();
	String deleteUser(Long userId);
	User getUserByEmail(String email);
	List<User> findAllUserByAddress(String search);
}
