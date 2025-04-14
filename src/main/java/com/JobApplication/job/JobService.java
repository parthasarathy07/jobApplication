package com.JobApplication.job;
import java.util.List;

public interface JobService {
    List<Job> findAll();
    Boolean createJob(Job job);
    Job getJobById(Long id);
    Boolean deleteJob(Long id);
    Boolean updateJob(Long id, Job updatedJob);
}
