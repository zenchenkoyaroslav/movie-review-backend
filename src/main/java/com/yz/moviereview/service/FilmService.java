package com.yz.moviereview.service;

import com.yz.moviereview.entities.Film;
import com.yz.moviereview.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {
    @Autowired
    private FilmRepository filmRepository;

    public List<Film> getAllFilms(){
        return filmRepository.findAll();
    }

    public List<Film> getFilms(String country, Integer year){
        if (country != null && year != null){
            return filmRepository.findByCountryAndYear(country, year);
        }
        if (country != null){
            return filmRepository.findByCountry(country);
        }
        if (year != null){
            return filmRepository.findByYear(year);
        }
        return getAllFilms();
    }

    public Film getFilm(Long id){
        return filmRepository.findById(id).orElse(null);
    }
}
