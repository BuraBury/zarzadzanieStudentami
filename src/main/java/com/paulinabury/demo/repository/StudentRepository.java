package com.paulinabury.demo.repository;

import com.paulinabury.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value = "select s from student s where s.indexNumber= :indexNumber")
    List<Student> selectAllStudentsWithIndexEqualTo(@Param("indexNumber") String indexNumber);

    @Query(value = "select s from student s where s.name= :name")
    List<Student> selectAllStudentsWithNameEqualsTo(@Param("name") String name);

    @Query(value = "select s from student s where s.field= :field")
    List<Student> selectAllStudentsWithFieldEqualsTo(@Param("field") String field);
}
