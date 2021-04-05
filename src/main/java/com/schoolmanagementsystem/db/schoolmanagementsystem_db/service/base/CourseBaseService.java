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

import com.schoolmanagementsystem.db.schoolmanagementsystem_db.entity.Course;
import com.schoolmanagementsystem.db.schoolmanagementsystem_db.service.CourseService;

//IMPORT RELATIONS
import com.schoolmanagementsystem.db.schoolmanagementsystem_db.entity.Course;
import com.schoolmanagementsystem.db.schoolmanagementsystem_db.entity.Course;
import com.schoolmanagementsystem.db.schoolmanagementsystem_db.entity.Course;

@Service
public class CourseBaseService {

	private static NamedParameterJdbcTemplate jdbcTemplate;
	
		@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	


    //CRUD METHODS
    
    //CRUD - CREATE
    	
	public Course insert(Course obj) {
		Long id = jdbcTemplate.queryForObject("select max(_id) from `course`", new MapSqlParameterSource(), Long.class);
		obj.set_id(id == null ? 1 : id + 1);
		String sql = "INSERT INTO `course` (`_id`, `name`) VALUES (:id,:name)";
			SqlParameterSource parameters = new MapSqlParameterSource()
		    .addValue("id", obj.get_id())
			.addValue("name", obj.getName());
		
		jdbcTemplate.update(sql, parameters);
		return obj;
	}
	
    	
    //CRUD - REMOVE
    
	public void delete(Long id) {
		String sql = "DELETE FROM `course` WHERE `_id`=:id";
		SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id);
		
		String sql__courses = "DELETE FROM `student__courses` WHERE `id_course`= :id";
		jdbcTemplate.update(sql__courses, parameters);
		String sql__courses = "DELETE FROM `teacher__courses` WHERE `id_course`= :id";
		jdbcTemplate.update(sql__courses, parameters);
		jdbcTemplate.update(sql, parameters);
	}

    	
    //CRUD - GET ONE
    	
	public Course get(Long id) {
	    
		String sql = "select * from `course` where `_id` = :id";
		
	    SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id);
	    
	    return (Course) jdbcTemplate.queryForObject(sql, parameters, new BeanPropertyRowMapper(Course.class));
	}


    	
        	
    //CRUD - GET LIST
    	
	public List<Course> getList() {
	    
		String sql = "select * from `course`";
		
	    SqlParameterSource parameters = new MapSqlParameterSource();
	    return jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Course.class));
	    
	    
	}

    	
    //CRUD - EDIT
    	
	public Course update(Course obj, Long id) {

		String sql = "UPDATE `course` SET `name` = :name  WHERE `_id`=:id";
		SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id)
			.addValue("name", obj.getName());
		jdbcTemplate.update(sql, parameters);
	    return obj;
	}
	
    	
    
    
    
        
        
    

    
    /*
     * CUSTOM SERVICES
     * 
     *	These services will be overwritten and implemented in courseService.java
     */
    

}
