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
 *  FOR CUSTOMIZE ExamBase PLEASE EDIT ../Exam.java
 * 
 *  -- THIS FILE WILL BE OVERWRITTEN ON THE NEXT SKAFFOLDER CODE GENERATION --
 * 
 */
 

/**
 * This is the model of Exam object
 * 
 */
 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.math.BigDecimal;


import org.springframework.jdbc.core.RowMapper;
import com.schoolmanagementsystem.db.schoolmanagementsystem_db.entity.Exam;

public class ExamBase implements RowMapper<Exam>{
	
	private Long _id;
	
	// Attributes
    private String place;
    private Double score;
    private Boolean valid;
	
	// Relations _course
	private String _course;
	
	// Relations _student
	private String _student;
	
	// Relations _teacher
	private String _teacher;
	
	
	
	@Override
	public Exam mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Exam obj = new Exam();
		try {
			obj.set_id(rs.getLong("id"));
			obj.setPlace(rs.getString("place"));
			obj.setScore(rs.getDouble("score"));
			obj.setValid(rs.getBoolean("valid"));
			
        	
        	// Relations 1:m _course
			obj.set_course(rs.getString("_course"));
        	
        	// Relations 1:m _student
			obj.set_student(rs.getString("_student"));
        	
        	// Relations 1:m _teacher
			obj.set_teacher(rs.getString("_teacher"));
        	
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
	
	public String getPlace() {
		return place;
	}


	public void setPlace(String place) {
		this.place = place;
	}
	public Double getScore() {
		return score;
	}


	public void setScore(Double score) {
		this.score = score;
	}
	public Boolean getValid() {
		return valid;
	}


	public void setValid(Boolean valid) {
		this.valid = valid;
	}
	
    
    // Relations 1:m _course
	public String get_course() {
		return _course;
	}

	public void set_course(String _course) {
		this._course = _course;
	}
    
    // Relations 1:m _student
	public String get_student() {
		return _student;
	}

	public void set_student(String _student) {
		this._student = _student;
	}
    
    // Relations 1:m _teacher
	public String get_teacher() {
		return _teacher;
	}

	public void set_teacher(String _teacher) {
		this._teacher = _teacher;
	}
    
    
    
    
}