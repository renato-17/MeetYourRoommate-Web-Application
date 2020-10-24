package com.acme.meetyourroommate.controller;

import com.acme.meetyourroommate.domain.model.Student;
import com.acme.meetyourroommate.domain.model.Team;
import com.acme.meetyourroommate.domain.service.StudentService;
import com.acme.meetyourroommate.resource.SaveStudentResource;
import com.acme.meetyourroommate.resource.SaveTeamResource;
import com.acme.meetyourroommate.resource.StudentResource;
import org.hibernate.loader.plan.build.internal.LoadGraphLoadPlanBuildingStrategy;
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

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public Page<StudentResource> getAllStudents(Pageable pageable){
        Page<Student> studentPage = studentService.getAllStudents(pageable);

        List<StudentResource> resources = studentPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable, resources.size());
    }

    @GetMapping("/teams/{teamId}/students")
    public Page<StudentResource> getAllStudentsByTeamId(@PathVariable(name = "teamId") Long teamId, Pageable pageable){
        Page<Student> studentPage = studentService.getAllStudentsByTeamId(teamId,pageable);

        List<StudentResource> resources = studentPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable, resources.size());
    }
    @GetMapping("/students/{studentDni}")
    public StudentResource getStudentByDni(@PathVariable String studentDni){
        return convertToResource(studentService.getStudentByDni(studentDni));
    }

    @PostMapping("/students")
    public StudentResource createStudent(@Valid @RequestBody SaveStudentResource resource){
        Student student = convertToEntity(resource);
        return convertToResource(studentService.createStudent(student));
    }

    @PostMapping("/teams/{studentId}")
    public StudentResource joinTeam(@Valid @RequestBody SaveTeamResource resource, @PathVariable Long studentId, Pageable pageable) {
        Team team = mapper.map(resource,Team.class);
        return convertToResource(studentService.joinTeam(team,studentId,pageable));
    }

    @PutMapping("/students/{studentId}")
    public StudentResource updateStudent(
            @PathVariable Long studentId,
            @RequestBody @Valid SaveStudentResource resource){
        Student student = convertToEntity(resource);
        return convertToResource(studentService.updateStudent(student,studentId));
    }

    @PutMapping("/teams/students/{studentId}")
    public ResponseEntity<?> leaveTeam(@PathVariable(name = "studentId") Long studentId,
                                      Pageable pageable) {
        return studentService.leaveTeam(studentId,pageable);
    }

    @DeleteMapping("/students/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long studentId){
        return studentService.deleteStudent(studentId);
    }



    private  Student convertToEntity(SaveStudentResource resource){return mapper.map(resource,Student.class);}
    private  StudentResource convertToResource(Student entity){return mapper.map(entity,StudentResource.class);}
}
