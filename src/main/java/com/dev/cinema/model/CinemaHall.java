package com.dev.cinema.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cinema_hall")
public class CinemaHall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany
    private List<MovieSession> movieSessions;

    public CinemaHall() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
