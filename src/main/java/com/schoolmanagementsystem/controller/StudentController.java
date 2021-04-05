package com.schoolmanagementsystem.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.context.annotation.PropertySource;

import com.schoolmanagementsystem.controller.base.StudentBaseController;

@RestController
@PropertySource("classpath:${configfile.path}/SchoolManagementSystem.properties")
@RequestMapping(value="${endpoint.api}", headers = "Accept=application/json")
public class StudentController extends StudentBaseController {

	//OVERRIDE HERE YOUR CUSTOM CONTROLLER

}
