package com.yunuss.issuemanagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.yunuss.issuemanagement.entities.IssueHistory;


public interface IssueHistoryRepository extends JpaRepository<IssueHistory, Long> {

}
