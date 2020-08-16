package com.example.test.job;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.example.test.repo.GradeRepo;
import com.example.test.repo.StudentRepo;
import com.example.test.util.CSVHelper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UpdateDataJob implements Job {

	@Value("${test.pathFile}")
	private String path;
	
	@Autowired
	private CSVHelper helper;
	
	@Autowired
	private GradeRepo gradeRepo;
	
	@Autowired
	private StudentRepo studentRepo;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		log.info("starting update data job execution");
		File dir = new File(path);
		for (File file : FileUtils.listFiles(dir, new String[] {"csv"}, true)) {
			try {
				if(file.getName().contains("siswa")) {
					studentRepo.saveAll(helper.toStudents(new FileInputStream(file)));
				} else if(file.getName().contains("nilai")) {
					gradeRepo.saveAll(helper.toGrades(new FileInputStream(file)));
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
