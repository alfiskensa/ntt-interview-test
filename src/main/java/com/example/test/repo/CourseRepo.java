package com.example.test.repo;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.test.model.Course;

public interface CourseRepo extends CrudRepository<Course, BigInteger>{

	public Optional<Course> findByName(String name);
}
