package com.security.springsecurity.api;

import com.security.springsecurity.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    public static final List<Student> STUDENTS = Arrays.asList(
            new Student(1,"Mario", "Rossi"),
            new Student(2, "Giorgio", "Bianchi"),
            new Student(3, "Giuseppe", "Neri")
    );


    @GetMapping(path = "{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable("studentId") Integer studentId) {
        Student studentFound = STUDENTS.stream()
                .filter(student -> student.getId().equals(studentId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Student " + studentId + " not found"));
        return ResponseEntity.ok(studentFound);
    }

    @GetMapping(path = "/listAll")
    public ResponseEntity<List<Student>> listAll() {
        return new ResponseEntity<>(STUDENTS, HttpStatus.OK);
    }



}
