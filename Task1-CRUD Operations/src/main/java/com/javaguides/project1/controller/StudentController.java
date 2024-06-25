package com.javaguides.project1.controller;
import com.javaguides.project1.Student;
import com.javaguides.project1.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository repo;
    //localhost:8080/students
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return repo.findAll();
    }
    //localhost:8080/students/1
    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable int id){
      Student student=  repo.findById(id).get();
      return student;
    }
    //locahost:8080/students/add
    @PostMapping("/students/add")
    @ResponseStatus(code= HttpStatus.CREATED)
    public  void createStudent(@RequestBody Student student){
        repo.save(student);
    }
    //localhost:8080/students/update/1
    @PutMapping("students/update/{id}")
    public  Student updateStudents(@PathVariable int id){
      Student student=  repo.findById(id).get();
      student.setName("Hrithick");
      student.setPercentage(92);
      student.setBranch("EEE");
      repo.save(student);
      return student;
    }
    //localhost:8080/students/delete/1
    @DeleteMapping("students/delete/{id}")
    public  void removeStudent(@PathVariable int id){
       Student student = repo.findById(id).get();
       repo.delete(student);
    }
}
