package com.javafullstackguru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.javafullstackguru.entity.Student;
import com.javafullstackguru.repository.StudentRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Create or Update Student
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    // Get Student by ID
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    // Get All Students with Sorting
    public List<Student> getAllStudentsSorted(String sortBy) {
        return studentRepository.findAll(Sort.by(sortBy));
    }

    // Delete Student by ID
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    // Query by Example
    public List<Student> searchStudents(String name, Integer age) {
        if (name != null && age != null) {
            return studentRepository.findByNameAndAge(name, age);
        } else if (name != null) {
            return studentRepository.findByName(name);
        } else if (age != null) {
            return studentRepository.findByAge(age);
        } else {
            return new ArrayList<>(); // Return an empty list if both parameters are null
        }
    }


    public Page<Student> getAllStudentsPaginated(String sortBy, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return studentRepository.findAll(pageable);
    }

}

