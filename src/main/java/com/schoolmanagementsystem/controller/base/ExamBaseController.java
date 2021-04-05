package com.schoolmanagementsystem.controller.base;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.ArrayList;
import org.springframework.security.access.annotation.Secured;
import org.springframework.beans.factory.annotation.Autowired;
import com.schoolmanagementsystem.db.schoolmanagementsystem_db.service.ExamService;
import com.schoolmanagementsystem.db.schoolmanagementsystem_db.entity.Exam;

//IMPORT RELATIONS
import com.schoolmanagementsystem.db.schoolmanagementsystem_db.entity.Course;
import com.schoolmanagementsystem.db.schoolmanagementsystem_db.entity.Student;
import com.schoolmanagementsystem.db.schoolmanagementsystem_db.entity.Teacher;

public class ExamBaseController {
    
    @Autowired
	ExamService examService;



//CRUD METHODS


    //CRUD - CREATE
    @Secured({ "ROLE_PRIVATE_USER" })
		@RequestMapping(value = "/exam", method = RequestMethod.POST, headers = "Accept=application/json")
	public Exam insert(@RequestBody Exam obj) {
		Exam result = examService.insert(obj);

	    
		
		return result;
	}

	
    //CRUD - REMOVE
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/exam/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void delete(@PathVariable("id") Long id) {
		examService.delete(id);
	}
	

    //CRUD - FIND BY _course
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/exam/findBy_course/{key}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Exam> findBy_course(@PathVariable("key") Long id_course) {
		List<Exam> list = examService.findBy_course(id_course);
		return list;
	}

    //CRUD - FIND BY _student
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/exam/findBy_student/{key}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Exam> findBy_student(@PathVariable("key") Long id_student) {
		List<Exam> list = examService.findBy_student(id_student);
		return list;
	}

    //CRUD - FIND BY _teacher
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/exam/findBy_teacher/{key}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Exam> findBy_teacher(@PathVariable("key") Long id_teacher) {
		List<Exam> list = examService.findBy_teacher(id_teacher);
		return list;
	}
	
    //CRUD - GET ONE
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/exam/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Exam get(@PathVariable Long id) {
		Exam obj = examService.get(id);
		
		
		
		return obj;
	}
	
	
    //CRUD - GET LIST
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/exam", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Exam> getList() {
		return examService.getList();
	}
	
	

    //CRUD - EDIT
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/exam/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
	public Exam update(@RequestBody Exam obj, @PathVariable("id") Long id) {
		Exam result = examService.update(obj, id);

	    
		
		return result;
	}
	


/*
 * CUSTOM SERVICES
 * 
 *	These services will be overwritten and implemented in  Custom.js
 */


    /*
    Name: validate
    Description: 
    Params: 
    	String id - id of the exam
    
    
    @RequestMapping(value = "/exam/validate/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
    public Object validate() {
		return new HashMap<String, String>();
    }
    */
    		

	
}
