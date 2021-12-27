package com.userservice.userservice.repo.model;

import javax.persistence.*;

@Entity
@Table(name="users")
public final class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String password;
    private Integer tournament_played;

    public User() {

    }

    public User(String name, String password, Integer tournament_played) {
        this.name = name;
        this.password = password;
        this.tournament_played = tournament_played;
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

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getTournament_played() {
        return tournament_played;
    }
    public void setTournament_played(Integer tournament_played) {
        this.tournament_played = tournament_played;
    }
}
