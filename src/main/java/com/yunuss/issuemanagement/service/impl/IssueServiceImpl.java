package com.yunuss.issuemanagement.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.yunuss.issuemanagement.dto.IssueDto;
import com.yunuss.issuemanagement.entities.Issue;
import com.yunuss.issuemanagement.repository.IssueRepository;
import com.yunuss.issuemanagement.service.IssueService;
import com.yunuss.issuemanagement.util.TPage;

import java.util.Arrays;

@Service
public class IssueServiceImpl implements IssueService{

    private final IssueRepository issueRepository;
    private final ModelMapper modelMapper;

    public IssueServiceImpl(IssueRepository issueRepository,ModelMapper modelMapper){
        this.issueRepository = issueRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public IssueDto save(IssueDto issue){
        // Bussiness Logic

        if(issue.getDate()==null){
            throw new IllegalArgumentException("Issue Date cannot be null");
        }

        Issue issueEntity = modelMapper.map(issue, Issue.class);

        issueEntity = issueRepository.save(issueEntity);

        issue.setId(issueEntity.getId());
        return issue;
    }

    @Override
    public IssueDto getById(Long id) {
        Issue issue = issueRepository.getOne(id);
        return modelMapper.map(issue,IssueDto.class);
    }

    @Override
    public TPage<IssueDto> getAllPageable(Pageable pageable) {
        Page<Issue> data= issueRepository.findAll(pageable);
        TPage<IssueDto> respnose= new TPage<IssueDto>();
        respnose.setStat(data,Arrays.asList(modelMapper.map(data.getContent(), IssueDto[].class)));
        return respnose;
    }

	/*
	 * @Override public Boolean delete(Long id) {
	 * issueRepository.deleteById(issue.getId()); return true; }
	 */

	@Override
	public IssueDto update(Long id, IssueDto issue) {
	
		Issue issueDb=issueRepository.getOne(id);
		if(issueDb==null) {
			throw new IllegalArgumentException("Issue do not exist id:"+id);
		}
		
		issueDb.setDate(issue.getDate());
		issueDb.setDescription(issue.getDescription());
		issueDb.setDetails(issue.getDetails());
		issueDb.setIssueStatus(issue.getIssueStatus());
		issueDb=issueRepository.save(issueDb);
		return modelMapper.map(issueDb, IssueDto.class);
	}

	@Override
	public Boolean deleteById(Long id) {
		issueRepository.deleteById(id);
		return true;
	}

}