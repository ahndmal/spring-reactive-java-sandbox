package com.anma.springreactivejl.srv;


import com.anma.springreactivejl.model.Cat;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CatService {

    public Mono<List<Cat>> allCats();
}
