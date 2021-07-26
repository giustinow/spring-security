package com.security.springsecurity.api;

import com.security.springsecurity.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    public static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    public static final List<Student> STUDENTS = Arrays.asList(
            new Student(1,"Mario", "Rossi"),
            new Student(2, "Giorgio", "Bianchi"),
            new Student(3, "Giuseppe", "Neri")
    );


    @GetMapping(path = "{studentId}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Student> getStudentById(@PathVariable("studentId") Integer studentId) {
        Student studentFound = STUDENTS.stream()
                .filter(student -> student.getId().equals(studentId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Student " + studentId + " not found"));
        return ResponseEntity.ok(studentFound);
    }

    @GetMapping(path = "/listAll")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Student>> listAll() {
        return new ResponseEntity<>(STUDENTS, HttpStatus.OK);
    }

    @PostMapping(path = "saveStudent")
    public ResponseEntity<List<Student>> saveStudent() {
        return ResponseEntity.ok().body(STUDENTS);
    }



}
