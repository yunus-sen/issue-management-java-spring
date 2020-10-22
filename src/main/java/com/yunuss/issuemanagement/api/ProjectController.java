package com.yunuss.issuemanagement.api;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yunuss.issuemanagement.dto.ProjectDto;
import com.yunuss.issuemanagement.service.ProjectService;

@RequestMapping("/project")
@RestController
public class ProjectController {

	private final ProjectService projectService;
	
	
	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}


	@GetMapping("/{id}")
	public ResponseEntity<ProjectDto> getById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(projectService.getById(id));
	}
	
	
	@PostMapping
	public ResponseEntity<ProjectDto> createProject(@Validated @RequestBody ProjectDto project){	
		return ResponseEntity.ok(projectService.save(project));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProjectDto>updateProect(@PathVariable(value="id",required = true) Long id ,@Validated @RequestBody ProjectDto project){
	
		return ResponseEntity.ok(projectService.update(id,project));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteProject(@PathVariable(name = "id",required = true)Long id){
		return ResponseEntity.ok(projectService.delete(id));
	}
}
