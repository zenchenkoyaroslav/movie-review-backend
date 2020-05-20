package com.yz.moviereview.repositories;

import com.yz.moviereview.entities.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FilmRepository extends PagingAndSortingRepository<Film, Long> {
    Page<Film> findAllByCountryAndYear(String country, Integer year, Pageable pageable);
    Page<Film> findAllByCountry(String country, Pageable pageable);
    Page<Film> findAllByYear(Integer year, Pageable pageable);
}
