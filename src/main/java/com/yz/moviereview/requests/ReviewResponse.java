package com.yz.moviereview.requests;

import lombok.Data;

@Data
public class ReviewResponse {
    private Long id;

    private String title;

    private String content;

    private Integer rate;

    private String userFullname;
}
