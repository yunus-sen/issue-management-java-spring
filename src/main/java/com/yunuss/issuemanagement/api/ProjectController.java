package com.yunuss.issuemanagement.api;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yunuss.issuemanagement.advice.IMExceptionHandler;
import com.yunuss.issuemanagement.dto.ProjectDto;
import com.yunuss.issuemanagement.service.ProjectService;
import com.yunuss.issuemanagement.util.ApiPaths;
import com.yunuss.issuemanagement.util.TPage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RequestMapping(ApiPaths.ProjectCtrl.CTRL)
@RestController
@Api(value = ApiPaths.ProjectCtrl.CTRL, description = "Project APIs")
@Slf4j
@CrossOrigin
public class ProjectController {

	private final ProjectService projectService;
	
	
	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	 @GetMapping("/pagination")
	 @ApiOperation(value = "Get By Pagination Operation", response = ProjectDto.class)
	 public ResponseEntity<TPage<ProjectDto>> getAllByPagination(Pageable pageable) {
	     TPage<ProjectDto> data = projectService.getAllPageable(pageable);
	     return ResponseEntity.ok(data);
	 }

	@GetMapping("/{id}")
    @ApiOperation(value = "Get By Id Operation", response = ProjectDto.class)
	public ResponseEntity<ProjectDto> getById(@PathVariable("id") Long id) {
		log.info("projectCOntroller->getById");
		return ResponseEntity.ok(projectService.getById(id));
	}
	
	
	@PostMapping
	@ApiOperation(value = "create Operation", response = ProjectDto.class)
	public ResponseEntity<ProjectDto> createProject(@Validated @RequestBody ProjectDto project){	
		return ResponseEntity.ok(projectService.save(project));
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "update Operation", response = ProjectDto.class)
	public ResponseEntity<ProjectDto>updateProect(@PathVariable(value="id",required = true) Long id ,@Validated @RequestBody ProjectDto project){
	
		return ResponseEntity.ok(projectService.update(id,project));
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "delete Operation", response = Boolean.class)
	public ResponseEntity<Boolean> deleteProject(@PathVariable(name = "id",required = true)Long id){
		return ResponseEntity.ok(projectService.delete(id));
	}
}
