package com.example.test.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.bean.GradeFilter;
import com.example.test.bean.GradeRequest;
import com.example.test.model.Grade;
import com.example.test.model.QGrade;
import com.example.test.repo.GradeRepo;
import com.querydsl.core.types.dsl.BooleanExpression;

@Service
public class GradeService {

	@Autowired
	private GradeRepo gradeRepo;
	
	public List<Grade> findByFiltered(GradeFilter filter){
		BooleanExpression predicate = null;
		List<BooleanExpression> predicates = new ArrayList<BooleanExpression>();
		
		QGrade grade = QGrade.grade;
		
		if(filter.getRegNumber() != null)
			predicates.add(grade.student.regNumber.eq(filter.getRegNumber()));
		if(filter.getCourseName() != null)
			predicates.add(grade.course.name.eq(filter.getCourseName()));
		if(filter.getStudentName() != null)
			predicates.add(grade.student.name.eq(filter.getStudentName()));
		if(filter.getScore() != null)
			predicates.add(grade.score.eq(Integer.parseInt(filter.getScore())));
		
		for (BooleanExpression p : predicates) {
			BooleanExpression curPredicate = p;
			if(curPredicate != null)
			{
				if(predicate == null)
					predicate = curPredicate;
				else
					predicate = predicate.and(curPredicate);
			}
		}
		if(predicate == null)
			return (List<Grade>) gradeRepo.findAll();
		else
			return StreamSupport.stream(gradeRepo.findAll(predicate).spliterator(), false).collect(Collectors.toList());
	}
	
	public Grade update(GradeRequest request) {
		gradeRepo.updateGrade(request.getStudentId(), request.getCourseId(), request.getScore());
		return gradeRepo.findByStudentIdAndCourseId(request.getStudentId(), request.getCourseId());
	}
	
	public void delete(BigInteger id) {
		gradeRepo.deleteById(id);
	}
}
