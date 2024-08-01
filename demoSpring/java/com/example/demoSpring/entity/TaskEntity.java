package com.example.demoSpring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@NotNull
@Entity
@Table(name = "tasklist", schema = "public")
public class TaskEntity {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "number")
	  private Integer number;
	
	@Column(name = "name")
	  private String name;
	
	@Column(name = "detail")
	  private String detail;
	
	@Column(name = "status")
	  private String status;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "duedate")
	  private String dueDate;
	}
