package com.example.test.controller;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.bean.GradeFilter;
import com.example.test.bean.GradeRequest;
import com.example.test.model.Grade;
import com.example.test.service.GradeService;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private GradeService gradeService;
	
	@GetMapping("/grade")
	public List<Grade> findGrades(GradeFilter filter){
		return gradeService.findByFiltered(filter); 
	}
	
	@PutMapping("/grade")
	public Grade updateGrade(@RequestBody GradeRequest request) {
		return gradeService.update(request);
	}
	
	@DeleteMapping("/grade/{id}")
	public Map<String, Object> deleteGrade(@PathVariable BigInteger id) {
		try {
			gradeService.delete(id);
			return toResposne("ok", "delete data with id "+id+" successful");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return toResposne("error", e.getMessage());
		}
	}
	
	private Map<String, Object> toResposne(String status, String message){
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("status", status);
		response.put("message", message);
		return response;
	}
}
