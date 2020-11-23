package com.paulinabury.demo.controller;

import com.paulinabury.demo.model.Student;
import com.paulinabury.demo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(path = "/school/student")
@Slf4j
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        Optional<Student> student = studentService.getStudentById(id);
        if (Objects.nonNull(student)) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/index/{index}")
    public ResponseEntity<?> getStudentByIndex(@PathVariable String index) {
        List<Student> students = studentService.selectStudentByIndexNumber(index);
        if (Objects.nonNull(students)) {
            return ResponseEntity.ok(students);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getStudentsByName(@PathVariable String name) {
        List<Student> students = studentService.selectStudentByName(name);
        if (Objects.nonNull(students)) {
            return ResponseEntity.ok(students);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/field/{field}")
    public ResponseEntity<?> getStudentsByField(@PathVariable String field) {
        List<Student> students = studentService.selectStudentByField(field);
        if (Objects.nonNull(students)) {
            return ResponseEntity.ok(students);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable Long id) {
        if (studentService.deleteStudentById(id)) {
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> addNewStudent(@RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.addNewStudent(student));
    }

    @PostMapping("/batch")
    public ResponseEntity<?> addBatchOfStudents(@RequestBody List<Student> students) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.addBatchOfStudents(students));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudentByID(@PathVariable Long id, @RequestBody Student student) {
        studentService.updateStudentById(id, student);
        return ResponseEntity.accepted().build();
    }
}
