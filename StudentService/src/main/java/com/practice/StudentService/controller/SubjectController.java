package com.practice.StudentService.controller;

import com.practice.StudentService.domain.Subject;
import com.practice.StudentService.exception.*;
import com.practice.StudentService.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student/subject")
public class SubjectController {
    private final SubjectService service;

    @Autowired
    public SubjectController(SubjectService service) {
        this.service = service;
    }

    @PutMapping("/addSubject/{studentId}")
    public ResponseEntity<?> addSubject(@RequestBody Subject subject, @PathVariable int studentId) throws StudentNotFoundException, SubjectAlreadyExistException {
        return new ResponseEntity<>(service.addSubjectForStudent(studentId, subject), HttpStatus.OK);
    }

    @PutMapping("/deleteSubject/{studentId}/{subjectId}")
    public ResponseEntity<?> deleteSubject(@PathVariable int subjectId, @PathVariable int studentId) throws StudentNotFoundException, SubjectNotFoundException {
        return new ResponseEntity<>(service.deleteSubjectForStudent(studentId, subjectId), HttpStatus.OK);
    }

    @PutMapping("/updateSubject/{studentId}")
    public ResponseEntity<?> updateSubject(@RequestBody Subject subject, @PathVariable int studentId) throws StudentNotFoundException, SubjectNotFoundException {
        return new ResponseEntity<>(service.updateSubject(studentId, subject), HttpStatus.OK);
    }

}
