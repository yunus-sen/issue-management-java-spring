package com.yunuss.issuemanagement.api;

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

import com.yunuss.issuemanagement.dto.IssueDto;
import com.yunuss.issuemanagement.service.IssueService;
import com.yunuss.issuemanagement.util.ApiPaths;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RequestMapping(ApiPaths.IssueCtrl.CTRL)
@RestController
@Api(value = ApiPaths.IssueCtrl.CTRL, description = "Issue APIs")
@CrossOrigin
public class IssueController {

	private final IssueService issueService;
	
	
	public IssueController(IssueService issueService) {
		this.issueService = issueService;
	}


	@GetMapping("/{id}")
	@ApiOperation(value = "Get By Id Operation", response = IssueDto.class)
	public ResponseEntity<IssueDto> getById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(issueService.getById(id));
	}
	
	
	@PostMapping
	 @ApiOperation(value = "Get By Id Operation", response = IssueDto.class)
	public ResponseEntity<IssueDto> createProject(@Validated @RequestBody IssueDto issue){	
		return ResponseEntity.ok(issueService.save(issue));
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Get By Id Operation", response = IssueDto.class)
	public ResponseEntity<IssueDto>updateProect(@PathVariable(value="id",required = true) Long id ,@Validated @RequestBody IssueDto project){
	
		return ResponseEntity.ok(issueService.update(id,project));
	}
	
	@DeleteMapping("/{id}")
	 @ApiOperation(value = "Get By Id Operation", response = Boolean.class)
	public ResponseEntity<Boolean> deleteProject(@PathVariable(name = "id",required = true)Long id){
		return ResponseEntity.ok(issueService.deleteById(id));
	}
}
