package com.playbook.poc.dao;

import com.playbook.poc.entity.Ruleset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RulesetEnvironmentDao extends JpaRepository<Ruleset, Integer> {

}
