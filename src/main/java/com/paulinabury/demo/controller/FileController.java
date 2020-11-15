package com.paulinabury.demo.controller;

import com.paulinabury.demo.model.Student;
import com.paulinabury.demo.service.FileService;
import com.paulinabury.demo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping(path = "/school/save")
@Slf4j
public class FileController {

    private final FileService fileService;
    private final StudentService studentService;

    public FileController(FileService fileService, StudentService studentService) {
        this.fileService = fileService;
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<?> saveToFile() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(fileService.saveToFile(students, new File("students.txt")));
    }
}
