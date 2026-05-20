package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.JobPost;
import com.example.demo.services.JobPostService;

@RestController
@RequestMapping("api/v1/jobs")
public class JobPostController {

    @Autowired
    private JobPostService service;

    // CREATE
    @PostMapping
    public JobPost createJob(@RequestBody JobPost jobPost) {
        return service.saveJob(jobPost);
    }

    // READ ALL
    @GetMapping
    public List<JobPost> getAllJobs() {
        return service.getAllJobs();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Optional<JobPost> getJobById(@PathVariable Long id) {
        return service.getJobById(id);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteJob(@PathVariable Long id) {
        service.deleteJob(id);
        return "Job deleted successfully";
    }

    // SEARCH
    @GetMapping("/search/title")
    public List<JobPost> searchTitle(@RequestParam String title) {
        return service.searchByTitle(title);
    }

    @GetMapping("/search/company")
    public List<JobPost> searchCompany(@RequestParam String company) {
        return service.searchByCompany(company);
    }

    @GetMapping("/search/location")
    public List<JobPost> searchLocation(@RequestParam String location) {
        return service.searchByLocation(location);
    }
}