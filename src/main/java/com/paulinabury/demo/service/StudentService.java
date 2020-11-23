package com.paulinabury.demo.service;

import com.paulinabury.demo.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Optional<Student> getStudentById(Long id);

    List<Student> getAllStudents();

    boolean deleteStudentById(Long id);

    Student addNewStudent(Student student);

    List<Student> addBatchOfStudents(List<Student> students);

    void updateStudentById(Long id, Student student);

}
