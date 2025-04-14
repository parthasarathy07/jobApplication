package com.JobApplication.job.implementation;

import com.JobApplication.job.Job;
import com.JobApplication.job.JobRepository;
import com.JobApplication.job.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    JobRepository jobRepository;
    public JobServiceImpl(JobRepository jobRepository){
        this.jobRepository=jobRepository;
    }
    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public Boolean createJob(Job job) {
        try {
            jobRepository.save(job);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean deleteJob(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean updateJob(Long id, Job updatedJob) {
        Optional<Job>optionalJob=jobRepository.findById(id);
            if(optionalJob.isPresent()) {
                Job job = optionalJob.get();
                job.setTittle(updatedJob.getTittle());
                job.setDescription(updatedJob.getDescription());
                job.setLocation(updatedJob.getLocation());
                jobRepository.save(job);
                return true;
            }
        return false;
    }
}
