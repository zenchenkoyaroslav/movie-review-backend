package com.yz.moviereview.contollers;

import com.yz.moviereview.entities.Film;
import com.yz.moviereview.entities.USERROLE;
import com.yz.moviereview.entities.User;
import com.yz.moviereview.exceptions.ValidationException;
import com.yz.moviereview.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/films")
public class FilmController extends Controller {

    @Autowired
    private FilmService filmService;

    @GetMapping
    public @ResponseBody
    Page<Film> getFilms(Pageable pageable){
        return filmService.getFilms(pageable);
    }

    @GetMapping("/{id}")
    public @ResponseBody
    Film getFilm(@PathVariable(value = "id", required = true) Long id){
        Film film = filmService.getFilm(id);
        return film;
    }
    @PostMapping
    public @ResponseBody Film newFilm(@RequestBody Film film, HttpServletRequest request){
        User user = getUser(request);
        if (user.getRole() != USERROLE.ADMIN){
            throw new ValidationException("User is not admin");
        }
        return filmService.addFilm(film);
    }

    @PutMapping("/{id}")
    public Film updateFilm(@PathVariable("id") Long id, @RequestBody Film film, HttpServletRequest request){
        User user = getUser(request);
        if (user.getRole() != USERROLE.ADMIN){
            throw new ValidationException("User is not admin");
        }
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
