package com.exception.response.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exception.response.app.dao.UserRepo;
import com.exception.response.app.exception.UserEmailNotFoundException;
import com.exception.response.app.exception.UserExistException;
import com.exception.response.app.exception.UserNotFoundException;
import com.exception.response.app.model.User;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	// getAll
	public List<User> getAllUsers() throws UserExistException {
		List<User> user=userRepo.findAll();
		if(user.isEmpty()) {
			throw new UserExistException("There is no data Here");
		}
		return userRepo.findAll();
	}

	// create user
	public User addUser(User user) throws UserExistException {
		User userexists = userRepo.findByEmail(user.getEmail());
		if (userexists != null) {
			throw new UserExistException("User alredey created  :" + user.getEmail());

		}
		return userRepo.save(user);
	}

	// update user
	public User updateByid(User user, Long id) throws UserNotFoundException {
		Optional<User> exitsUser = userRepo.findById(id);
		if (!exitsUser.isPresent()) {
			throw new UserNotFoundException(" User Not Found to Update :" + id);
		}
		user.setId(id);
		return userRepo.save(user);
	}

	// findByid
	public Optional<User> findByUserId(Long id) throws UserNotFoundException {
		Optional<User> existUser = userRepo.findById(id);
		if (!existUser.isPresent()) {
			throw new UserNotFoundException("User Not Found By id :" + id);
		}
		return userRepo.findById(id);
	}

	// delete user
	public void deleteById(Long id) throws UserNotFoundException {
		Optional<User> userExist = userRepo.findById(id);
		if (!userExist.isPresent()) {
			throw new UserNotFoundException("User not Found to Delete With id :" + id);
		}
		userRepo.deleteById(id);
	}
	//findByEmail
	public User findByEmail(String email) throws UserEmailNotFoundException {
		User existUser=userRepo.findByEmail(email);
		if(existUser==null) {
			throw new UserEmailNotFoundException("User not found to find Email id :"+email);
		}
		return userRepo.findByEmail(email);
	}
	

}
