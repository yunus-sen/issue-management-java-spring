package com.yunuss.issuemanagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.yunuss.issuemanagement.entities.Issue;


public interface IssueRepository extends JpaRepository<Issue, Long> {

}
