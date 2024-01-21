package com.practice.StudentService.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Student {
    @Id
    private int studentId;
    private String studentName;
    private int studentClass;
    private User classTeacher;
    private List<Subject> subjects;
    private Department departmentName;
}
