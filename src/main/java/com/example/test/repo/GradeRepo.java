package com.example.test.repo;

import java.math.BigInteger;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.example.test.model.Grade;

public interface GradeRepo extends CrudRepository<Grade, BigInteger>, QuerydslPredicateExecutor<Grade>{

	@Transactional
	@Modifying
	@Query("UPDATE Grade g SET g.score = ?3 WHERE g.student.id = ?1 AND g.course.id = ?2")
	public void updateGrade(BigInteger studentId, BigInteger courseId, Integer score);
	
	public Grade findByStudentIdAndCourseId(BigInteger studentId, BigInteger courseId);
}
