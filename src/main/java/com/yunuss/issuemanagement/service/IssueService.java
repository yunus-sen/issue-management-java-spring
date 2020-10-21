package com.yunuss.issuemanagement.service;

import org.springframework.data.domain.Pageable;

import com.yunuss.issuemanagement.dto.IssueDto;
import com.yunuss.issuemanagement.util.TPage;

/**
 * Created by temelt on 4.02.2019.
 */
public interface IssueService {

    IssueDto save(IssueDto issue);

    IssueDto getById(Long id);

    TPage<IssueDto> getAllPageable(Pageable pageable);

    Boolean delete(IssueDto issue);
}
