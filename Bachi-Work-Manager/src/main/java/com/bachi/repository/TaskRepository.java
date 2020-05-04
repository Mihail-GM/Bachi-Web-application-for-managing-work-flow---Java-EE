package com.bachi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bachi.model.Tasks;

@Repository("taskRepository")
public interface TaskRepository  extends JpaRepository<Tasks, Long>{
	Tasks findByName(String name);
}
