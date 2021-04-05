package com.schoolmanagementsystem.controller.base;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.ArrayList;
import org.springframework.security.access.annotation.Secured;
import org.springframework.beans.factory.annotation.Autowired;
import com.schoolmanagementsystem.db.schoolmanagementsystem_db.service.TeacherService;
import com.schoolmanagementsystem.db.schoolmanagementsystem_db.entity.Teacher;

//IMPORT RELATIONS
import com.schoolmanagementsystem.db.schoolmanagementsystem_db.entity.Course;
import com.schoolmanagementsystem.db.schoolmanagementsystem_db.entity.Teacher;

public class TeacherBaseController {
    
    @Autowired
	TeacherService teacherService;



//CRUD METHODS


    //CRUD - CREATE
    @Secured({ "ROLE_PRIVATE_USER" })
		@RequestMapping(value = "/teacher", method = RequestMethod.POST, headers = "Accept=application/json")
	public Teacher insert(@RequestBody Teacher obj) {
		Teacher result = teacherService.insert(obj);

	    
		//external relation _courses
		ArrayList<Long> _courses = obj.get_courses();
		if (_courses != null) {
			TeacherService.Teacher__coursesService.updateRelation(result.get_id(), _courses);
		}
		
		
		return result;
	}

	
    //CRUD - REMOVE
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/teacher/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void delete(@PathVariable("id") Long id) {
		teacherService.delete(id);
	}
	

    //CRUD - FIND BY _courses
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/teacher/findBy_courses/{key}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Teacher> findBy_courses(@PathVariable("key") Long id_courses) {
		List<Teacher> list = teacherService.findBy_courses(id_courses);
		return list;
	}
	
    //CRUD - GET ONE
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/teacher/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Teacher get(@PathVariable Long id) {
		Teacher obj = teacherService.get(id);
		
		
		//external relation _courses
		ArrayList<Long> _courses = TeacherService.Teacher__coursesService.findBy_teacher(id);
		obj.set_courses(_courses);
		
		
		return obj;
	}
	
	
    //CRUD - GET LIST
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/teacher", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Teacher> getList() {
		return teacherService.getList();
	}
	
	

    //CRUD - EDIT
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/teacher/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
	public Teacher update(@RequestBody Teacher obj, @PathVariable("id") Long id) {
		Teacher result = teacherService.update(obj, id);

	    
		//external relation _courses
		ArrayList<Long> _courses = obj.get_courses();
		if (_courses != null) {
			TeacherService.Teacher__coursesService.updateRelation(id, _courses);
		}
		
		
		return result;
	}
	


/*
 * CUSTOM SERVICES
 * 
 *	These services will be overwritten and implemented in  Custom.js
 */


	
}
