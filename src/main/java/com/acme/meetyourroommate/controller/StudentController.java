package com.acme.meetyourroommate.controller;

import com.acme.meetyourroommate.domain.model.Student;
import com.acme.meetyourroommate.domain.service.StudentService;
import com.acme.meetyourroommate.resource.SaveStudentResource;
import com.acme.meetyourroommate.resource.StudentResource;
import com.acme.meetyourroommate.domain.model.Team;
import com.acme.meetyourroommate.resource.SaveTeamResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "students",description = "Students API")
@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private StudentService studentService;

    @Operation(summary = "Get Students", description = "Get All Students", tags = {"students"})
    @GetMapping("/students")
    public Page<StudentResource> getAllStudents(Pageable pageable){
        Page<Student> studentPage = studentService.getAllStudents(pageable);

        List<StudentResource> resources = studentPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable, resources.size());
    }

    @Operation(summary = "Get Students by Team", description = "Get All Students by Team Id", tags = {"students"})
    @GetMapping("/teams/{teamId}/students")
    public Page<StudentResource> getAllStudentsByTeamId(@PathVariable(name = "teamId") Long teamId, Pageable pageable){
        Page<Student> studentPage = studentService.getAllStudentsByTeamId(teamId,pageable);

        List<StudentResource> resources = studentPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable, resources.size());
    }

    @Operation(summary = "Get Students by Id", description = "Get Students by Id", tags = {"students"})
    @GetMapping("/students/{studentId}")
    public StudentResource getStudentByDni(@PathVariable Long studentId){
        return convertToResource(studentService.getStudentById(studentId));
    }

    @Operation(summary = "Register a Student", description = "Register a new Student", tags = {"students"})
    @PostMapping("/students")
    public StudentResource createStudent(@Valid @RequestBody SaveStudentResource resource){
        Student student = convertToEntity(resource);
        return convertToResource(studentService.createStudent(student));
    }

    @Operation(summary = "Join a Team ", description = "Make a Student join a Team", tags = {"students"})
    @PostMapping("/teams/{studentId}")
    public StudentResource joinTeam(@Valid @RequestBody SaveTeamResource resource, @PathVariable Long studentId) {
        Team team = mapper.map(resource,Team.class);
        return convertToResource(studentService.joinTeam(team,studentId));
    }

    @Operation(summary = "Update a Student", description = "Update a Student", tags = {"students"})
    @PutMapping("/students/{studentId}")
    public StudentResource updateStudent(
            @PathVariable Long studentId,
            @RequestBody @Valid SaveStudentResource resource){
        Student student = convertToEntity(resource);
        return convertToResource(studentService.updateStudent(student,studentId));
    }

    @Operation(summary = "Leave a Team", description = "Make a Student leave the team", tags = {"students"})
    @PutMapping("/teams/students/{studentId}")
    public StudentResource leaveTeam(@PathVariable(name = "studentId") Long studentId,
                                      Pageable pageable) {
        return convertToResource(studentService.leaveTeam(studentId,pageable));
    }

    @Operation(summary = "Delete a Student", description = "Delete a Student", tags = {"students"})
    @DeleteMapping("/students/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long studentId){
        return studentService.deleteStudent(studentId);
    }

    private  Student convertToEntity(SaveStudentResource resource){return mapper.map(resource,Student.class);}
    private  StudentResource convertToResource(Student entity){return mapper.map(entity,StudentResource.class);}
}
