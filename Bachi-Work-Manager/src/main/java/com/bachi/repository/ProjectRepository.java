package com.bachi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bachi.model.Project;
import com.bachi.model.Role;

@Repository("projectepository")

public interface ProjectRepository  extends JpaRepository<Project, Long> {
	Project findByName(String name);
}


