package com.schoolmanagementsystem.controller.base;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.ArrayList;
import org.springframework.security.access.annotation.Secured;
import org.springframework.beans.factory.annotation.Autowired;
import com.schoolmanagementsystem.db.schoolmanagementsystem_db.service.StudentService;
import com.schoolmanagementsystem.db.schoolmanagementsystem_db.entity.Student;

//IMPORT RELATIONS
import com.schoolmanagementsystem.db.schoolmanagementsystem_db.entity.Course;
import com.schoolmanagementsystem.db.schoolmanagementsystem_db.entity.Student;

public class StudentBaseController {
    
    @Autowired
	StudentService studentService;



//CRUD METHODS


    //CRUD - CREATE
    @Secured({ "ROLE_PRIVATE_USER" })
		@RequestMapping(value = "/students", method = RequestMethod.POST, headers = "Accept=application/json")
	public Student insert(@RequestBody Student obj) {
		Student result = studentService.insert(obj);

	    
		//external relation _courses
		ArrayList<Long> _courses = obj.get_courses();
		if (_courses != null) {
			StudentService.Student__coursesService.updateRelation(result.get_id(), _courses);
		}
		
		
		return result;
	}

	
    //CRUD - REMOVE
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/students/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void delete(@PathVariable("id") Long id) {
		studentService.delete(id);
	}
	

    //CRUD - FIND BY _courses
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/students/findBy_courses/{key}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Student> findBy_courses(@PathVariable("key") Long id_courses) {
		List<Student> list = studentService.findBy_courses(id_courses);
		return list;
	}
	
    //CRUD - GET ONE
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/students/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Student get(@PathVariable Long id) {
		Student obj = studentService.get(id);
		
		
		//external relation _courses
		ArrayList<Long> _courses = StudentService.Student__coursesService.findBy_student(id);
		obj.set_courses(_courses);
		
		
		return obj;
	}
	
	
    //CRUD - GET LIST
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/students", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Student> getList() {
		return studentService.getList();
	}
	
	

    //CRUD - EDIT
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/students/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
	public Student update(@RequestBody Student obj, @PathVariable("id") Long id) {
		Student result = studentService.update(obj, id);

	    
		//external relation _courses
		ArrayList<Long> _courses = obj.get_courses();
		if (_courses != null) {
			StudentService.Student__coursesService.updateRelation(id, _courses);
		}
		
		
		return result;
	}
	


/*
 * CUSTOM SERVICES
 * 
 *	These services will be overwritten and implemented in  Custom.js
 */


	
}
