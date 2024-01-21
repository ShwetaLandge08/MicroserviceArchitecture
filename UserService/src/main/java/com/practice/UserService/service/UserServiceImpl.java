package com.practice.UserService.service;

import com.practice.UserService.domain.User;
import com.practice.UserService.exception.UserAlreadyExistException;
import com.practice.UserService.exception.UserNotFoundException;
import com.practice.UserService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User register(User user) throws UserAlreadyExistException {
        if (repository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistException();
        }
        return repository.save(user);
    }

    @Override
    public User login(String email, String password) throws UserNotFoundException {
        User u1 = repository.findByEmailAndPassword(email, password);
        if (u1 == null) {
            throw new UserNotFoundException();
        }
        return u1;
    }

    @Override
    public List<User> getAllUser() {
        return repository.findAll();
    }

    @Override
    public User updateUser(User user) throws UserNotFoundException {
        Optional<User> optional = repository.findByEmail(user.getEmail());
        if (optional.isEmpty()) {
            throw new UserNotFoundException();
        }
        User existingUser = optional.get();
        if (existingUser.getUserName() != null) {
            existingUser.setUserName(user.getUserName());
        }
        if (existingUser.getPhoneNo() != null) {
            existingUser.setPhoneNo(user.getPhoneNo());
        }
        if (existingUser.getPassword() != null) {
            existingUser.setPassword(user.getPassword());
        }
        return repository.save(existingUser);
    }

//    @Override
//    public User getUser(int id) throws UserNotFoundException {
//        return repository.findById(id).orElseThrow(UserNotFoundException::new);
//    }

    @Override
    public boolean deleteUser(String email) throws UserNotFoundException {
        boolean res = true;
        Optional<User> u1 = repository.findById(email);
        if (u1.isEmpty()) {
            res = false;
            throw new UserNotFoundException();
        }
        repository.deleteById(email);
        return res;
    }
}
