package com.yunuss.issuemanagement.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.yunuss.issuemanagement.dto.ProjectDto;
import com.yunuss.issuemanagement.entities.Project;
import com.yunuss.issuemanagement.repository.ProjectRepository;
import com.yunuss.issuemanagement.service.ProjectService;

import java.util.List;


@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;
    
    public ProjectServiceImpl(ProjectRepository projectRepository,ModelMapper modelMapper) {
        this.projectRepository = projectRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public ProjectDto save(ProjectDto project) {     

    	Project projectCheck=projectRepository.getByProjectCode(project.getProjectCode());
    	if(projectCheck!=null) 
    		throw new IllegalArgumentException("Project already exist");
    	
        Project projectDb=modelMapper.map(project, Project.class);
        projectDb = projectRepository.save(projectDb);
        project=modelMapper.map(projectDb,ProjectDto.class);
        project.setId(projectDb.getId());
        return project; 
    }

    @Override
	public ProjectDto update(Long id, ProjectDto project) {

    	Project projectDb=projectRepository.getById(id);
    	if(projectDb==null)
    		throw new IllegalArgumentException("Project do not exist. ID:"+id);
    	
    	Project projectCheck=projectRepository.getByProjectCodeAndIdNot(project.getProjectCode(),id);
    	if(projectCheck!=null) 
    		throw new IllegalArgumentException("Project already exist");
    	
    	projectDb.setProjectName(project.getProjectName());
    	projectDb.setProjectCode(project.getProjectCode());
    	projectDb=projectRepository.save(projectDb);
		return modelMapper.map(projectDb, ProjectDto.class);
	}
    
    @Override
    public ProjectDto getById(Long id) {
    	Project project=projectRepository.getOne(id);
    	//ProjectDto dto=modelMapper.map(project, ProjectDto.class);
        return modelMapper.map(project, ProjectDto.class);
    }

    @Override
    public Project getByProjectCode(String projectCode) {
        return null;
    }

    @Override
    public List<Project> getByProjectCodeContains(String projectCode) {
        return null;
    }

    @Override
    public Page<Project> getAllPageable(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    @Override
    public Boolean delete(Long id) {
        projectRepository.deleteById(id);
    	return true;
    }

	

}
