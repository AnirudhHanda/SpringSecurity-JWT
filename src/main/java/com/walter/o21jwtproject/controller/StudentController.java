package com.walter.o21jwtproject.controller;

import com.walter.o21jwtproject.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    private List<Student> students = List.of(new Student(1, "Homi Bhabha", "Java"), new Student(2, "Vikram Sarabhai", "Python"));

    @GetMapping("students")
    public ResponseEntity<?> getAllStudents() {
        if(students.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(students, org.springframework.http.HttpStatus.OK);
    }

    @PostMapping("students")
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        students.add(student);

        return new ResponseEntity<>(student, org.springframework.http.HttpStatus.OK);
    }
}
