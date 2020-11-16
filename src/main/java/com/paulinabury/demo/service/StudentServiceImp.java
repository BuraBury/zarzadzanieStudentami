package com.paulinabury.demo.service;

import com.paulinabury.demo.model.Student;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Scope("singleton")
public class StudentServiceImp implements StudentService {

    private final Map<Long, Student> studentMap = new HashMap<>();
    private Long nextId = 1L;



    @Override
    public Student getStudentById(Long id) {
        return studentMap.getOrDefault(id, null);
    }

    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<>(studentMap.values());
    }

    @Override
    public boolean deleteStudentById(Long id) {
        if (studentMap.containsKey(id)) {
            studentMap.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public Student addStudent(Student student) {
        student.setId(getNextId());
        student.setGraduationDate(getGraduationDate(student.getStartDate()));
        return studentMap.put(student.getId(), student);
    }

    @Override
    public Student updateStudentById(Long id, Student student) {
        if (studentMap.containsKey(id)) {
            if (student.getName() != null) {
                studentMap.get(id).setName(student.getName());
            }
            if (student.getLastName() != null) {
                studentMap.get(id).setLastName(student.getLastName());
            }
            if (student.getIndexNumber() != null) {
                studentMap.get(id).setIndexNumber(student.getIndexNumber());
            }
            if (student.getField() != null) {
                studentMap.get(id).setField(student.getField());
            }
            if (student.getStartDate() != null) {
                studentMap.get(id).setStartDate(student.getStartDate());
            }
            return studentMap.get(id);
        }
        return null;
    }

    @Override
    public List<Student> addBatchOfStudents(List<Student> students) {
        for (Student student : students) {
            student.setGraduationDate(getGraduationDate(student.getStartDate()));
        }
        return addListOfStudents(students);
    }

    private List<Student> addListOfStudents(List<Student> students) {
        students.forEach(student -> {
            student.setGraduationDate(getGraduationDate(student.getStartDate()));
            student.setId(getNextId());
            studentMap.put(student.getId(), student);
        });
        return students;
    }

    private Long getNextId() {
        return nextId++;
    }

    private LocalDate getGraduationDate(LocalDate startDate) {
        return startDate.plusYears(5);
    }
}

