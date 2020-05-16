package com.yz.moviereview.repositories;

import com.yz.moviereview.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {
}
