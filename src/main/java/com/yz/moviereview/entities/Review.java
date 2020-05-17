package com.yz.moviereview.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @JsonIgnore
    @OneToMany(mappedBy = "film", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<UserFilmReviewRelation> filmReviewRelations = new ArrayList<>();
}
