package com.example.test.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "course")
public class Course extends BaseEntity{

	private String name;
}
