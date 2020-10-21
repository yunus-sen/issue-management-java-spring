package com.yunuss.issuemanagement.dto;

import lombok.*;

import java.util.Date;

import com.yunuss.issuemanagement.entities.IssueStatus;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueDto {
    private Long id;
    private String description;
    private String details;
    private Date date;
    private IssueStatus issueStatus;
    private UserDto assignee;
    private ProjectDto project;
}
