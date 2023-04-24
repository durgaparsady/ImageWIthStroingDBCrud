package com.example.demo.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

public class ImageUpload {
   public static String imageUploadMethod(MultipartFile image,HttpSession httpSession) throws IOException {
	   String fileName=image.getOriginalFilename();
	   byte[] data=image.getBytes();
	   
	   String path=httpSession.getServletContext().getRealPath("/")+"WEB-INF"+File.separator+"image"+File.separator+fileName;
	   System.out.println(path);
	   FileOutputStream fos=new FileOutputStream(path);
	   fos.write(data);
	   fos.close();
	   return fileName;
	   
   }
}
