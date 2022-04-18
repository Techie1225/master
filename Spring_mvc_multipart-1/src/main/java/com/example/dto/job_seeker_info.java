package com.example.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="Jobseeker")
public class job_seeker_info implements Serializable {
	
	@Id
	@GeneratedValue
	private Integer jsid;
	@Column(length = 15)
	private String jsname;
	@Column(length = 15)
	private String address;
	@Column(length = 50)
	private String resumepath;
	@Column(length = 50)
	private String photopath;
	
}
