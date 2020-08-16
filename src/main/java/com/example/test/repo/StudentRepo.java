package com.example.test.repo;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.test.model.Student;

public interface StudentRepo extends CrudRepository<Student, BigInteger>{

	public Optional<Student> findByRegNumber(String regNumber);
}
