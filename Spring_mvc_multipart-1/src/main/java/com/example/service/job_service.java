package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.dto.job_seeker_info;
import com.example.repo.Ijob_repo;

@Component
public class job_service implements Ijob_service {

	@Autowired
	private Ijob_repo job_repo;
	@Override
	public int savethedata(job_seeker_info jobseekerinfo) {
		// TODO Auto-generated method stub
		return job_repo.save(jobseekerinfo).getJsid();
	}

}
