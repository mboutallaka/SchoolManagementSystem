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

import com.schoolmanagementsystem.db.schoolmanagementsystem_db.entity.Student;
import com.schoolmanagementsystem.db.schoolmanagementsystem_db.service.StudentService;

//IMPORT RELATIONS
import com.schoolmanagementsystem.db.schoolmanagementsystem_db.entity.Course;
import com.schoolmanagementsystem.db.schoolmanagementsystem_db.entity.Student;

@Service
public class StudentBaseService {

	private static NamedParameterJdbcTemplate jdbcTemplate;
	
		@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	


    //CRUD METHODS
    
    //CRUD - CREATE
    	
	public Student insert(Student obj) {
		Long id = jdbcTemplate.queryForObject("select max(_id) from `student`", new MapSqlParameterSource(), Long.class);
		obj.set_id(id == null ? 1 : id + 1);
		String sql = "INSERT INTO `student` (`_id`, `dateofbirth`,`name`,`surname`) VALUES (:id,:dateofbirth,:name,:surname)";
			SqlParameterSource parameters = new MapSqlParameterSource()
		    .addValue("id", obj.get_id())
			.addValue("dateofbirth", obj.getDateofbirth())
			.addValue("name", obj.getName())
			.addValue("surname", obj.getSurname());
		
		jdbcTemplate.update(sql, parameters);
		return obj;
	}
	
    	
    //CRUD - REMOVE
    
	public void delete(Long id) {
		String sql = "DELETE FROM `student` WHERE `_id`=:id";
		SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id);
		
		String sql__courses = "DELETE FROM `student__courses` WHERE `id_student`= :id";
		jdbcTemplate.update(sql__courses, parameters);
		jdbcTemplate.update(sql, parameters);
	}

    	
    //CRUD - FIND BY _courses
    	
	public List<Student> findBy_courses(Long id_courses) {
		
        String sql = "select * from `Student` WHERE `_id` IN (SELECT `id_student` FROM `student__courses` WHERE `id_course` = :id_courses)";
		
	    SqlParameterSource parameters = new MapSqlParameterSource()
		.addValue("id_courses", id_courses);
	    
	    return jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Student.class));
	}
    	
    //CRUD - GET ONE
    	
	public Student get(Long id) {
	    
		String sql = "select * from `student` where `_id` = :id";
		
	    SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id);
	    
	    return (Student) jdbcTemplate.queryForObject(sql, parameters, new BeanPropertyRowMapper(Student.class));
	}


    	
        	
    //CRUD - GET LIST
    	
	public List<Student> getList() {
	    
		String sql = "select * from `student`";
		
	    SqlParameterSource parameters = new MapSqlParameterSource();
	    return jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Student.class));
	    
	    
	}

    	
    //CRUD - EDIT
    	
	public Student update(Student obj, Long id) {

		String sql = "UPDATE `student` SET `dateofbirth` = :dateofbirth,`name` = :name,`surname` = :surname  WHERE `_id`=:id";
		SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id)
			.addValue("dateofbirth", obj.getDateofbirth())
			.addValue("name", obj.getName())
			.addValue("surname", obj.getSurname());
		jdbcTemplate.update(sql, parameters);
	    return obj;
	}
	
    	
    
    
    
    
    // External relation m:m _courses
    public static class Student__coursesService {

    	public static ArrayList<Long> findBy_student(Long id_student) {
    		String sql = "select `id_course` from student__courses WHERE `id_student`=:id_student";
    		SqlParameterSource parameters = new MapSqlParameterSource()
    			.addValue("id_student", id_student);
    	    
    	    List<Long> listId = jdbcTemplate.queryForList(sql, parameters, Long.class);
    		return (ArrayList<Long>) listId;
    	}
    	

    	public static void updateRelation(Long id_student, ArrayList<Long> _courses) {

    		// Delete not in array
    		String in = " and `id_course` NOT IN (:_courses)";
    		String sql = "DELETE FROM student__courses WHERE `id_student`=:id_student ";
    		
    		if (_courses != null && _courses.size() > 0)
    			sql = sql + in;
    			
    		SqlParameterSource parameters = new MapSqlParameterSource()
    			.addValue("id_student", id_student)
    			.addValue("_courses", _courses);
    		
    		jdbcTemplate.update(sql, parameters);
    		
    		// Get actual    		
    	    List<Long> actual = StudentService.Student__coursesService.findBy_student(id_student);
    	    
    		// Insert new
    		for (Long id_course : _courses) {
    			if (actual.indexOf(id_course) == -1){
    				StudentService.Student__coursesService.insert(id_student, id_course);
    			}
    		}
    		
    	}
    	

    	public static void insert(Long id_student, Long id_course) {
    		Student.Student__courses obj = new Student.Student__courses();
			Long id = jdbcTemplate.queryForObject("select max(_id) FROM student__courses", new MapSqlParameterSource(), Long.class);
			obj.set_id(id == null ? 1 : id + 1);
			
			String sql = "INSERT INTO student__courses (`_id`, `id_student`, `id_course` )	VALUES (:id, :id_student, :id_course)";

			MapSqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("id", obj.get_id())
				.addValue("id_student", id_student)
				.addValue("id_course", id_course);

			jdbcTemplate.update(sql, parameters);
    	}

    }
	    
    

    
    /*
     * CUSTOM SERVICES
     * 
     *	These services will be overwritten and implemented in studentService.java
     */
    

}
