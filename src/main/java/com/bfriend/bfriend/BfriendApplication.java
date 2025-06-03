package com.bfriend.bfriend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BfriendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BfriendApplication.class, args);
	}

}
