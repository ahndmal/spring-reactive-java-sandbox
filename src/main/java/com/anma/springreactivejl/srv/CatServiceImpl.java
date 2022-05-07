package com.anma.springreactivejl.srv;

import com.anma.springreactivejl.model.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatServiceImpl implements CatService {

    private final CatRepo catRepo;

    @Autowired
    public CatServiceImpl(CatRepo catRepo) {
        this.catRepo = catRepo;
    }

    @Override
    public Mono<List<Cat>> allCats() {

        return catRepo.findAll().collect(Collectors.toList());
    }
}
