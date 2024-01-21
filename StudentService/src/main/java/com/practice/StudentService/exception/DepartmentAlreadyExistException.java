package com.practice.StudentService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.CONFLICT,reason = "Department Already Exist")
public class DepartmentAlreadyExistException extends Exception{
}