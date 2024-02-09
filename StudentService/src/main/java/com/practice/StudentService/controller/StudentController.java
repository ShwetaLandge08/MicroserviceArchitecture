package com.practice.StudentService.controller;

import com.practice.StudentService.domain.Student;
import com.practice.StudentService.domain.User;
import com.practice.StudentService.exception.StudentAlreadyExistException;
import com.practice.StudentService.exception.StudentNotFoundException;
import com.practice.StudentService.exception.UserNotFoundException;
import com.practice.StudentService.service.StudentService;
import com.practice.StudentService.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/student/student")
public class StudentController {
    private final StudentService service;
    private final UserService userService;

    @Autowired
    public StudentController(StudentService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @PostMapping("/addStudent")
    public ResponseEntity<?> createStudent(@RequestBody Student student, HttpServletRequest servletRequest) {
        System.out.println(servletRequest.getHeader("Authorization"));
        System.out.println(servletRequest);
        Claims claims = (Claims) servletRequest.getAttribute("claims");

                System.out.println(claims);
        String email = claims.getSubject();
        System.out.println(email);
        try {
            User user = userService.getUser(email);
            user.setPhoneNo(null);
            user.setPassword(null);
            student.setClassTeacher(user);
            return new ResponseEntity<>(service.addStudent(student), HttpStatus.CREATED);
        } catch (StudentAlreadyExistException | UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/students")
    public ResponseEntity<?> getAllStudents() throws StudentNotFoundException {
        return new ResponseEntity<>(service.getAllStudentList(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable int studentId) {
        try {
            return new ResponseEntity<>(service.deleteStudent(studentId), HttpStatus.OK);
        } catch (StudentNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findStudent(@PathVariable int id) {
        try {
            return new ResponseEntity<>(service.getStudentOnStudentId(id), HttpStatus.OK);
        } catch (StudentNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/teacher")
    public ResponseEntity<?> findStudentOnTeacherBasis(HttpServletRequest servletRequest) {
        System.out.println(servletRequest.getHeader("Authorization"));
        System.out.println(servletRequest);
        Claims claims = (Claims) servletRequest.getAttribute("claims");

        System.out.println(claims);
        String email = claims.getSubject();
        System.out.println(email);
        try {
            User user = userService.getUser(email);
            return new ResponseEntity<>(service.getStudentForClassTeacher(user), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
