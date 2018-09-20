package com.example.demo.jobs;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins="http://localhost:4200")
public interface JobsRepository extends JpaRepository<Job, Integer>{
		List<Job> findByType(String type);
}


