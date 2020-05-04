package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Tasks;

@Repository("taskRepository")
public interface TaskRepository  extends JpaRepository<Tasks, Long>{
	Tasks findByName(String name);
}
