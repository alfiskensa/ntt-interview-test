package com.example.test.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.test.model.Course;
import com.example.test.model.Grade;
import com.example.test.model.Student;
import com.example.test.repo.CourseRepo;
import com.example.test.repo.GradeRepo;
import com.example.test.repo.StudentRepo;

@Component
public class CSVHelper {
	public static final String TYPE = "text/csv";
	
	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private CourseRepo courseRepo;
	
	@Autowired
	private GradeRepo gradeRepo;
	
	public static boolean hasCSVFormat(MultipartFile file) {

	    if (!TYPE.equals(file.getContentType())) {
	      return false;
	    }

	    return true;
	  }

	public List<Student> toStudents(InputStream is) throws UnsupportedEncodingException, IOException{
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		        CSVParser csvParser = new CSVParser(fileReader,
		            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) 
		{
			List<Student> students = new ArrayList<Student>();
			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			for (CSVRecord record : csvRecords) {
				String regNumber = record.get("nomor_induk");
				if(!studentRepo.findByRegNumber(regNumber).isPresent()) {
					Student student = new Student();
					student.setRegNumber(regNumber);
					student.setName(record.get("nama"));
					student.setClassroom(record.get("kelas"));
					students.add(student);
				}
			}
			return students;
		}catch (Exception e) {
			// TODO: handle exception
			 throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}
	
	public List<Grade> toGrades(InputStream is) throws UnsupportedEncodingException, IOException{
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		        CSVParser csvParser = new CSVParser(fileReader,
		            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) 
		{
			List<Grade> grades = new ArrayList<Grade>();
			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			for (CSVRecord record : csvRecords) {
				String regNumber = record.get("nomor_induk");
				String courseName = record.get("mata_pelajaran");
				
				Optional<Course> course = courseRepo.findByName(courseName);
				if(!course.isPresent()) {
					Course newCourse = new Course();
					newCourse.setName(courseName);
					course = Optional.of(courseRepo.save(newCourse));
				}
				
				Optional<Student> student = studentRepo.findByRegNumber(regNumber);
				if(student.isPresent()) {
					Grade grade = gradeRepo.findByStudentIdAndCourseId(student.get().getId(), course.get().getId());
					if(grade == null)
						grade = new Grade();
					grade.setStudent(student.get());
					grade.setCourse(course.get());
					grade.setScore(Integer.parseInt(record.get("nilai")));
					grades.add(grade);
				}
			}
			return grades;
		}catch (Exception e) {
			// TODO: handle exception
			 throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}
}
