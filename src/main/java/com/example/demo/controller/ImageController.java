package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
 
import com.example.demo.model.Image;
import com.example.demo.model.UpdateRequest;
import com.example.demo.repo.ImageRepository;
import com.example.demo.service.AddRequest;
import com.example.demo.service.ImageService;
import com.example.demo.service.ImageUpload;
@Controller
public class ImageController {
	@Autowired
	private ImageService service;
 
	@GetMapping("/")
	public String homePage(Model model) {
		
	String sortDir="asc";
	String sortField="id";
	      List<Image>imageList=service.getAllList();
//	      System.out.println(imageList);
	      model.addAttribute("images",imageList);
	      model.addAttribute("sortDir",sortDir);
	      model.addAttribute("sortField", sortField);
	    	model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
	      
	      	 
		return "index"; 
	}
	@GetMapping("/new")
	public String sayHello(Model model) {
//		Image image=new Image();
		AddRequest image=new AddRequest();
		UpdateRequest updateReq=new UpdateRequest();
		model.addAttribute("updateReq", updateReq);
		model.addAttribute("image", image);
		return "form";
	}
	@PostMapping("/save")
	public String AddMethod(@ModelAttribute("image") AddRequest addRequest,Model model ,HttpSession httpSession) throws IOException {
		 
		      String fileName=ImageUpload.imageUploadMethod(addRequest.getImage(), httpSession) ;
		      service.addData(addRequest,fileName,addRequest.getImage().getBytes()); 
		      
		      return "redirect:/";
		
	}
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_product");
		Image image = service.get(id);
	   System.out.println(image);
		mav.addObject("image", image); 
		return mav;
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updateProduct(@ModelAttribute("image") UpdateRequest updateReq , HttpSession httpSession  ) throws IOException{
		   String fileName=ImageUpload.imageUploadMethod(updateReq.getImage(), httpSession) ;
		service.update(updateReq,fileName,updateReq.getImage().getBytes(),httpSession);
	
	return  new ModelAndView("redirect:/");
	}

	@RequestMapping("/delete/{id}")
	public ModelAndView deleteProduct(@PathVariable(name = "id") Long id) {

		service.delete(id);
		return  new ModelAndView("redirect:/");
	}
	
}
