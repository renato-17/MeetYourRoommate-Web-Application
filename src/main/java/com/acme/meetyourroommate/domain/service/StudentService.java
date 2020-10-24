package com.acme.meetyourroommate.domain.service;

import com.acme.meetyourroommate.domain.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface StudentService {
    Page<Student> getAllStudents(Pageable pageable);
    Student getStudentById(Long studentId);
    Student getStudentByDni(String studentDni);
    Student createStudent(Student student);
    Student updateStudent(Student studentRequest, Long studentId);
    ResponseEntity<?> deleteStudent(Long studentId);

}
