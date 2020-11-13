package com.acme.meetyourroommate.controller;

import com.acme.meetyourroommate.domain.model.Task;
import com.acme.meetyourroommate.domain.service.TaskService;
import com.acme.meetyourroommate.resource.SaveTaskResource;
import com.acme.meetyourroommate.resource.TaskResource;
import io.swagger.v3.oas.annotations.Operation;
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
public class TasksController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TaskService taskService;

    @Operation(summary = "Get All Tasks", description = "Get All Task associated to given Team", tags = {"tasks"})
    @GetMapping("/teams/{teamId}/tasks")
    public Page<TaskResource> getAllTasksByTeamId(@PathVariable Long teamId, Pageable pageable){
        Page<Task> taskPage = taskService.getAllTasksByTeamId(teamId,pageable);
        List<TaskResource> resources = taskPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable,resources.size());
    }

    @Operation(summary = "Get Task", description = "Get Task with given Id", tags = {"tasks"})
    @GetMapping("/teams/{teamId}/tasks/{taskId}")
    public TaskResource getTaskByIdAndTeamId(
            @PathVariable(name = "teamId") Long teamId,
            @PathVariable(name = "taskId") Long taskId){
        Task task = taskService.getTaskByIdAndTeamId(teamId,taskId);
        return convertToResource(task);
    }

    @Operation(summary = "Create Task", description = "Create a new Task", tags = {"tasks"})
    @PostMapping("/teams/{teamId}/tasks")
    public TaskResource createTask(
            @PathVariable(name = "teamId") Long teamId,
            @Valid @RequestBody SaveTaskResource taskResource){
        Task task = convertToEntity(taskResource);
        return convertToResource(taskService.createTask(teamId,task));
    }

    @Operation(summary = "Update Task", description = "Update an existing Task", tags = {"tasks"})
    @PutMapping("/teams/{teamId}/tasks/{taskId}")
    public TaskResource updateTask(
            @PathVariable(name = "teamId") Long teamId,
            @PathVariable(name = "taskId") Long taskId,
            @Valid @RequestBody SaveTaskResource taskResource){
        Task task = convertToEntity(taskResource);
        return convertToResource(taskService.updateTask(teamId,taskId,task));
    }

    @Operation(summary = "Create detail Task", description = "Create detail an existing Task", tags = {"tasks"})
    @PatchMapping("/teams/{teamId}/tasks/{taskId}")
    public TaskResource finishTask(
            @PathVariable(name = "teamId") Long teamId,
            @PathVariable(name = "taskId") Long taskId){
        return convertToResource(taskService.finishTask(teamId,taskId));
    }

    @Operation(summary = "Delete Task", description = "Delete an existing Task", tags = {"tasks"})
    @DeleteMapping("/teams/{teamId}/tasks/{taskId}")
    public ResponseEntity<?> deleteTask(
            @PathVariable(name = "teamId") Long teamId,
            @PathVariable(name = "taskId") Long taskId){
        return taskService.deleteTask(teamId, taskId);
    }

    private Task convertToEntity(SaveTaskResource resource){return mapper.map(resource,Task.class);}
    private TaskResource convertToResource(Task entity){return mapper.map(entity,TaskResource.class);}
}
