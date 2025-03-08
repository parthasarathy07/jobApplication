package com.JobApplication.job;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//or extend curdRepository
@Repository
public interface JobRepository extends JpaRepository<Job,Long>  {
}
