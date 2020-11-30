package com.paulinabury.demo.service;

import com.paulinabury.demo.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Student getStudentById(Long id);

    List<Student> getAllStudents();

    boolean deleteStudentById(Long id);

    boolean deleteStudentByIndexNumber(String index);

    Student addNewStudent(Student student);

    List<Student> addBatchOfStudents(List<Student> students);

    void updateStudentById(Long id, Student student);

    Student selectStudentByIndexNumber(String indexNumber);

    List<Student> selectStudentByName(String name);

    List<Student> selectStudentBySurname(String surname);

    List<Student> selectStudentByField(String field);

}
