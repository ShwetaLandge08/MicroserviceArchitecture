package com.practice.StudentService.service;

import com.practice.StudentService.domain.Student;
import com.practice.StudentService.domain.Subject;
import com.practice.StudentService.exception.*;

public interface SubjectService {
    Student addSubjectForStudent(int studentId, Subject subject)throws SubjectAlreadyExistException, StudentNotFoundException;
    Student deleteSubjectForStudent(int studentId,int subjectId)throws SubjectNotFoundException,StudentNotFoundException;
    Student updateSubject(int studentId, Subject subject) throws StudentNotFoundException,SubjectNotFoundException;
}
