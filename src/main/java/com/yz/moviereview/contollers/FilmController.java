package com.yz.moviereview.contollers;

import com.yz.moviereview.entities.Film;
import com.yz.moviereview.entities.User;
import com.yz.moviereview.entities.UserFilmReviewRelation;
import com.yz.moviereview.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/films")
public class FilmController extends Controller {

    @Autowired
    private FilmService filmService;

    @GetMapping
    public @ResponseBody
    List<Film> getFilms(@RequestParam(value = "country", required = false) String country, @RequestParam(value = "year", required = false) Integer year){
        return filmService.getFilms(country, year);
    }

    @GetMapping("/{id}")
    public @ResponseBody
    Film getFilm(@PathVariable(value = "id", required = true) Long id){
        Film film = filmService.getFilm(id);
        if (film != null){
            film.setReviews(film.getFilmReviewRelations().stream().map(UserFilmReviewRelation::getReview).collect(Collectors.toList()));
        }
        return film;
    }
}
