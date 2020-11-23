package com.paulinabury.demo.service;

import com.paulinabury.demo.model.Student;
import com.paulinabury.demo.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Scope("prototype")
@Slf4j
@Primary
public class StudentServiceDbImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceDbImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public boolean deleteStudentById(Long id) {
        studentRepository.deleteById(id);
        return true;
    }

    @Override
    public Student addNewStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> addBatchOfStudents(List<Student> students) {
        return studentRepository.saveAll(students);
    }

    @Override
    public void updateStudentById(Long id, Student student) {
        if(studentRepository.existsById(id)) {
            student.setId(id);
            studentRepository.save(student);
        }

    }

}
