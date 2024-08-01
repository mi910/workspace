package com.example.demoSpring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demoSpring.entity.TaskEntity;
import com.example.demoSpring.repository.TaskRepository;
import com.example.demoSpring.request.TaskRequest;

@Service
@Transactional(rollbackFor = Exception.class)
public class TaskService {
	@Autowired
	  private TaskRepository taskRepository;
	
	public List<TaskEntity> searchAll() {
	      return taskRepository.findAll();
	  }
	 public TaskEntity findById(Integer id) {
		    return taskRepository.getOne(id);
		  }
	 public void create(TaskRequest taskRequest) {
		
		    TaskEntity task = new TaskEntity();
		    task.setName(taskRequest.getName());
		    task.setDetail(taskRequest.getDetail());
		    task.setStatus(taskRequest.getStatus());
		    task.setDueDate(taskRequest.getDueDate());
		    taskRepository.save(task);
		  }
}
