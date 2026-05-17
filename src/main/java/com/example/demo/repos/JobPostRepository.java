package com.example.demo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.JobPost;


@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Long> {

    // ===== READ / SEARCH =====
    List<JobPost> findByTitle(String title);

    List<JobPost> findByCompany(String company);

    List<JobPost> findByLocation(String location);

    List<JobPost> findByTitleContainingIgnoreCase(String keyword);

    List<JobPost> findByCompanyContainingIgnoreCase(String keyword);

    List<JobPost> findByLocationContainingIgnoreCase(String keyword);

    List<JobPost> findByLocationAndCompany(String location, String company);
}