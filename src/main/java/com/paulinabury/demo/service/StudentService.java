package com.paulinabury.demo.service;

import com.paulinabury.demo.model.Student;

import java.io.File;
import java.io.IOException;
import java.util.List;


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

    List<Student> findByKeyword(String keyword);

    File save(List<Student> list) throws IOException;


}
