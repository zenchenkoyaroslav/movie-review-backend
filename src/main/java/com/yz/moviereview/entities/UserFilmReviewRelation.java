package com.yz.moviereview.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "USER")
public class UserFilmReviewRelation {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "review_id")
    private Review review;
}
