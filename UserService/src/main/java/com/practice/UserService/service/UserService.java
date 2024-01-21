package com.practice.UserService.service;

import com.practice.UserService.domain.User;
import com.practice.UserService.exception.UserAlreadyExistException;
import com.practice.UserService.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    User register(User user) throws UserAlreadyExistException;
    User login(String email,String password) throws UserNotFoundException;
    List<User> getAllUser();
    User updateUser(User user) throws UserNotFoundException;
    //User getUser(int id) throws UserNotFoundException;
    boolean deleteUser(String email) throws UserNotFoundException;
}
