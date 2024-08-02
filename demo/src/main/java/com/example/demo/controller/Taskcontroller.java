package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.TaskRequest;
import com.example.demo.dto.TaskUpdateRequest;
import com.example.demo.entity.TaskEntity;
import com.example.demo.service.TaskService;


@Controller
public class Taskcontroller {
	
	@Autowired
	TaskService taskService;
	
	@GetMapping("/taskList")
	 public String tasktList(Model model) {
		 List<TaskEntity> taskList = taskService.searchAll();
		 model.addAttribute("taskList", taskList);
		 return "taskList";
	 }
	@GetMapping("/taskAdd")
	  public String taskRegister(Model model) {
	    model.addAttribute("taskRequest", new TaskRequest());
	    return "taskAdd";
	  }
	@PostMapping("/taskAdd")
	  public String taskCreate(@Validated @ModelAttribute TaskRequest taskRequest, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	      // 入力チェックエラーの場合
	      List<String> errorList = new ArrayList<String>();
	      for (ObjectError error : result.getAllErrors()) {
	        errorList.add(error.getDefaultMessage());
	      }
	      //エラー判定後の画面遷移
	      model.addAttribute("TaskRequest", taskRequest);
	      model.addAttribute("validationError", errorList);
	      return "taskAdd";
	    }
	    // ユーザー情報の登録
	    taskService.create(taskRequest);
	    return "redirect:/taskList";
	  }
	 @GetMapping("/taskView/{number}")
	  public String userDetail(@PathVariable Integer number, Model model) {
	    TaskEntity task = taskService.findById(number);
	    model.addAttribute("taskData", task);
	    return "taskView";
	  }
	 @GetMapping("/taskEdit/{number}/edit")
	  public String userEdit(@PathVariable Integer number, Model model) {
		 TaskEntity task = taskService.findById(number);
	    // 編集画面用のDTOに格納
		 TaskUpdateRequest taskUpdateRequest = new TaskUpdateRequest();
		 taskUpdateRequest.setNumber(task.getNumber());
		 taskUpdateRequest.setName(task.getName());
		 taskUpdateRequest.setDetail(task.getDetail());
		 taskUpdateRequest.setStatus(task.getStatus());
		 taskUpdateRequest.setDueDate(task.getDueDate());
	    model.addAttribute("taskUpdateRequest", taskUpdateRequest);
	    return "taskEdit";
	  }
	 @PostMapping("/taskUpdate")
	  public String taskUpdate(@Validated @ModelAttribute TaskUpdateRequest taskUpdateRequest, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	      List<String> errorList = new ArrayList<String>();
	      for (ObjectError error : result.getAllErrors()) {
	        errorList.add(error.getDefaultMessage());
	      }
	      model.addAttribute("validationError", errorList);
	      return "taskEdit";
	    }
	    taskService.update(taskUpdateRequest);
	    return String.format("redirect:/taskView/%d", taskUpdateRequest.getNumber());
	  }
	 @GetMapping("/taskList/{number}/delete")
	    public String taskDelete(@PathVariable Integer number, Model model) {
	        // ユーザー情報の削除
	        taskService.delete(number);
	        return "redirect:/taskList";
	    }
}
