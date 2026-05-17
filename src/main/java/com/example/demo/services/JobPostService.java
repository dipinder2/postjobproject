package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.JobPost;
import com.example.demo.repos.JobPostRepository;

@Service
public class JobPostService {

    @Autowired
    private JobPostRepository repository;

    // CREATE / UPDATE
    public JobPost saveJob(JobPost jobPost) {
        return repository.save(jobPost);
    }

    // GET ALL
    public List<JobPost> getAllJobs() {
        return repository.findAll();
    }

    // GET BY ID
    public Optional<JobPost> getJobById(Long id) {
        return repository.findById(id);
    }

    // DELETE
    public void deleteJob(Long id) {
        repository.deleteById(id);
    }

    // SEARCH METHODS
    public List<JobPost> searchByTitle(String title) {
        return repository.findByTitleContainingIgnoreCase(title);
    }

    public List<JobPost> searchByCompany(String company) {
        return repository.findByCompanyContainingIgnoreCase(company);
    }

    public List<JobPost> searchByLocation(String location) {
        return repository.findByLocationContainingIgnoreCase(location);
    }
}