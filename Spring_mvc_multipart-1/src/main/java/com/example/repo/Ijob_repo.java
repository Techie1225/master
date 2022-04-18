package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.dto.job_seeker_info;
public interface Ijob_repo extends JpaRepository<job_seeker_info, Integer> {

}
