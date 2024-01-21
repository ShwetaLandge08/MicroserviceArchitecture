package com.practice.UserService.controller;

import com.practice.UserService.domain.User;
import com.practice.UserService.exception.UserAlreadyExistException;
import com.practice.UserService.exception.UserNotFoundException;
import com.practice.UserService.security.JWTSecurityTokenGenerator;
import com.practice.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService service;
    private final JWTSecurityTokenGenerator jwtSecurityTokenGenerator;

    @Autowired
    public UserController(UserService service, JWTSecurityTokenGenerator jwtSecurityTokenGenerator) {
        this.service = service;
        this.jwtSecurityTokenGenerator = jwtSecurityTokenGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) throws UserAlreadyExistException {
        return new ResponseEntity<>(service.register(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) throws UserNotFoundException {
        Map<String, String> map;
        try {
            User logginUser = service.login(user.getEmail(), user.getPassword());
            map = jwtSecurityTokenGenerator.generateToken(logginUser);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user) throws UserNotFoundException {
        return new ResponseEntity<>(service.updateUser(user), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email) throws UserNotFoundException {
        return new ResponseEntity<>(service.deleteUser(email), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(service.getAllUser(), HttpStatus.OK);
    }
}
