package com.example.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sms.entity.Student;
import com.example.sms.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	public void save(Student student)
	{
		studentRepository.save(student);
	}
	
	public List<Student> getAllStudents() {
		
	    return studentRepository.findAll();
	}

	
	public Student getStudentById(long id)
	{
		return studentRepository.findById(id).get();
	}
	
	public void deleteById(long id)
	{
		studentRepository.deleteById(id);
	}

}
