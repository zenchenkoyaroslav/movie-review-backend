package com.yz.moviereview.service;

import com.yz.moviereview.entities.Actor;
import com.yz.moviereview.exceptions.ValidationException;
import com.yz.moviereview.repositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ActorService {
    @Autowired
    private ActorRepository actorRepository;

    public List<Actor> getAllActors(){
        List<Actor> actors = new ArrayList<>();
        actorRepository.findAll().forEach(actors::add);
        return actors;
    }

    public Actor getActor(Long id){
        return actorRepository.findById(id).orElse(null);
    }

    public Actor addActor(Actor actor){
        Objects.requireNonNull(actor);
        if (StringUtils.isEmpty(actor.getName())){
            throw new ValidationException("Actor name is empty");
        }
        if (StringUtils.isEmpty(actor.getPhoto())){
            throw new ValidationException("Photo url is empty");
        }
        return actorRepository.save(actor);
    }

    public Actor updateActor(Long oldId, Actor actor){
        Objects.requireNonNull(actor);
        if (StringUtils.isEmpty(actor.getName())){
            throw new ValidationException("Actor name is empty");
        }
        if (StringUtils.isEmpty(actor.getPhoto())){
            throw new ValidationException("Photo url is empty");
        }
        Actor fromDB = actorRepository.findById(oldId).orElse(null);
        if (fromDB == null){
            throw new ValidationException("Old actor doesnt exists");
        }
        fromDB.setName(actor.getName());
        fromDB.setPhoto(actor.getPhoto());
        fromDB.setFilms(actor.getFilms());
        return actorRepository.save(fromDB);
    }

    public void deleteActor(Long id){
        actorRepository.deleteById(id);
    }
}
