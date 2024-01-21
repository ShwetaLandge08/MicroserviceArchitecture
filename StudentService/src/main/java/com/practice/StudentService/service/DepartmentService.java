package com.practice.StudentService.service;

import com.practice.StudentService.domain.Department;
import com.practice.StudentService.domain.Student;
import com.practice.StudentService.exception.DepartmentAlreadyExistException;
import com.practice.StudentService.exception.DepartmentNotFoundException;
import com.practice.StudentService.exception.StudentNotFoundException;
import com.practice.StudentService.repository.StudentRepository;

import java.util.List;

public interface DepartmentService {
    Student addDepartmentForStudent(int studentId, Department department)throws DepartmentAlreadyExistException, StudentNotFoundException;
    Student deleteDepartmentForStudent(int studentId,int departmentId)throws DepartmentNotFoundException,StudentNotFoundException;
  // Student updateDepartment(int studentId, Department department) throws StudentNotFoundException,DepartmentNotFoundException;

}
