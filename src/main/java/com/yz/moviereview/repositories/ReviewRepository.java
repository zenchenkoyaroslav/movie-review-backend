package com.yz.moviereview.repositories;

import com.yz.moviereview.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    Review findByUser_IdAndFilm_Id(Long userId, Long filmId);
}
