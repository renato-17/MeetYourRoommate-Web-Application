package com.acme.meetyourroommate.service;

import com.acme.meetyourroommate.domain.model.Student;
import com.acme.meetyourroommate.domain.model.Team;
import com.acme.meetyourroommate.domain.repository.StudentRepository;
import com.acme.meetyourroommate.domain.repository.TeamRepository;
import com.acme.meetyourroommate.domain.service.StudentService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Page<Student> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(()-> new ResourceNotFoundException("Student","Id",studentId));
    }

    @Override
    public Student getStudentByDni(String studentDni) {
        return studentRepository.findByDni(studentDni)
                .orElseThrow(()-> new ResourceNotFoundException("Student","Dni",studentDni));
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student studentRequest, Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> new ResourceNotFoundException("Student","Id",studentId));

        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        student.setAddress(studentRequest.getAddress());
        student.setDescription(studentRequest.getDescription());
        student.setHobbies(studentRequest.getHobbies());
        student.setbSmoker(studentRequest.getbSmoker());

        return studentRepository.save(student);
    }

    @Override
    public ResponseEntity<?> deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> new ResourceNotFoundException("Student","Id",studentId));
        studentRepository.delete(student);
        return ResponseEntity.ok().build();
    }

    @Override
    public Page<Student> getAllStudentsByTeamId(Long teamId, Pageable pageable) {
        return studentRepository.findByTeamId(teamId,pageable);
    }

    @Override
    public Student joinTeam(Team team, Long studentId, Pageable pageable) {
        Student student = getStudentById(studentId);
        if(student.getTeam() != null)
            return student;

        Team existingTeam = teamRepository.findByName(team.getName()).orElse(null);
        if(existingTeam==null){
            student.setTeam(team);
            return studentRepository.save(student);
        }
        student.setTeam(existingTeam);
        return studentRepository.save(student);
    }


    @Override
    public ResponseEntity<?> leaveTeam(Long studentId, Pageable pageable) {
        Student student = getStudentById(studentId);
        Team team = student.getTeam();
        student.setTeam(null);

        studentRepository.save(student);
        List<Student> students = getAllStudentsByTeamId(team.getId(),pageable).getContent();
        if(students.size() == 0)
            teamRepository.delete(team);

        return ResponseEntity.ok().build();
    }

}
