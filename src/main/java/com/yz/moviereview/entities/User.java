package com.yz.moviereview.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "USER")
public class User {
    @Id
    @SequenceGenerator(name = "USER_SEQUENCE", sequenceName = "USER_SEQUENCE_ID",
                        initialValue = 100, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQUENCE")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Column(nullable = true)
    private String token;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private USERROLE role;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Review> reviews = new ArrayList<>();
}
