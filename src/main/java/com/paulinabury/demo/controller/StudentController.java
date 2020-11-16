package com.paulinabury.demo.controller;

import com.paulinabury.demo.model.Student;
import com.paulinabury.demo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/school/student")
@Slf4j
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        if (Objects.nonNull(student)) {
            log.info("Successfully searched student by id");
            return ResponseEntity.ok(student);
        } else {
            log.info("404 not found");
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        log.info("Successfully searched all students");
        return ResponseEntity.ok(studentService.getAllStudents());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        if (studentService.deleteStudentById(id)) {
            log.info("Successfully deleted student by id");
            return ResponseEntity.accepted().build();
        } else {
            log.info("Not found");
            return ResponseEntity.badRequest().build();
        }
    }


    @PostMapping(path = "/batch")
    public ResponseEntity<?> addStudent(@RequestBody List<Student> students) {
        log.info("Successfully added all students");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.addBatchOfStudents(students));
    }


    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        log.info("Successfully added one student");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.addStudent(student));
    }


    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateStudentById(@PathVariable Long id, @RequestBody Student student) {
        log.info("Successfully updated student's data");
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(studentService.updateStudentById(id, student));
    }

}
