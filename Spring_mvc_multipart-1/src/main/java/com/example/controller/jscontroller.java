package com.example.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.job_seeker_data;
import com.example.dto.job_seeker_info;
import com.example.service.job_service;

@Controller
public class jscontroller {
	
	@Autowired
	job_service jservice;
	
	@Autowired
	private Environment env;
	
	@GetMapping("/")
	public String home() {
		System.out.println(" in controller");
		return "home";
	}
	
	@GetMapping("/add")
	public String addjs(@ModelAttribute("jsdata") job_seeker_data jdata ) {
		System.out.println("in get add");
		return "Register";
		
	}
	@PostMapping("/add")
	public String addingtodb(@ModelAttribute("jsdata") job_seeker_data jdata ) throws IOException {
		System.out.println("in post add");
		String filestore=env.getRequiredProperty("upload.store");
		File file=new File(filestore);
		if(!file.exists())
			file.mkdir();
		MultipartFile resumefile=jdata.getJsresume();
		MultipartFile photofile=jdata.getJsphoto();
		InputStream resumeinstream=resumefile.getInputStream();
		InputStream photoinstream=photofile.getInputStream();
		String resumefilename=resumefile.getOriginalFilename();
		String photofilename=photofile.getOriginalFilename();
		System.out.println(resumefilename);
		OutputStream resumeout=new FileOutputStream(file.getAbsolutePath()+"\\*********"+resumefilename);
		OutputStream photoout=new FileOutputStream(file.getAbsolutePath()+"\\"+photofilename);
		IOUtils.copy(photoinstream, photoout);
		IOUtils.copy(resumeinstream, resumeout);
		
		
		job_seeker_info jobinfo=new job_seeker_info();
		jobinfo.setJsname(jdata.getJsname());
		jobinfo.setAddress(jdata.getJsaddress());
		jobinfo.setResumepath(file.getAbsolutePath()+"/"+resumefilename);
		jobinfo.setPhotopath(file.getAbsolutePath()+"/"+photofilename);
		
		jservice.savethedata(jobinfo);
		return "showreport";
		
	}

}
