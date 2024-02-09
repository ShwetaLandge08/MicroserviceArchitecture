package com.practice.StudentService.service;

import com.practice.StudentService.domain.Student;
import com.practice.StudentService.domain.User;
import com.practice.StudentService.exception.StudentAlreadyExistException;
import com.practice.StudentService.exception.StudentNotFoundException;
import com.practice.StudentService.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository repository;

    @Autowired
    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Student addStudent(Student student) throws StudentAlreadyExistException {
        if (student.getStudentId() != 0 && repository.existsById(student.getStudentId()))
            throw new StudentAlreadyExistException();
        List<Student> students = repository.findAll();
        int lastId = 0;
        if (!students.isEmpty()) {
            lastId = ((Student) students.toArray()[students.size() - 1]).getStudentId();
        }
        student.setStudentId(lastId + 1);
        return repository.save(student);
    }

    @Override
    public List<Student> getAllStudentList() throws StudentNotFoundException {
        List<Student> students = repository.findAll();
        if (students.isEmpty()) throw new StudentNotFoundException();
        return students;
    }

    @Override
    public boolean deleteStudent(int studentId) throws StudentNotFoundException {
        if (!repository.existsById(studentId)) throw new StudentNotFoundException();
        repository.deleteById(studentId);
        return true;
    }

    @Override
    public Student getStudentOnStudentId(int id) throws StudentNotFoundException {
        return repository.findById(id).orElseThrow(StudentNotFoundException::new);
    }

    @Override
    public List<Student> getStudentForClassTeacher(User user) {
        List<Student> all = repository.findAll();
        return all.stream().filter(student -> student.getClassTeacher().equals(user)).toList();
    }
}
