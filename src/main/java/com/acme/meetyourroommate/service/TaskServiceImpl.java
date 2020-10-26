package com.acme.meetyourroommate.service;

import com.acme.meetyourroommate.domain.model.Task;
import com.acme.meetyourroommate.domain.repository.TaskRepository;
import com.acme.meetyourroommate.domain.repository.TeamRepository;
import com.acme.meetyourroommate.domain.service.TaskService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TeamRepository teamRepository;

    @Override
    public Page<Task> getAllTasksByTeamId(Long teamId, Pageable pageable) {
       return taskRepository.findByTeamId(teamId,pageable);
    }

    @Override
    public Task getTaskByIdAndTeamId(Long teamId, Long taskId) {
        return taskRepository.findByIdAndTeamId(taskId,teamId)
                .orElseThrow(() -> new ResourceNotFoundException(
                "Task not found with Id " + taskId +
                        " and TeamId " + teamId));
    }

    @Override
    public Task createTask(Long teamId, Task task) {
        return teamRepository.findById(teamId).map(team ->{
            task.setActive(true);
            task.setTeam(team);
            return taskRepository.save(task);
        }).orElseThrow(()-> new ResourceNotFoundException("Team","Id",teamId));
    }

    @Override
    public Task updateTask(Long teamId, Long taskId, Task taskDetail) {
        if(!teamRepository.existsById(teamId))
            throw new ResourceNotFoundException("Team", "Id", teamId);

        return taskRepository.findById(taskId).map(task -> {
            if(task.getActive()) {
                task.setDescription(taskDetail.getDescription());
                return taskRepository.save(task);
            }
            throw new ResourceNotFoundException("This task is not active");
        }).orElseThrow(() -> new ResourceNotFoundException("Task", "Id", taskId));
    }

    @Override
    public Task finishTask(Long teamId, Long taskId) {
        if (!teamRepository.existsById(teamId))
        throw new ResourceNotFoundException("Team", "Id", teamId);

        return taskRepository.findById(taskId).map(task -> {
            task.setActive(false);
            return taskRepository.save(task);
        }).orElseThrow(() -> new ResourceNotFoundException("Task", "Id", taskId));
    }

    @Override
    public ResponseEntity<?> deleteTask(Long teamId, Long taskId) {
        if(!teamRepository.existsById(teamId))
            throw new ResourceNotFoundException("Team", "Id", teamId);

        return taskRepository.findById(taskId).map(task -> {
            taskRepository.delete(task);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Task", "Id", taskId));
    }
}
