package com.paulinabury.demo.service;


import com.paulinabury.demo.model.Student;

import java.util.List;

public interface StudentService {

    Student getStudentById(Long id);
    List<Student> getAllStudents();
    boolean deleteStudentById(Long id);
    Student addStudent(Student student);
    Student updateStudentById(Long id, Student student);
    List<Student> addBatchOfStudents(List<Student> students);

}
