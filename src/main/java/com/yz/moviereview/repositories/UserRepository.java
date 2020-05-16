package com.yz.moviereview.repositories;

import com.yz.moviereview.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
