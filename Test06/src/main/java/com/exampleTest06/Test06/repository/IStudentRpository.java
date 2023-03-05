package com.exampleTest06.Test06.repository;

import com.exampleTest06.Test06.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Repository
public interface IStudentRpository extends JpaRepository<Student,Integer> {
    List<Student>findByIdAndActive(Integer age,boolean active);
    List<Student>findByFirstNameAndActive(String name,boolean active);
    List<Student>findByLastNameAndActive(String name,boolean active);

    List<Student>findByAgeAndActive(Integer age,boolean active);

    @Query(value = "Select * From student where firstName = :name",nativeQuery = true)
    List<Student> findByName(String name);
    @Modifying
    @Transactional
    @Query(value = "update student set firstName=:firstName,lastName =:lastName, age = :age, admissionDate=:admissionDate where id=:id",
            countQuery = "Select count(*) from student",
            nativeQuery = true)
    Integer updateStudent(Integer id,String firstName,String lastName,Integer age, Date admissionDate);

    @Modifying
    @Transactional
    @Query(value = "update student set active=:active where id=:id",
            countQuery = "Select count(*) from student",
            nativeQuery = true)
    void deleteStudent(Integer id,boolean active);
}
