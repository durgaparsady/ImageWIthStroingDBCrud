package com.example.demo.model;
import javax.persistence.Lob;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRequest {
	 private Long id;
	 private String name;
		private int age;
		@Lob
		private MultipartFile image;

}
