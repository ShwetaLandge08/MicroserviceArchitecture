package com.practice.StudentService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND,reason = "Student not Found")
public class StudentNotFoundException extends Exception{
}
