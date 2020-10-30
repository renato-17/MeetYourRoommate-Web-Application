package com.acme.meetyourroommate;

import com.acme.meetyourroommate.domain.model.Student;
import com.acme.meetyourroommate.domain.model.Team;
import com.acme.meetyourroommate.domain.repository.StudentRepository;
import com.acme.meetyourroommate.domain.repository.TeamRepository;
import com.acme.meetyourroommate.domain.service.StudentService;
import com.acme.meetyourroommate.service.StudentServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class StudentServiceImplIntegrationTest {
    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private TeamRepository teamRepository;

    @Autowired
    private StudentService studentService;

    @TestConfiguration
    static class StudentServiceImplTestConfiguration {
        @Bean
        public StudentService studentService() {
            return new StudentServiceImpl();
        }
    }

    @Test
    @DisplayName("When GetStudentByDni With Valid Dni Then Returns Student")
    public void whenGetStudentByValidDniThenReturnsStudent(){
        //Arrange
        String dni = "13452467";
        Student student = new Student();
        student.setId(1L);
        student.setDni(dni);

        when(studentRepository.findByDni(dni))
                .thenReturn(Optional.of(student));

        //Act
        Student foundStudent = studentService.getStudentByDni(dni);

        //Assert
        assertThat(foundStudent.getDni()).isEqualTo(dni);
    }

    @Test
    @DisplayName("When JoinATeam With Valid Team Then Returns Student With New Team ")
    public void whenJoinATeamWithTeamExistReturnsStudentWithNewTeamId(){
        //Arrange
        String teamName = "UPC";
        Team team = new Team();
        team.setName(teamName);

        Long id = 1L;
        Student student = new Student();
        student.setId(1L);

        when(studentRepository.findById(id))
                .thenReturn(Optional.of(student));

        student.setTeam(team);
        when(studentRepository.save(student))
                .thenReturn(student);

        when(teamRepository.findByName(teamName))
                .thenReturn(null);

        //Act
        Student foundStudent = studentService.joinTeam(team,student.getId());
        //Assert
        assertThat(foundStudent.getTeam()).isEqualTo(team);
    }

    @Test
    @DisplayName("When LeaveATeam With Valid Team Then Returns Student Without Team ")
    public void whenLeaveATeamWithTeamExistReturnsStudentWithNewTeamId(){
        //Arrange
        String teamName = "UPC";
        Team team = new Team();
        team.setName(teamName);

        Long id = 1L;
        Student student = new Student();
        student.setId(1L);
        student.setTeam(team);

        when(studentRepository.findById(id))
                .thenReturn(Optional.of(student));

        when(studentRepository.findByTeamId(team.getId(),Pageable.unpaged()))
                .thenReturn(null);

        student.setTeam(null);
        when(studentRepository.save(student))
                .thenReturn(student);

        //Act
        Student foundStudent = studentService.leaveTeam(student.getId(),Pageable.unpaged());
        //Assert
        assertThat(foundStudent.getTeam()).isEqualTo(null);
    }
}
