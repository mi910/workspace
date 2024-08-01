package com.example.demoSpring.request;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class TaskRequest implements Serializable {
	
	@NotEmpty(message = "Numberを入力してください")
	  private Integer  number;
	
	@NotEmpty(message = "名前を入力してください")
	  @Size(max = 50, message = "名前は100桁以内で入力してください")
	  private String name;
	
	@NotEmpty(message = "詳細を入力してください")
	  @Size(max = 100, message = "詳細は100桁以内で入力してください")
	  private String detail;

	@NotEmpty(message = "ステータスを入力してください")
	 @Size(max = 100, message = "ステータスは100桁以内で入力してください")
	  private String status;
	
	@Pattern(regexp = "yyyy-MM-dd", message = "期限を入力してください")
	  private String dueDate;
}
