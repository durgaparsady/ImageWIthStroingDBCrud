package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.example.demo.model.Image;
import com.example.demo.model.UpdateRequest;
import com.example.demo.repo.ImageRepository;

 

@Service
public class ImageService {
	@Autowired
	private ImageRepository repo;

	public void addData(AddRequest addRequest, String fileName, byte[] bytes) {
		Image img=new Image();
		img.setName(addRequest.getName());
		img.setAge(addRequest.getAge());
		img.setFile(fileName);
		 
		repo.save(img);
		
	}

	public List<Image> getAllList() {
		return repo.findAll();
	 
	}
	
	public Image get(long id) {
		return repo.findById(id).get();
	}
	

	public Image update(UpdateRequest rrequest,String fileName, byte[] bytes, HttpSession httpSession) throws IOException {
		Image request = get(rrequest.getId());
		if (request != null) {
			request.setName(rrequest.getName());
	        request.setAge(rrequest.getAge());
			    if (!rrequest.getImage().isEmpty()) {
				   String img = ImageUpload.imageUploadMethod(rrequest.getImage(), httpSession);
				      request.setFile(img); 
			}
			return repo.save(request);
		}
		return null;
	}
	

	public void delete(long id) {
		repo.deleteById(id);
	}

	
}
