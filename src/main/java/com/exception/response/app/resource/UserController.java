package com.exception.response.app.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.exception.response.app.exception.UserEmailNotFoundException;
import com.exception.response.app.exception.UserExistException;
import com.exception.response.app.exception.UserNotFoundException;
import com.exception.response.app.model.User;
import com.exception.response.app.service.UserService;
@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	//getAll
	@GetMapping("/")
	public List<User> getAll() throws UserExistException{
		return userService.getAllUsers();
	}
	//add user
	@PostMapping("/user")
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user,UriComponentsBuilder builder){
		try {
			userService.addUser(user);
			HttpHeaders headers=new HttpHeaders();
			headers.setLocation(builder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(headers,HttpStatus.CREATED);

		}catch (UserExistException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
		}
	}
	@PutMapping("/user/{id}")
	public User updateByUser(@Valid @RequestBody User user,@PathVariable("id")Long id) {
		try {
			return userService.updateByid(user, id);
		}catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
		
	}
	//getByid
	@GetMapping("/user/{id}")
	public Optional<User> getByid(@PathVariable("id")Long id){
		try {
			return userService.findByUserId(id);
		}catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	//delete by id
	@DeleteMapping("/user/{id}")
	public void deleteByid(@PathVariable("id")Long id) {
		try {
			 userService.deleteById(id);
		}catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	//findByEmail
	@GetMapping("/user/id/{email}")
	public User getByEmail(@PathVariable("email")String email) {
		try {
			return userService.findByEmail(email);
		}catch (UserEmailNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

}
