package com.example.demoSpring.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demoSpring.entity.TaskEntity;
import com.example.demoSpring.request.TaskRequest;
import com.example.demoSpring.service.TaskService;


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
	@RequestMapping("/taskAdd")
	  public String taskRegister(Model model) {
	    model.addAttribute("taskRequest", new TaskRequest());
	    return "taskAdd";
	  }
	 @RequestMapping("/taskCreate")
	  public String taskCreate(@Validated @ModelAttribute TaskRequest taskRequest, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	      // 入力チェックエラーの場合
	      List<String> errorList = new ArrayList<String>();
	      for (ObjectError error : result.getAllErrors()) {
	        errorList.add(error.getDefaultMessage());
	      }
	      //エラー判定後の画面遷移
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
}
