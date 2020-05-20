package com.yz.moviereview.repositories;

import com.yz.moviereview.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long>, PagingAndSortingRepository<Film, Long> {
    List<Film> findByCountryAndYear(String country, Integer year);
    List<Film> findByCountry(String country);
    List<Film> findByYear(Integer year);
}
