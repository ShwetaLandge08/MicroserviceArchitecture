package com.practice.StudentService.service;

import com.practice.StudentService.domain.Subject;
import com.practice.StudentService.domain.Student;
import com.practice.StudentService.exception.StudentNotFoundException;
import com.practice.StudentService.exception.SubjectAlreadyExistException;
import com.practice.StudentService.exception.SubjectNotFoundException;
import com.practice.StudentService.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {
    private final StudentRepository repository;

    @Autowired
    public SubjectServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Student addSubjectForStudent(int studentId, Subject subject) throws SubjectAlreadyExistException, StudentNotFoundException {
        Student student = repository.findById(studentId).orElseThrow(StudentNotFoundException::new);
        List<Subject> subjects = student.getSubjects();
        if (subjects != null && subjects.stream().anyMatch(s -> s.getSubjectId() == subject.getSubjectId()))
            throw new SubjectAlreadyExistException();
        if (subjects == null)
            subjects = new ArrayList<>();
        int lastId = 200;
        if (!subjects.isEmpty()) {
            lastId = ((Subject) subjects.toArray()[subjects.size() - 1]).getSubjectId();
        }
        subject.setSubjectId(lastId + 1);
        subjects.add(subject);
        student.setSubjects(subjects);
        return repository.save(student);
    }

    @Override
    public Student deleteSubjectForStudent(int studentId, int subjectId) throws SubjectNotFoundException, StudentNotFoundException {
        Student student = repository.findById(studentId).orElseThrow(StudentNotFoundException::new);
        List<Subject> subjects = student.getSubjects();
        if (subjects.stream().noneMatch(s -> s.getSubjectId() == subjectId))
            throw new SubjectNotFoundException();
        subjects.removeIf(s -> s.getSubjectId() == subjectId);
        student.setSubjects(subjects);
        return repository.save(student);
    }

    @Override
    public Student updateSubject(int studentId, Subject subject) throws StudentNotFoundException, SubjectNotFoundException {
        Student student = repository.findById(studentId).orElseThrow(StudentNotFoundException::new);
        List<Subject> subjects = student.getSubjects();
        if (subjects.stream().noneMatch(s -> s.getSubjectId() == subject.getSubjectId()))
            throw new SubjectNotFoundException();
        Subject subject1 = subjects.stream().filter(s -> s.getSubjectId() == subject.getSubjectId()).findFirst().get();
        subjects.remove(subject1);
        subjects.add(subject);
        student.setSubjects(subjects);
        return repository.save(student);
    }
}
