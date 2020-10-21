package com.yunuss.issuemanagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.yunuss.issuemanagement.entities.User;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}