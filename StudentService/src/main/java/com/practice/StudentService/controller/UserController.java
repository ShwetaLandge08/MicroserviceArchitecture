package com.practice.StudentService.controller;

import com.practice.StudentService.domain.User;
import com.practice.StudentService.exception.UserAlreadyExistException;
import com.practice.StudentService.exception.UserNotFoundException;
import com.practice.StudentService.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/student/user")
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) throws UserAlreadyExistException {
        return new ResponseEntity<>(service.register(user), HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(service.getAllUser(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(HttpServletRequest request, @RequestBody User user) throws UserNotFoundException {
        Claims claims = (Claims) request.getAttribute("claims");
        String email = claims.getSubject();
        return new ResponseEntity<>(service.updateUser(email, user), HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(HttpServletRequest request) throws UserNotFoundException {
        Claims claims = (Claims) request.getAttribute("claims");
        String email = claims.getSubject();
        return new ResponseEntity<>(service.deleteUser(email), HttpStatus.OK);
    }
}
