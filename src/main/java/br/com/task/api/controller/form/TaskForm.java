package br.com.task.api.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.task.api.model.Task;
import br.com.task.api.repository.TaskRepository;

public class TaskForm {
	
	@NotNull @NotEmpty @Length(min = 5)
	private String descricao;
	
	private Boolean status;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	public Task converter(TaskRepository taskRepository) {
		return new Task(descricao);
	}
}
