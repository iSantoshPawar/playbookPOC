package com.playbook.poc.dao;

import com.playbook.poc.entity.PlaybookTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaybookTaskDao extends JpaRepository<PlaybookTask, Integer> {

}
