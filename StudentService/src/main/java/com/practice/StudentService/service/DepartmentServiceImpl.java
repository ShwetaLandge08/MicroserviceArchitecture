package com.practice.StudentService.service;

import com.practice.StudentService.domain.Department;
import com.practice.StudentService.domain.Student;
import com.practice.StudentService.exception.DepartmentAlreadyExistException;
import com.practice.StudentService.exception.DepartmentNotFoundException;
import com.practice.StudentService.exception.StudentNotFoundException;
import com.practice.StudentService.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final StudentRepository repository;
    List<Department> departmentList;


    @Autowired
    public DepartmentServiceImpl(StudentRepository repository) {
        this.repository = repository;
        departmentList = new ArrayList<>();
        departmentList.add(new Department(101, "Art"));
        departmentList.add(new Department(102, "Science"));
        departmentList.add(new Department(103, "Commerce"));
        departmentList.add(new Department(104, "Social Science"));
        departmentList.add(new Department(105, "Agriculture"));
        departmentList.add(new Department(106, "Politics"));
        departmentList.add(new Department(107, "Literature"));
    }


    @Override
    public Student addDepartmentForStudent(int studentId, int departmentId) throws DepartmentAlreadyExistException, StudentNotFoundException {
        Student student = repository.findById(studentId).orElseThrow(StudentNotFoundException::new);
        Department department = departmentList.stream().filter(department1 -> department1.getDepartmentId() == departmentId).findFirst().orElseThrow();

        if (student.getDepartmentName() != null && student.getDepartmentName().getDepartmentId() == department.getDepartmentId()) {
            throw new DepartmentAlreadyExistException();
        }
        if (departmentList.stream().noneMatch(dd -> dd.getDepartmentId() == department.getDepartmentId()))
            throw new RuntimeException("This Department is Not available in school");
        student.setDepartmentName(department);
        return repository.save(student);
    }

    @Override
    public Student deleteDepartmentForStudent(int studentId, int departmentId) throws
            DepartmentNotFoundException, StudentNotFoundException {
        Student student = repository.findById(studentId).orElseThrow(StudentNotFoundException::new);
        if (student.getDepartmentName().getDepartmentId() != departmentId)
            throw new DepartmentNotFoundException();
        student.setDepartmentName(null);
        return repository.save(student);
    }

//    @Override
//    public Student updateDepartment(int studentId, Department department) throws
//            StudentNotFoundException, DepartmentNotFoundException {
//        Student student = repository.findById(studentId).orElseThrow(StudentNotFoundException::new);
//        if (student.getDepartmentName().getDepartmentId() != department.getDepartmentId())
//            throw new DepartmentNotFoundException();
//        student.setDepartmentName(department);
//        return repository.save(student);
//    }
}
