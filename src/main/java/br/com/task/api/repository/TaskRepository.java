package br.com.task.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.task.api.model.Task;

public interface TaskRepository extends JpaRepository <Task, Long>{
	
}
