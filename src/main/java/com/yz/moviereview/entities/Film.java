package com.yz.moviereview.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "FILM")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String poster;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private String country;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "REL_FILM_ACTOR", joinColumns = { @JoinColumn(name = "film_id")}, inverseJoinColumns = {@JoinColumn(name = "actor_id")})
    @JsonManagedReference
    List<Actor> actors = new ArrayList<>();

    @OneToMany(mappedBy = "film", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Review> reviews = new ArrayList<>();

}
