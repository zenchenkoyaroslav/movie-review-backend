package com.yz.moviereview.requests;

import com.yz.moviereview.entities.Actor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FilmResponse {
    private Long id;

    private String title;

    private String description;

    private String poster;

    private Integer year;

    private String country;

    List<Actor> actors = new ArrayList<>();

    private List<ReviewResponse> reviews = new ArrayList<>();
}
