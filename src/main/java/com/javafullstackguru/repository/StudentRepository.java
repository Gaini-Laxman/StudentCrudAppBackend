package com.javafullstackguru.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javafullstackguru.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByNameAndAge(String name, Integer age);

    List<Student> findByName(String name);

    List<Student> findByAge(Integer age);

    Page<Student> getStudentById(Long id, Pageable pageable);

    void deleteStudentById(Long id);
}
