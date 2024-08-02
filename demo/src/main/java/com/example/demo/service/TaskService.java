package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.TaskRequest;
import com.example.demo.dto.TaskUpdateRequest;
import com.example.demo.entity.TaskEntity;
import com.example.demo.repository.TaskRepository;

@Service
@Transactional(rollbackFor = Exception.class)
public class TaskService {
	@Autowired
	  private TaskRepository taskRepository;
	
	public List<TaskEntity> searchAll() {
	      return taskRepository.findAll();
	  }
	 public TaskEntity findById(Integer number) {
		    return taskRepository.getOne(number);
		  }
	 public void create(TaskRequest taskRequest) {
		
		    TaskEntity task = new TaskEntity();
//		    task.setNumber(taskRequest.getNumber());
		    task.setName(taskRequest.getName());
		    task.setDetail(taskRequest.getDetail());
		    task.setStatus(taskRequest.getStatus());
		    task.setDueDate(taskRequest.getDueDate());
		    taskRepository.save(task);
		  }
	 public void update(TaskUpdateRequest taskUpdateRequest) {
		    TaskEntity task = findById(taskUpdateRequest.getNumber());
//		    task.setNumber(taskUpdateRequest.getNumber());
		    task.setName(taskUpdateRequest.getName());
		    task.setDetail(taskUpdateRequest.getDetail());
		    task.setStatus(taskUpdateRequest.getStatus());
		    task.setDueDate(taskUpdateRequest.getDueDate());
		    taskRepository.save(task);
		  }
	 public void delete(Integer number) {
	        TaskEntity task = findById(number);
	        taskRepository.delete(task);
	    }
}