package br.com.task.api.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.task.api.controller.dto.DetalhesDaTaskDto;
import br.com.task.api.controller.dto.TaskDto;
import br.com.task.api.controller.form.AtualizacaoTaskForm;
import br.com.task.api.controller.form.TaskForm;
import br.com.task.api.model.Task;
import br.com.task.api.repository.TaskRepository;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	
	@Autowired
	private TaskRepository taskRepository;
	
	//MÉTODO PARA LISTAS AS TASKS
	@GetMapping
	public List<TaskDto> lista (Long id) {
		List<Task> tasks = taskRepository.findAll();
		return TaskDto.converter(tasks);
		
		//if(id == null) {
			//List<Task> tasks = taskRepository.findAll();
			//return TaskDto.converter(tasks);
		//} else {
			//List<Task> tasks = taskRepository.findById(id);
			//return TaskDto.converter(tasks);
		//}
	}
	
	
	//MÉTODO PARA POSTAR UMA TASK
	@PostMapping
	@Transactional
	public ResponseEntity<TaskDto> cadastrar (@RequestBody @Valid TaskForm form, UriComponentsBuilder uriBuilder) {
		Task task = form.converter(taskRepository);
		taskRepository.save(task);
		
		URI uri = uriBuilder.path("/tasks/{id}").buildAndExpand(task.getId()).toUri();
		return ResponseEntity.created(uri).body(new TaskDto(task));
		
	}
	
	//MÉTODO PARA DETALHAR UMA TASK
	@GetMapping("/{id}")
	public ResponseEntity<DetalhesDaTaskDto> detalhar (@PathVariable Long id) {
		Optional<Task> task = taskRepository.findById(id);
		
		if (task.isPresent()) {
			return ResponseEntity.ok(new DetalhesDaTaskDto(task.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	//MÉTODO PARA ATUALIZAR UMA TASK
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TaskDto> atualizar (@PathVariable Long id, @RequestBody @Valid AtualizacaoTaskForm form) {
		Optional<Task> optional = taskRepository.findById(id);
		
		if (optional.isPresent()) {
			Task task = form.atualizar(id, taskRepository);
			return ResponseEntity.ok(new TaskDto(task));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	//MÉTODO PARA DELETAR UMA TASK
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover (@PathVariable Long id) {
		Optional<Task> optional = taskRepository.findById(id);
		
		if (optional.isPresent()) {
			taskRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	

}
