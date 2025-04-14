package com.JobApplication.job;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//@RestController
@Controller
@ResponseBody
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService){ //constructor injection
        this.jobService=jobService;
    }

    //@GetMapping("/jobs")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Job>> findAll(){
        return ResponseEntity.ok(jobService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable long id){
        Job job=jobService.getJobById(id);
        if(job==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(job,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        if(jobService.createJob(job)){
            return new ResponseEntity<>("operation success",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("operation fails",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        Boolean delete=jobService.deleteJob(id);
        if(delete){
            return ResponseEntity.ok("job is deleted successfully");
        }else{
            return new ResponseEntity<>("operation fails",HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id,@RequestBody Job updatedJob){
        Boolean update=jobService.updateJob(id,updatedJob);
        if(update){
            return ResponseEntity.ok("Update successfully");
        }else {
            return new ResponseEntity<>("update unsuccessful",HttpStatus.NOT_FOUND);
        }
    }
}
