package com.yunuss.issuemanagement.service;

import org.springframework.data.domain.Pageable;

import com.yunuss.issuemanagement.dto.IssueDto;
import com.yunuss.issuemanagement.util.TPage;


public interface IssueService {

    IssueDto save(IssueDto issue);

    IssueDto getById(Long id);

    TPage<IssueDto> getAllPageable(Pageable pageable);

    Boolean deleteById(Long id);

	IssueDto update(Long id, IssueDto project);
}
