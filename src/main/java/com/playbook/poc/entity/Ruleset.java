package com.playbook.poc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RULESET")
public class Ruleset {

    @Column(name = "RULSET_ID")
    private Integer rulesetID;

    @Column(name = "RULESET_NAME")
    @Id
    private String rulesetTagname;

    public Integer getRulesetID() {
        return rulesetID;
    }

    public void setRulesetID(Integer rulesetID) {
        this.rulesetID = rulesetID;
    }

    public String getRulesetTagname() {
        return rulesetTagname;
    }

    public void setRulesetTagname(String rulesetTagname) {
        this.rulesetTagname = rulesetTagname;
    }

    @Override
    public String toString() {
        return "RulesetEnvironment{" +
                "rulesetID=" + rulesetID +
                ", rulesetTagname='" + rulesetTagname + '\'' +
                '}';
    }
}