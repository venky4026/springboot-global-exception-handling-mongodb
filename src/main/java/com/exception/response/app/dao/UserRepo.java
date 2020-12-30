package com.exception.response.app.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.exception.response.app.model.User;

@Repository
public interface UserRepo extends MongoRepository<User, Long>{
	public User findByEmail(String email);

}
