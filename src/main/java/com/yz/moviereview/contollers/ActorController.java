package com.yz.moviereview.contollers;


import com.yz.moviereview.entities.Actor;
import com.yz.moviereview.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actors")
public class ActorController extends Controller {

    @Autowired
    private ActorService actorService;

    @GetMapping
    public @ResponseBody
    List<Actor> getAllActors(){
        return actorService.getAllActors();
    }

    @GetMapping("/{id}")
    public @ResponseBody Actor getActor(@PathVariable("id") Long id){
        return actorService.getActor(id);
    }
}
