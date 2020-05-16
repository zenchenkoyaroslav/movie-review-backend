package com.yz.moviereview.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "ACTOR")
public class Actor {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String photo;

    @ManyToMany(mappedBy = "actors")
    @JsonBackReference
    private List<Film> films = new ArrayList<>();
}
