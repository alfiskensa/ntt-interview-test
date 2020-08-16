package com.example.test.bean;

import java.math.BigInteger;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
public class GradeRequest {

	private BigInteger studentId;
	
	private BigInteger courseId;
	
	private Integer score;
}
