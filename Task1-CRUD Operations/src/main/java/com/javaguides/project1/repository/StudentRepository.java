package com.javaguides.project1.repository;

import com.javaguides.project1.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
