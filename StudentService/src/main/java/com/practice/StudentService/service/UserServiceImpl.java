package com.practice.StudentService.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.practice.StudentService.domain.User;
import com.practice.StudentService.exception.UserAlreadyExistException;
import com.practice.StudentService.exception.UserNotFoundException;
import com.practice.StudentService.proxy.IUserProxy;
import com.practice.StudentService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final IUserProxy proxy;
    private final UserRepository repository;
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    public UserServiceImpl(IUserProxy proxy, UserRepository repository) {
        this.proxy = proxy;
        this.repository = repository;
    }

    @HystrixCommand(fallbackMethod = "callUserService_Fallback")
    @Override
    public User register(User user) throws UserAlreadyExistException {
        if (repository.existsById(user.getEmail())) throw new UserAlreadyExistException();
        proxy.register(user);
        user.setPhoneNo(null);
        user.setPassword(null);
        return repository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return repository.findAll();
    }

   // @HystrixCommand(fallbackMethod = "callUserService_Fallback")
    @Override
    public User updateUser(String email, User user) throws UserNotFoundException {
        Optional<User> optional = repository.findByEmail(email);
        if (optional.isEmpty()) {
            throw new UserNotFoundException();
        }
        User existingUser = optional.get();
        if (existingUser.getName() != null) {
            existingUser.setName(user.getName());
        }
        if (existingUser.getPhoneNo() != null) {
            existingUser.setPhoneNo(user.getPhoneNo());
        }

        proxy.updateUser(existingUser);
        existingUser.setPassword(null);
        existingUser.setPhoneNo(null);
        return repository.save(existingUser);
    }

    @Override
    public User getUser(String email) throws UserNotFoundException {
        Optional<User> optional = repository.findByEmail(email);
        if (optional.isEmpty()) {
            throw new UserNotFoundException();
        }
        return optional.get();
    }

    //@HystrixCommand(fallbackMethod = "callUserService_Fallback")
    @Override
    public boolean deleteUser(String email) throws UserNotFoundException {
        boolean res = true;
        Optional<User> u1 = repository.findById(email);
        if (u1.isEmpty()) {
            res = false;
            throw new UserNotFoundException();
        }
        proxy.deleteUser(email);
        repository.deleteById(email);
        return res;
    }

    private User callUserService_Fallback(User user) {

        System.out.println("CIRCUIT BREAKER ENABLED!!! No Response From User Service at this moment. " +
                " Service will be back shortly - " + new Date());
//        System.out.println(user);
        return user;
    }
}