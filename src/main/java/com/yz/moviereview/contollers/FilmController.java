package com.yz.moviereview.contollers;

import com.yz.moviereview.entities.Film;
import com.yz.moviereview.entities.Review;
import com.yz.moviereview.entities.USERROLE;
import com.yz.moviereview.entities.User;
import com.yz.moviereview.exceptions.ValidationException;
import com.yz.moviereview.requests.FilmRequest;
import com.yz.moviereview.requests.FilmResponse;
import com.yz.moviereview.requests.ReviewResponse;
import com.yz.moviereview.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController extends Controller {

    @Autowired
    private FilmService filmService;

    @GetMapping
    public @ResponseBody
    Page<Film> getFilms(@RequestParam(value = "country", required = false) String country, @RequestParam(value = "year", required = false) Integer year, Pageable pageable){
        return filmService.getFilms(country, year, pageable);
    }

    @GetMapping("/{id}")
    public @ResponseBody
    FilmResponse getFilm(@PathVariable(value = "id", required = true) Long id){
        Film film = filmService.getFilm(id);
        FilmResponse filmResponse = new FilmResponse();
        filmResponse.setId(film.getId());
        filmResponse.setCountry(film.getCountry());
        filmResponse.setDescription(film.getDescription());
        filmResponse.setTitle(film.getTitle());
        filmResponse.setPoster(film.getPoster());
        filmResponse.setYear(film.getYear());
        filmResponse.setActors(film.getActors());
        List<ReviewResponse> reviewResponses = new ArrayList<>();
        for (Review review : film.getReviews()){
            ReviewResponse reviewResponse = new ReviewResponse();
            reviewResponse.setId(review.getId());
            reviewResponse.setTitle(review.getTitle());
            reviewResponse.setContent(review.getContent());
            reviewResponse.setRate(review.getRate());
            reviewResponse.setUserFullname(review.getUser().getName());
            reviewResponses.add(reviewResponse);
        }
        filmResponse.setReviews(reviewResponses);
        return filmResponse;
    }
    @PostMapping
    public @ResponseBody Film newFilm(@RequestBody FilmRequest filmRequest, HttpServletRequest request){
        User user = getUser(request);
        if (user.getRole() != USERROLE.ADMIN){
            throw new ValidationException("User is not admin");
        }
        Film film = new Film();
        film.setPoster(filmRequest.getPoster());
        film.setYear(filmRequest.getYear());
        film.setTitle(filmRequest.getTitle());
        film.setDescription(filmRequest.getDescription());
        film.setCountry(filmRequest.getCountry());
        return filmService.addFilm(film);
    }

    @PutMapping("/{id}")
    public Film updateFilm(@PathVariable("id") Long id, @RequestBody FilmRequest filmRequest, HttpServletRequest request){
        User user = getUser(request);
        if (user.getRole() != USERROLE.ADMIN){
            throw new ValidationException("User is not admin");
        }
        Film film = new Film();
        film.setPoster(filmRequest.getPoster());
        film.setYear(filmRequest.getYear());
        film.setTitle(filmRequest.getTitle());
        film.setDescription(filmRequest.getDescription());
        film.setCountry(filmRequest.getCountry());
        return filmService.updateFilm(id, film);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFilm(@PathVariable("id") Long id, HttpServletRequest request){
        User user = getUser(request);
        if (user.getRole() != USERROLE.ADMIN){
            throw new ValidationException("User is not admin");
        }
        filmService.deleteFilm(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
