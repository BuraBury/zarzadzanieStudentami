package com.paulinabury.demo.service;

import com.paulinabury.demo.exceptions.*;
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
    public Student getStudentById(Long id) {
        return studentRepository.selectStudentByIdEqualTo(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public boolean deleteStudentById(Long id) throws WrongIdException {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        } else {
            throw new WrongIdException("Podano błędne id");
        }
    }

    @Override
    public boolean deleteStudentByIndexNumber(String index) throws WrongIndexNumberException {
        Student student = selectStudentByIndexNumber(index);
        if (student != null) {
            studentRepository.deleteById(student.getId());
            return true;
        } else {
            throw new WrongIndexNumberException("Podano błędny numer indeksu");
        }
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
    public void updateStudentById(Long id, Student student) throws WrongIdException {
        if (studentRepository.existsById(id)) {
            student.setId(id);
            studentRepository.save(student);
        } else {
            throw new WrongIdException("Podano błędne id");
        }
    }

    @Override
    public Student selectStudentByIndexNumber(String indexNumber) throws WrongIndexNumberException {
        if (studentRepository.selectAllStudentsWithIndexEqualTo(indexNumber) == null)
            throw new WrongIndexNumberException("Podano błędny nr indeksu");
        return studentRepository.selectAllStudentsWithIndexEqualTo(indexNumber);
    }

    @Override
    public List<Student> selectStudentByName(String name) throws WrongNameException {
        if (studentRepository.selectAllStudentsWithNameEqualsTo(name).size() == 0)
            throw new WrongNameException("Brak studentów o podanym imieniu");
        return studentRepository.selectAllStudentsWithNameEqualsTo(name);
    }

    @Override
    public List<Student> selectStudentBySurname(String surname) throws WrongSurnameException {
        if (studentRepository.selectAllStudentsWithSurnameEqualsTo(surname).size() == 0)
            throw new WrongSurnameException("Brak studentów o podanym nazwisku");
        return studentRepository.selectAllStudentsWithSurnameEqualsTo(surname);
    }

    @Override
    public List<Student> selectStudentByField(String field) throws WrongFieldException {
        if (studentRepository.selectAllStudentsWithFieldEqualsTo(field).size() == 0)
            throw new WrongFieldException("Brak studentów na podanym kierunku");
        return studentRepository.selectAllStudentsWithFieldEqualsTo(field);
    }

}
