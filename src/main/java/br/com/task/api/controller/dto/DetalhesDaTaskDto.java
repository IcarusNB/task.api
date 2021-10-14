package br.com.task.api.controller.dto;

import br.com.task.api.model.Task;

public class DetalhesDaTaskDto {
	
	private Long id;
	private String descricao;
	private Boolean status;
	
	public DetalhesDaTaskDto (Task task) {
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
	
}
