package com.tweetapp.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.api.exception.InvalidUsernameOrPasswordException;
import com.tweetapp.api.exception.UsernameAlreadyExists;
import com.tweetapp.api.model.User;
import com.tweetapp.api.model.UserResponse;
import com.tweetapp.api.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	String Status = "success";

	@Autowired(required = true)
	UserRepository userRepository;



	@Override
	public User createUser(User user) throws UsernameAlreadyExists {
		if (userRepository.findByUsername(user.getFirstName()) != null)
			throw new UsernameAlreadyExists("Username already exists");
		logger.info("Registration successfully...");
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public int deleteUser(User user) {
		userRepository.delete(user);
		logger.info("Deleted user successfully..");
		return 1;
	}

	@Override
	public List<User> getAllUsers() {
		logger.info("Retriving all the users");
		return userRepository.findAll();
	}

	@Override
	public List<User> getUserByUsername(String username) throws InvalidUsernameOrPasswordException {
		if (userRepository.findByUsernameContaining(username) == null)
			throw new InvalidUsernameOrPasswordException("Please enter a valid username");
		logger.info("Retriving the user by the username.." + username);
		return userRepository.findByUsernameContaining(username);
	}

	@Override
	public Optional<User> getUserById(String id) {
		return userRepository.findById(id);
	}

	@Override
	public UserResponse loginUser(String username, String password) throws InvalidUsernameOrPasswordException {

		UserResponse response = new UserResponse();
		try {
			User user = userRepository.findByUsername(username);
			if (user != null) {
				if (user.getPassword().equals(password)) {
					response.setUser(user);
					response.setLoginStatus("success");
					logger.info("Login Successful..");
				} else
					throw new InvalidUsernameOrPasswordException("Invalid Username Or Password Exception");
			} else
				throw new InvalidUsernameOrPasswordException("Invalid Username Or Password Exception");
		} catch (InvalidUsernameOrPasswordException invalidUsernameOrPasswordException) {
			response.setLoginStatus("failed");
			logger.info("Login failed");
			throw new InvalidUsernameOrPasswordException("Invalid Username Or Password Exception");
		}
		return response;
	}

	@Override
	public Map<String, String> forgotPassword(String username) {
		Map<String, String> map = new HashMap<String, String>();
		User user = userRepository.findByUsername(username);
		Long.toHexString(Double.doubleToLongBits(Math.random()));
		UUID.randomUUID().toString();
		String password = RandomStringUtils.randomAlphanumeric(8);
		user.setPassword(password);
		userRepository.save(user);
		map.put("newPassword", user.getPassword());
		map.put("resetStatus", Status);
		logger.info("Completed..");
		return map;
	}

	@Override
	public Map<String, String> resetPassword(String username, String password) {
		Map<String, String> map = new HashMap<String, String>();
		User user = userRepository.findByUsername(username);
		user.setPassword(password);
		userRepository.save(user);
		map.put("newPassword", user.getPassword());
		map.put("resetStatus", Status);
		logger.info("Reset password successful..");
		return map;
	}

}
