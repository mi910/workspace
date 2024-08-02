package com.example.demo.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TaskRequest implements Serializable {
//	@NotNull(message = "Numberを入力してください")
//	  private Integer  number;
//	
	@NotEmpty(message = "名前を入力してください")
	  @Size(max = 50, message = "名前は100桁以内で入力してください")
	  private String name;
	
	@NotEmpty(message = "詳細を入力してください")
	  @Size(max = 100, message = "詳細は100桁以内で入力してください")
	  private String detail;

	@NotEmpty(message = "ステータスを入力してください")
	 @Size(max = 100, message = "ステータスは100桁以内で入力してください")
	  private String status;
	
	@NotNull(message = "期限を入力してください")
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	  private LocalDate dueDate;
}