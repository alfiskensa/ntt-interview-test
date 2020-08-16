package com.example.test.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "student")
public class Student extends BaseEntity {

	private String regNumber;
	
	private String name;
	
	private String classroom;
}
