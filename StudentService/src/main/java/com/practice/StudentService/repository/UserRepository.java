package com.practice.StudentService.repository;

import com.practice.StudentService.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    User findByEmailAndPassword(String email, String password);
    Optional<User> findByEmail(String email);
}
