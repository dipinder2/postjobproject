package com.example.demo.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

//
//JOB POST CLASS
// for Saving Job Posts
//
@Entity
@Table(name = "job_posts")
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min=3, message="Very Short Job Title!!!")
    private String title;
    @NotBlank
    @Size(min=3, message="Very Short Company Name!!!")
    private String company;
    private String location;
    @Positive
    private Double salary;
	@Column(updatable=false)
	@DateTimeFormat(pattern="MM-dd-yyyy")
    private Date createdAt;

    public JobPost() {
    }

    public JobPost(Long id, String title, String company, String location, Double salary) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.location = location;
        this.salary = salary;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}