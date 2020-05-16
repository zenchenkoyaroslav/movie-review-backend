package com.yz.moviereview.repositories;

import com.yz.moviereview.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
