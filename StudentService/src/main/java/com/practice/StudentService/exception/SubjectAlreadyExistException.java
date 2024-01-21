package com.practice.StudentService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.CONFLICT,reason = "Subject Already Exist")
public class SubjectAlreadyExistException extends Exception{
}
