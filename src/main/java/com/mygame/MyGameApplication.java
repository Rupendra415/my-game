package com.mygame;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mygame.dto.MyGameDTO;

@SpringBootApplication
public class MyGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyGameApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	private static MyGameDTO myGameDTO = null;
	public static MyGameDTO getInstance() {
	    if(myGameDTO == null)
	    	myGameDTO = new MyGameDTO();
	    return myGameDTO;
	}
}
