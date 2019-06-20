package com.itcinfotech.EmployeeTimeSheetBackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class EmployeeController {

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "Test";
	}

}
