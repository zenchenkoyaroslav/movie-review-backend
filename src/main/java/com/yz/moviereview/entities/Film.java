package com.yz.moviereview.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "FILM")
public class Film {
    @Id
    @GeneratedValue
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
    List<Actor> actors = new ArrayList<>();

    @OneToMany(mappedBy = "film", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<UserFilmReviewRelation> filmReviewRelations = new ArrayList<>();

}
