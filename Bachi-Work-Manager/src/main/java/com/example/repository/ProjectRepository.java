package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Project;
import com.example.model.Role;

@Repository("projectepository")

public interface ProjectRepository  extends JpaRepository<Project, Long> {
	Project findByName(String name);
}


