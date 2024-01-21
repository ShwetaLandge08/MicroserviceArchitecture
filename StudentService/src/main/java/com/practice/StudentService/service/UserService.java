package com.practice.StudentService.service;

import com.practice.StudentService.domain.User;
import com.practice.StudentService.exception.UserAlreadyExistException;
import com.practice.StudentService.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    User register(User user) throws UserAlreadyExistException;
    List<User> getAllUser();
    User updateUser(String email, User user) throws UserNotFoundException;
    //User getUser(int id) throws UserNotFoundException;
    boolean deleteUser(String email) throws UserNotFoundException;
    User getUser(String email) throws UserNotFoundException;
}
