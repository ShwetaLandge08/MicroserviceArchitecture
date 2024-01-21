package com.practice.StudentService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND,reason = "Department not Found")
public class DepartmentNotFoundException extends Exception{
}
