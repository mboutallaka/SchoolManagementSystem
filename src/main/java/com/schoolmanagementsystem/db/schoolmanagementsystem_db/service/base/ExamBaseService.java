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

import com.schoolmanagementsystem.db.schoolmanagementsystem_db.entity.Exam;
import com.schoolmanagementsystem.db.schoolmanagementsystem_db.service.ExamService;

//IMPORT RELATIONS
import com.schoolmanagementsystem.db.schoolmanagementsystem_db.entity.Course;
import com.schoolmanagementsystem.db.schoolmanagementsystem_db.entity.Student;
import com.schoolmanagementsystem.db.schoolmanagementsystem_db.entity.Teacher;

@Service
public class ExamBaseService {

	private static NamedParameterJdbcTemplate jdbcTemplate;
	
		@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	


    //CRUD METHODS
    
    //CRUD - CREATE
    	
	public Exam insert(Exam obj) {
		Long id = jdbcTemplate.queryForObject("select max(_id) from `exam`", new MapSqlParameterSource(), Long.class);
		obj.set_id(id == null ? 1 : id + 1);
		String sql = "INSERT INTO `exam` (`_id`, `place`,`score`,`valid`,`_course`,`_student`,`_teacher`) VALUES (:id,:place,:score,:valid, :_course , :_student , :_teacher )";
			SqlParameterSource parameters = new MapSqlParameterSource()
		    .addValue("id", obj.get_id())
			.addValue("place", obj.getPlace())
			.addValue("score", obj.getScore())
			.addValue("valid", obj.getValid())
			.addValue("_course", obj.get_course())
			.addValue("_student", obj.get_student())
			.addValue("_teacher", obj.get_teacher());
		
		jdbcTemplate.update(sql, parameters);
		return obj;
	}
	
    	
    //CRUD - REMOVE
    
	public void delete(Long id) {
		String sql = "DELETE FROM `exam` WHERE `_id`=:id";
		SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id);
		
		jdbcTemplate.update(sql, parameters);
	}

    	
    //CRUD - FIND BY _course
    	
	public List<Exam> findBy_course(Long id_course) {
		
		String sql = "select * from `Exam` WHERE `_course` = :id_course";
		
	    SqlParameterSource parameters = new MapSqlParameterSource()
		.addValue("id_course", id_course);
	    
	    return jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Exam.class));
	}
    	
    //CRUD - FIND BY _student
    	
	public List<Exam> findBy_student(Long id_student) {
		
		String sql = "select * from `Exam` WHERE `_student` = :id_student";
		
	    SqlParameterSource parameters = new MapSqlParameterSource()
		.addValue("id_student", id_student);
	    
	    return jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Exam.class));
	}
    	
    //CRUD - FIND BY _teacher
    	
	public List<Exam> findBy_teacher(Long id_teacher) {
		
		String sql = "select * from `Exam` WHERE `_teacher` = :id_teacher";
		
	    SqlParameterSource parameters = new MapSqlParameterSource()
		.addValue("id_teacher", id_teacher);
	    
	    return jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Exam.class));
	}
    	
    //CRUD - GET ONE
    	
	public Exam get(Long id) {
	    
		String sql = "select * from `exam` where `_id` = :id";
		
	    SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id);
	    
	    return (Exam) jdbcTemplate.queryForObject(sql, parameters, new BeanPropertyRowMapper(Exam.class));
	}


    	
        	
    //CRUD - GET LIST
    	
	public List<Exam> getList() {
	    
		String sql = "select * from `exam`";
		
	    SqlParameterSource parameters = new MapSqlParameterSource();
	    return jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Exam.class));
	    
	    
	}

    	
    //CRUD - EDIT
    	
	public Exam update(Exam obj, Long id) {

		String sql = "UPDATE `exam` SET `place` = :place,`score` = :score,`valid` = :valid , `_course` = :_course , `_student` = :_student , `_teacher` = :_teacher  WHERE `_id`=:id";
		SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id)
			.addValue("place", obj.getPlace())
			.addValue("score", obj.getScore())
			.addValue("valid", obj.getValid())
			.addValue("_course", obj.get_course())
			.addValue("_student", obj.get_student())
			.addValue("_teacher", obj.get_teacher());
		jdbcTemplate.update(sql, parameters);
	    return obj;
	}
	
    	
        	
    
    
    
        
        
    

    
    /*
     * CUSTOM SERVICES
     * 
     *	These services will be overwritten and implemented in examService.java
     */
    
    
    /*
    
    YOU CAN COPY AND MODIFY THIS METHOD IN ExamService.java
    
    Name: validate
    Description: 
    Params: 
    	String id - id of the exam
    */
	public Object validate () {
		
        return null;
        
	}
	
	

}
