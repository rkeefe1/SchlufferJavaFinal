package com.example.demo.jobs;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/jobs")
@CrossOrigin(origins="http://localhost:4200")

public class JobsController {
	

	@Autowired
	JobsRepository jobsRepository;
	
	@GetMapping()
	public ResponseEntity <List<Job>> getJob(){
		return ResponseEntity.ok(jobsRepository.findAll());
			
}
	@GetMapping("/{jobType}")
	public ResponseEntity <List<Job>> searchJob(@PathVariable String jobType) {
		return ResponseEntity.ok(jobsRepository.findByType(jobType));
	}

	@PostMapping()
	public ResponseEntity<Void> addJob(@RequestBody Job job) {
		jobsRepository.save(job);
		return ResponseEntity.ok().build();	
		
	}
	@PutMapping ("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Integer id, @RequestBody Job updateJob){

        Job job = jobsRepository.findById(id).orElse(null);
        
        job.setPrice(updateJob.getPrice());
        job.setType(updateJob.getType());
        job.setDescription(updateJob.getDescription());
        

        jobsRepository.save(job);
        return ResponseEntity.noContent().build();
        
    }
	
	
	
	
	@DeleteMapping("/{id}")
	public void deleteJob(@PathVariable int id) {
		jobsRepository.deleteById(id);
	}

}
