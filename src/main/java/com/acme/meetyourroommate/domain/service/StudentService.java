package com.acme.meetyourroommate.domain.service;

import com.acme.meetyourroommate.domain.model.Student;
import com.acme.meetyourroommate.domain.model.Team;
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

    Page<Student> getAllStudentsByTeamId(Long teamId, Pageable pageable);
    Student joinTeam(Team team, Long studentId, Pageable pageable);
    ResponseEntity<?> leaveTeam(Long studentId, Pageable pageable);
}
