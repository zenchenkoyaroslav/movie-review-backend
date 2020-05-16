package com.yz.moviereview.contollers;

import com.yz.moviereview.entities.Film;
import com.yz.moviereview.entities.User;
import com.yz.moviereview.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController extends Controller {

    @Autowired
    private FilmService filmService;

    @GetMapping
    public @ResponseBody
    List<Film> getAllFilms(){
        return filmService.getAllFilms();
    }

    @GetMapping("/{id}")
    public @ResponseBody
    Film getAllFilms(@PathVariable(value = "id", required = true) Long id){
        return filmService.getFilm(id);
    }
}
