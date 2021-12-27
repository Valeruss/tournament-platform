package com.tournamentservice.tournamentservice.repo.model;

import javax.persistence.*;

@Entity
@Table(name="tournaments")
public final class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
    private Integer size;

    public Tournament() {

    }

    public Tournament(String name, String description, Integer size) {
        this.name = name;
        this.description = description;
        this.size = size;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSize() {
        return size;
    }
    public void setSize(Integer size) {
        this.size = size;
    }
}
