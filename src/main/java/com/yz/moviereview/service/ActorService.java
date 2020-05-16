package com.yz.moviereview.service;

import com.yz.moviereview.entities.Actor;
import com.yz.moviereview.entities.Film;
import com.yz.moviereview.repositories.ActorRepository;
import com.yz.moviereview.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {
    @Autowired
    private ActorRepository actorRepository;

    public List<Actor> getAllActors(){
        return actorRepository.findAll();
    }

    public Actor getActor(Long id){
        return actorRepository.findById(id).orElse(null);
    }
}
