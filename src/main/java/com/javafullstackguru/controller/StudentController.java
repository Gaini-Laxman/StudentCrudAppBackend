package com.javafullstackguru.controller;

import com.javafullstackguru.entity.Student;
import com.javafullstackguru.repository.StudentRepository;
import com.javafullstackguru.service.StudentService;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService service;

    // Save a new student
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        // Replace with your logic to save the student
        System.out.println("Student received: " + student);
        Student savedStudent = service.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    // Get a student by ID with proper 404 handling
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Optional<Student> student = service.getStudentById(id);
        return student.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    // Get all students with sorting and pagination
    @GetMapping
    public Page<Student> getAllStudentsSorted(
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return service.getAllStudentsPaginated(sortBy, page, size);
    }

    // Delete a student by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable Long id) {
    	service.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

    // Search students by name and age
    @GetMapping("/search")
    public ResponseEntity<List<Student>> searchStudents(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "age", required = false) Integer age) {
        try {
            List<Student> students = service.searchStudents(name, age);
            return ResponseEntity.ok(students);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
