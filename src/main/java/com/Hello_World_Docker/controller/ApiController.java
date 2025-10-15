package com.Hello_World_Docker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Hello_World_Docker.entity.MyData;


@RestController
@RequestMapping("/api") 
public class ApiController {
	
	
	    @GetMapping("/hello")
	    public ResponseEntity<String> getHello() {
	        return ResponseEntity.ok( "Hello " + MyData.getName());
	    }

	    @PostMapping("/postData")
	    public ResponseEntity<String> postData(@RequestBody MyData data) {
	        return ResponseEntity.ok("Received data: " + data.getName());
	    }
	

}
