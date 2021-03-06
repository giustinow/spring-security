package com.security.springsecurity.api;

import com.security.springsecurity.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class MainController {

    public static final List<Student> STUDENTS = Arrays.asList(
            new Student(1,"Mario", "Rossi"),
            new Student(2, "Giorgio", "Bianchi"),
            new Student(3, "Giuseppe", "Neri")
    );

    @GetMapping(path = "{studentId}")
    public Student getStudentById(@PathVariable("studentId") Integer studentId) {
        return STUDENTS.stream()
                .filter(student -> student.getId().equals(studentId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Student " + studentId + " not found"));
    }
}
