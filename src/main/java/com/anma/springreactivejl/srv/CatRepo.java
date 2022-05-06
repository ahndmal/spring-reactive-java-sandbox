package com.anma.springreactivejl.srv;

import com.anma.springreactivejl.model.Cat;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface CatRepo extends ReactiveCrudRepository<Cat, Integer> {

    @Query("select * from cats where name = $1")
    public Flux<Cat> catsByName(String name);
}
