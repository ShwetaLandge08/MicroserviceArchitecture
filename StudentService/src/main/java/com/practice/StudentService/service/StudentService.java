package com.practice.StudentService.service;

import com.practice.StudentService.domain.Department;
import com.practice.StudentService.domain.Student;
import com.practice.StudentService.domain.User;
import com.practice.StudentService.exception.StudentAlreadyExistException;
import com.practice.StudentService.exception.StudentNotFoundException;

import java.util.List;

public interface StudentService {
    Student addStudent(Student student) throws StudentAlreadyExistException;
    List<Student>getAllStudentList() throws StudentNotFoundException;
    boolean deleteStudent(int studentId) throws StudentNotFoundException;
    Student getStudentOnStudentId(int id) throws StudentNotFoundException;
    List<Student>getStudentForClassTeacher(User user);
}
