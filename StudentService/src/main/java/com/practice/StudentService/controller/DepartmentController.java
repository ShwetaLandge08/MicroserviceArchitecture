package com.practice.StudentService.controller;

import com.practice.StudentService.domain.Department;
import com.practice.StudentService.domain.User;
import com.practice.StudentService.exception.DepartmentAlreadyExistException;
import com.practice.StudentService.exception.DepartmentNotFoundException;
import com.practice.StudentService.exception.StudentNotFoundException;
import com.practice.StudentService.exception.UserAlreadyExistException;
import com.practice.StudentService.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PutMapping("/addDepartment/{studentId}")
    public ResponseEntity<?> addDepartment(@RequestBody int departmentId, @PathVariable int studentId) throws StudentNotFoundException, DepartmentAlreadyExistException {
        System.out.println(departmentId);
        return new ResponseEntity<>(departmentService.addDepartmentForStudent(studentId, departmentId), HttpStatus.OK);
    }

    @PutMapping("/deleteDepartment/{studentId}/{departmentId}")
    public ResponseEntity<?> deleteDepartment(@PathVariable int departmentId, @PathVariable int studentId) throws StudentNotFoundException, DepartmentNotFoundException {
        return new ResponseEntity<>(departmentService.deleteDepartmentForStudent(studentId, departmentId), HttpStatus.OK);
    }

//    @PutMapping("/updateDepartment/{studentId}")
//    public ResponseEntity<?> updateDepartment(@RequestBody Department department, @PathVariable int studentId) throws StudentNotFoundException, DepartmentNotFoundException {
//        return new ResponseEntity<>(departmentService.updateDepartment(studentId, department), HttpStatus.OK);
//    }

}
