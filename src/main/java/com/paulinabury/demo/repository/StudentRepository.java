package com.paulinabury.demo.repository;

import com.paulinabury.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query (value = "select s from student s where s.id= :id")
    Student selectStudentByIdEqualTo(@Param("id") Long id);

    @Query(value = "select s from student s where s.indexNumber= :indexNumber")
    Student selectAllStudentsWithIndexEqualTo(@Param("indexNumber") String indexNumber);

    @Query(value = "select s from student s where s.name= :name")
    List<Student> selectAllStudentsWithNameEqualsTo(@Param("name") String name);

    @Query(value = "select s from student s where s.field= :field")
    List<Student> selectAllStudentsWithFieldEqualsTo(@Param("field") String field);

    @Query(value = "select s from student s where s.surname= :surname")
    List<Student> selectAllStudentsWithSurnameEqualsTo(@Param("surname") String surname);
//
//    @Query(value = "delete s from student s where id= :id")
//    boolean deleteStudentByIdEqualsTo(@Param("id") Long id);

}
