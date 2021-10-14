package br.com.task.api.controller.dto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.task.api.model.Task;

public class TaskDto {
	
	private Long id;
	private String descricao;
	private Boolean status;
	
	public TaskDto (Task task) {
		this.id = task.getId();
		this.descricao = task.getDescricao();
		this.status = task.getStatus();
	}
	
	public Long getId() {
		return id;
	}
	public String getDescricao() {
		return descricao;
	}
	public Boolean getStatus() {
		return status;
	}
	
	public static List<TaskDto> converter(List<Task> tasks) {
		return tasks.stream().map(TaskDto::new).collect(Collectors.toList());
	}
}
