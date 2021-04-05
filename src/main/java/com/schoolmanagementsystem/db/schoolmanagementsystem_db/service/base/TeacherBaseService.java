package com.schoolmanagementsystem.db.schoolmanagementsystem_db.service.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import com.schoolmanagementsystem.db.schoolmanagementsystem_db.entity.Teacher;
import com.schoolmanagementsystem.db.schoolmanagementsystem_db.service.TeacherService;

//IMPORT RELATIONS
import com.schoolmanagementsystem.db.schoolmanagementsystem_db.entity.Course;
import com.schoolmanagementsystem.db.schoolmanagementsystem_db.entity.Teacher;

@Service
public class TeacherBaseService {

	private static NamedParameterJdbcTemplate jdbcTemplate;
	
		@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	


    //CRUD METHODS
    
    //CRUD - CREATE
    	
	public Teacher insert(Teacher obj) {
		Long id = jdbcTemplate.queryForObject("select max(_id) from `teacher`", new MapSqlParameterSource(), Long.class);
		obj.set_id(id == null ? 1 : id + 1);
		String sql = "INSERT INTO `teacher` (`_id`, `name`,`surname`) VALUES (:id,:name,:surname)";
			SqlParameterSource parameters = new MapSqlParameterSource()
		    .addValue("id", obj.get_id())
			.addValue("name", obj.getName())
			.addValue("surname", obj.getSurname());
		
		jdbcTemplate.update(sql, parameters);
		return obj;
	}
	
    	
    //CRUD - REMOVE
    
	public void delete(Long id) {
		String sql = "DELETE FROM `teacher` WHERE `_id`=:id";
		SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id);
		
		String sql__courses = "DELETE FROM `teacher__courses` WHERE `id_teacher`= :id";
		jdbcTemplate.update(sql__courses, parameters);
		jdbcTemplate.update(sql, parameters);
	}

    	
    //CRUD - FIND BY _courses
    	
	public List<Teacher> findBy_courses(Long id_courses) {
		
        String sql = "select * from `Teacher` WHERE `_id` IN (SELECT `id_teacher` FROM `teacher__courses` WHERE `id_course` = :id_courses)";
		
	    SqlParameterSource parameters = new MapSqlParameterSource()
		.addValue("id_courses", id_courses);
	    
	    return jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Teacher.class));
	}
    	
    //CRUD - GET ONE
    	
	public Teacher get(Long id) {
	    
		String sql = "select * from `teacher` where `_id` = :id";
		
	    SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id);
	    
	    return (Teacher) jdbcTemplate.queryForObject(sql, parameters, new BeanPropertyRowMapper(Teacher.class));
	}


    	
        	
    //CRUD - GET LIST
    	
	public List<Teacher> getList() {
	    
		String sql = "select * from `teacher`";
		
	    SqlParameterSource parameters = new MapSqlParameterSource();
	    return jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Teacher.class));
	    
	    
	}

    	
    //CRUD - EDIT
    	
	public Teacher update(Teacher obj, Long id) {

		String sql = "UPDATE `teacher` SET `name` = :name,`surname` = :surname  WHERE `_id`=:id";
		SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id)
			.addValue("name", obj.getName())
			.addValue("surname", obj.getSurname());
		jdbcTemplate.update(sql, parameters);
	    return obj;
	}
	
    	
    
    
    
    
    // External relation m:m _courses
    public static class Teacher__coursesService {

    	public static ArrayList<Long> findBy_teacher(Long id_teacher) {
    		String sql = "select `id_course` from teacher__courses WHERE `id_teacher`=:id_teacher";
    		SqlParameterSource parameters = new MapSqlParameterSource()
    			.addValue("id_teacher", id_teacher);
    	    
    	    List<Long> listId = jdbcTemplate.queryForList(sql, parameters, Long.class);
    		return (ArrayList<Long>) listId;
    	}
    	

    	public static void updateRelation(Long id_teacher, ArrayList<Long> _courses) {

    		// Delete not in array
    		String in = " and `id_course` NOT IN (:_courses)";
    		String sql = "DELETE FROM teacher__courses WHERE `id_teacher`=:id_teacher ";
    		
    		if (_courses != null && _courses.size() > 0)
    			sql = sql + in;
    			
    		SqlParameterSource parameters = new MapSqlParameterSource()
    			.addValue("id_teacher", id_teacher)
    			.addValue("_courses", _courses);
    		
    		jdbcTemplate.update(sql, parameters);
    		
    		// Get actual    		
    	    List<Long> actual = TeacherService.Teacher__coursesService.findBy_teacher(id_teacher);
    	    
    		// Insert new
    		for (Long id_course : _courses) {
    			if (actual.indexOf(id_course) == -1){
    				TeacherService.Teacher__coursesService.insert(id_teacher, id_course);
    			}
    		}
    		
    	}
    	

    	public static void insert(Long id_teacher, Long id_course) {
    		Teacher.Teacher__courses obj = new Teacher.Teacher__courses();
			Long id = jdbcTemplate.queryForObject("select max(_id) FROM teacher__courses", new MapSqlParameterSource(), Long.class);
			obj.set_id(id == null ? 1 : id + 1);
			
			String sql = "INSERT INTO teacher__courses (`_id`, `id_teacher`, `id_course` )	VALUES (:id, :id_teacher, :id_course)";

			MapSqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("id", obj.get_id())
				.addValue("id_teacher", id_teacher)
				.addValue("id_course", id_course);

			jdbcTemplate.update(sql, parameters);
    	}

    }
	    
    

    
    /*
     * CUSTOM SERVICES
     * 
     *	These services will be overwritten and implemented in teacherService.java
     */
    

}
