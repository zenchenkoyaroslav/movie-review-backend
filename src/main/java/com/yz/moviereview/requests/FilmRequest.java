package com.yz.moviereview.requests;

import lombok.Data;

import javax.persistence.Column;

@Data
public class FilmRequest {
    private String title;
    private String description;
    private String poster;
    private Integer year;
    private String country;
}
