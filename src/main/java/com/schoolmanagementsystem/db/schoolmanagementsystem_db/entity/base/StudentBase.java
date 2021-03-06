package com.schoolmanagementsystem.db.schoolmanagementsystem_db.entity.base;

/**
 * 
 * 
  _____                      _              _ _ _     _   _     _        __ _ _      
 |  __ \                    | |            | (_) |   | | | |   (_)      / _(_) |     
 | |  | | ___    _ __   ___ | |_    ___  __| |_| |_  | |_| |__  _ ___  | |_ _| | ___ 
 | |  | |/ _ \  | '_ \ / _ \| __|  / _ \/ _` | | __| | __| '_ \| / __| |  _| | |/ _ \
 | |__| | (_) | | | | | (_) | |_  |  __/ (_| | | |_  | |_| | | | \__ \ | | | | |  __/
 |_____/ \___/  |_| |_|\___/ \__|  \___|\__,_|_|\__|  \__|_| |_|_|___/ |_| |_|_|\___|
 
                                                                                   
 * DO NOT EDIT THIS FILE!! 
 *
 *  FOR CUSTOMIZE StudentBase PLEASE EDIT ../Student.java
 * 
 *  -- THIS FILE WILL BE OVERWRITTEN ON THE NEXT SKAFFOLDER CODE GENERATION --
 * 
 */
 

/**
 * This is the model of Student object
 * 
 */
 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.math.BigDecimal;


import org.springframework.jdbc.core.RowMapper;
import com.schoolmanagementsystem.db.schoolmanagementsystem_db.entity.Student;

public class StudentBase implements RowMapper<Student>{
	
	private Long _id;
	
	// Attributes
    private Date dateofbirth;
    private String name;
    private String surname;
	
	
	// Relations m:m _courses
	private ArrayList<Long> _courses;
	
	
	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Student obj = new Student();
		try {
			obj.set_id(rs.getLong("id"));
			obj.setDateofbirth(rs.getDate("dateofbirth"));
			obj.setName(rs.getString("name"));
			obj.setSurname(rs.getString("surname"));
			
        	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return obj;
	}


	public Long get_id() {
		return _id;
	}

	public void set_id(Long _id) {
		this._id = _id;
	}
	
	public Date getDateofbirth() {
		return dateofbirth;
	}


	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}
	
    
    
    
    
    // Relations m:m _courses
	public ArrayList<Long> get_courses() {
		return _courses;
	}

	public void set_courses(ArrayList<Long> _courses) {
		this._courses = _courses;
	}
	
	
	public static class Student__courses implements RowMapper<Student__courses>{
		
		private Long _id;
		
		// Relations
	
		private Long id_student;
		private Long id_course;
				
		@Override
		public Student__courses mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Student__courses obj = new Student__courses();
			try {
				obj.set_id(rs.getLong("id"));
				obj.setId_student(rs.getLong("id_student"));
				obj.setId_course(rs.getLong("id_course"));
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return obj;
		}
		public Long get_id() {
			return _id;
		}
	
		public void set_id(Long _id) {
			this._id = _id;
		}
	
		public Long getId_student() {
			return id_student;
		}
	
	
		public void setId_student(Long id_student) {
			this.id_student = id_student;
		}
	
	
		public Long getId_course() {
			return id_course;
		}
	
	
		public void setId_course(Long id_course) {
			this.id_course = id_course;
		}
	}
    
}