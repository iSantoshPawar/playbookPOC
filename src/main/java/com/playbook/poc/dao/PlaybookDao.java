package com.playbook.poc.dao;

import com.playbook.poc.entity.Playbook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaybookDao extends JpaRepository<Playbook, Integer> {

}
