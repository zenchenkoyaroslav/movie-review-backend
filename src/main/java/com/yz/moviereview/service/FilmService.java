package com.yz.moviereview.service;

import com.yz.moviereview.entities.Film;
import com.yz.moviereview.exceptions.ValidationException;
import com.yz.moviereview.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FilmService {
    @Autowired
    private FilmRepository filmRepository;

    public List<Film> getAllFilms(){
        return StreamSupport.stream(filmRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Page<Film> getFilms(String country, Integer year, Pageable pageable){
        if (country != null && year != null){
            return filmRepository.findAllByCountryAndYear(country, year, pageable);
        }
        if (country != null){
            return filmRepository.findAllByCountry(country, pageable);
        }
        if (year != null){
            return filmRepository.findAllByYear(year, pageable);
        }
        return filmRepository.findAll(pageable);
    }

    public Film getFilm(Long id){
        return filmRepository.findById(id).get();
    }

    public Film addFilm(Film film){
        if (StringUtils.isEmpty(film.getCountry())){
            throw new ValidationException("Country is null");
        }
        if (StringUtils.isEmpty(film.getDescription())){
            throw new ValidationException("Description is null");
        }
        if (StringUtils.isEmpty(film.getTitle())){
            throw new ValidationException("Description is null");
        }
        if (film.getYear()==null){
            throw new ValidationException("Year is null");
        }
        if (StringUtils.isEmpty(film.getPoster())){
            throw new ValidationException("Poster is null");
        }
        return filmRepository.save(film);
    }

    public Film updateFilm(Long id, Film film){
        Film fromDB = filmRepository.findById(id).get();
        if (fromDB==null){
            throw new ValidationException("Film doesnt exists");
        }
        if (StringUtils.isEmpty(film.getCountry())){
            throw new ValidationException("Country is null");
        }
        if (StringUtils.isEmpty(film.getDescription())){
            throw new ValidationException("Description is null");
        }
        if (StringUtils.isEmpty(film.getTitle())){
            throw new ValidationException("Description is null");
        }
        if (film.getYear()==null){
            throw new ValidationException("Year is null");
        }
        if (StringUtils.isEmpty(film.getPoster())){
            throw new ValidationException("Poster is null");
        }
        film.setReviews(fromDB.getReviews());
        film.setActors(fromDB.getActors());
        film.setId(fromDB.getId());
        return filmRepository.save(film);
    }

    public void deleteFilm(Long id){
        filmRepository.deleteById(id);
    }
}
