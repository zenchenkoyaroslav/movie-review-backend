package com.yz.moviereview.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "REVIEW")
public class Review {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Integer rate;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "FILM_ID", nullable = false)
    private Film film;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;
}
