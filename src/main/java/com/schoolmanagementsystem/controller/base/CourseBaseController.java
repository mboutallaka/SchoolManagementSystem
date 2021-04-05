package com.schoolmanagementsystem.controller.base;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.ArrayList;
import org.springframework.security.access.annotation.Secured;
import org.springframework.beans.factory.annotation.Autowired;
import com.schoolmanagementsystem.db.schoolmanagementsystem_db.service.CourseService;
import com.schoolmanagementsystem.db.schoolmanagementsystem_db.entity.Course;

//IMPORT RELATIONS
import com.schoolmanagementsystem.db.schoolmanagementsystem_db.entity.Course;
import com.schoolmanagementsystem.db.schoolmanagementsystem_db.entity.Course;
import com.schoolmanagementsystem.db.schoolmanagementsystem_db.entity.Course;

public class CourseBaseController {
    
    @Autowired
	CourseService courseService;



//CRUD METHODS


    //CRUD - CREATE
    @Secured({ "ROLE_PRIVATE_USER" })
		@RequestMapping(value = "/course", method = RequestMethod.POST, headers = "Accept=application/json")
	public Course insert(@RequestBody Course obj) {
		Course result = courseService.insert(obj);

	    
		
		return result;
	}

	
    //CRUD - REMOVE
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/course/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void delete(@PathVariable("id") Long id) {
		courseService.delete(id);
	}
	
	
    //CRUD - GET ONE
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/course/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Course get(@PathVariable Long id) {
		Course obj = courseService.get(id);
		
		
		
		return obj;
	}
	
	
    //CRUD - GET LIST
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/course", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Course> getList() {
		return courseService.getList();
	}
	
	

    //CRUD - EDIT
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/course/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
	public Course update(@RequestBody Course obj, @PathVariable("id") Long id) {
		Course result = courseService.update(obj, id);

	    
		
		return result;
	}
	


/*
 * CUSTOM SERVICES
 * 
 *	These services will be overwritten and implemented in  Custom.js
 */


	
}
