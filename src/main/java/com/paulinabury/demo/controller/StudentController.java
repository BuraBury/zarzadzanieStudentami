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
            log.info("Znaleziono studenta o id = " + id);
            return ResponseEntity.ok(student);
        } else {
            log.info("Brak studentów o podanym id; id = " + id);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/index/{index}")
    public ResponseEntity<?> getStudentByIndex(@PathVariable String index) {
        Student student = studentService.selectStudentByIndexNumber(index);
        if (Objects.nonNull(student)) {
            log.info("Odnaleziono studetna o nr indexu = " + index);
            return ResponseEntity.ok(student);
        } else {
            log.info("Błędny numer idnexu = " + index);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getStudentsByName(@PathVariable String name) {
        List<Student> students = studentService.selectStudentByName(name);
        if (Objects.nonNull(students)) {
            log.info("Odnaleziono studetna o imieniu = " + name);
            return ResponseEntity.ok(students);
        } else {
            log.info("Brak studentów o imieniu = " + name);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/surname/{surname}")
    public ResponseEntity<?> getStudentBySurname(@PathVariable String surname) {
        List<Student> students = studentService.selectStudentBySurname(surname);
        if (Objects.nonNull(students)) {
            log.info("Odnaleziono studetna o nazwisku = " + surname);
            return ResponseEntity.ok(students);
        } else {
            log.info("Brak studenta o nazwisku = " + surname);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/field/{field}")
    public ResponseEntity<?> getStudentsByField(@PathVariable String field) {
        List<Student> students = studentService.selectStudentByField(field);
        if (Objects.nonNull(students)) {
            log.info("Odnaleziono liste studentów kierunku " + field);
            return ResponseEntity.ok(students);
        } else {
            log.info("Błąd nazwy kierunku = " + field);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        log.info("Wyświetlono wszystkich studentów");
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable Long id) {
        if (studentService.deleteStudentById(id)) {
            log.info("usunięto studenta o id = " + id);
            return ResponseEntity.accepted().build();
        } else {
            log.info("Błędne id = " + id);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{indexNumber}")
    public ResponseEntity<?> deleteStudentByIndexNumber(@PathVariable String indexNumber) {
        if(studentService.deleteStudentByIndexNumber(indexNumber)) {
            log.info("usunięto studenta o numerze indeksu = " + indexNumber);
            return ResponseEntity.accepted().build();
        } else {
            log.info("Nie udało się usunąć, błędny numer indeksu");
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> addNewStudent(@RequestBody Student student) {
        log.info("Dodano do bazy " + student.toString());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.addNewStudent(student));
    }

    @PostMapping("/batch")
    public ResponseEntity<?> addBatchOfStudents(@RequestBody List<Student> students) {
        log.info("Dodano listę studentów do bazy");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.addBatchOfStudents(students));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudentByID(@PathVariable Long id, @RequestBody Student student) {
        studentService.updateStudentById(id, student);
        log.info("Zaktualizowano dane studenta " + student.toString());
        return ResponseEntity.accepted().build();
    }
}
