package com.playbook.poc.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PLAYBOOK")
public class Playbook {

    @OneToMany(mappedBy = "playbook")
    List<PlaybookTask> playbookTask;
    @Column(name = "PLAYBOOK_ID")
    @Id
    private Integer playbookId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "TYPE")
    private String type;

    public Integer getPlaybookId() {
        return playbookId;
    }

    public void setPlaybookId(Integer playbookId) {
        this.playbookId = playbookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public List<PlaybookTask> getPlaybookTask() {
        return playbookTask;
    }

    public void setPlaybookTask(List<PlaybookTask> playbookTask) {
        this.playbookTask = playbookTask;
    }

    @Override
    public String toString() {
        return "Playbook{" +
                "id=" + playbookId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", playbookTask=" + playbookTask +
                '}';
    }
}