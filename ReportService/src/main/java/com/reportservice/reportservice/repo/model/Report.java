package com.reportservice.reportservice.repo.model;

import javax.persistence.*;

@Entity
@Table(name="reports")
public final class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Integer tournament_id;
    private Integer violator_id;
    private String description;
    private String decision;

    public Report() {

    }

    public Report(Integer tournament_id, Integer violator_id, String description, String decision) {
        this.tournament_id = tournament_id;
        this.violator_id = violator_id;
        this.description = description;
        this.decision = decision;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Integer getTournament_id() {
        return tournament_id;
    }
    public void setTournament_id(Integer tournament_id) {
        this.tournament_id = tournament_id;
    }

    public Integer getViolator_id() {
        return violator_id;
    }
    public void setViolator_id(Integer violator_id) {
        this.violator_id = violator_id;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDecision() {
        return decision;
    }
    public void setDecision(String decision) {
        this.decision = decision;
    }
}
