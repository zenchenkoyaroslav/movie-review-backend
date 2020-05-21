package com.yz.moviereview.requests;

import lombok.Data;

@Data
public class ReviewRequest {
    private Long id;

    private String title;

    private String content;

    private Integer rate;

    private Long filmId;

    private Long userId;
}
