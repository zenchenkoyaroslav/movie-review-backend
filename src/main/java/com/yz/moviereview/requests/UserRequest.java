package com.yz.moviereview.requests;

import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String password;
    private String token;
}
