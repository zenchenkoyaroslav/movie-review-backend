package com.yz.moviereview.contollers;


import com.yz.moviereview.entities.Actor;
import com.yz.moviereview.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @PostMapping
    public @ResponseBody Actor newActor(@RequestBody Actor actor, HttpServletRequest request){
        getUser(request);
        return actorService.addActor(actor);
    }

    @PutMapping("/{id}")
    public Actor updateActor(@PathVariable("id") Long id, @RequestBody Actor actor, HttpServletRequest request){
        getUser(request);
        return actorService.updateActor(id, actor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteActor(@PathVariable("id") Long id, HttpServletRequest request){
        getUser(request);
        actorService.deleteActor(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
