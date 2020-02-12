package com.playbook.poc.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "PLAYBOOK_TASK")
public class PlaybookTask {

    @ManyToOne
    @JoinColumn(name = "PLAYBOOK_ID", insertable = false, updatable = false)
    Playbook playbook;
    @Column(name = "PLAYBOOK_ID")
    private Integer id;
    @Column(name = "TASK_ID")
    @Id
    private Integer taskID;
    @OneToOne
    @JoinColumn(name = "RULESET_NAME", insertable = false, updatable = false)
    private Ruleset ruleset;

    @Column(name = "TASK")
    private String task;

    @Column(name = "TASK_TYPE")
    private String taskType;

    @Column(name = "IS_REPEATABLE")
    private String isRepeatable;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "MODIFIED_ON")
    private Date modifiedDate;

    @Column(name = "RULESET_NAME")
    private String rulesetTagname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaskID() {
        return taskID;
    }

    public void setTaskID(Integer taskID) {
        this.taskID = taskID;
    }

    public Playbook getPlaybook() {
        return playbook;
    }

    public void setPlaybook(Playbook playbook) {
        this.playbook = playbook;
    }

    public Ruleset getRuleset() {
        return ruleset;
    }

    public void setRuleset(Ruleset ruleset) {
        this.ruleset = ruleset;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getIsRepeatable() {
        return isRepeatable;
    }

    public void setIsRepeatable(String isRepeatable) {
        this.isRepeatable = isRepeatable;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getRulesetTagname() {
        return rulesetTagname;
    }

    public void setRulesetTagname(String rulesetTagname) {
        this.rulesetTagname = rulesetTagname;
    }

    @Override
    public String toString() {
        return "PlaybookTask{" +
                "id=" + id +
                ", taskID=" + taskID +
                ", task='" + task + '\'' +
                ", taskType='" + taskType + '\'' +
                ", isRepeatable='" + isRepeatable + '\'' +
                ", startDate=" + startDate +
                ", modifiedDate=" + modifiedDate +
                ", rulesetTagname='" + rulesetTagname + '\'' +
                '}';
    }
}