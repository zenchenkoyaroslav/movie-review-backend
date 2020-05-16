package com.yz.moviereview.repositories;

import com.yz.moviereview.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
}
