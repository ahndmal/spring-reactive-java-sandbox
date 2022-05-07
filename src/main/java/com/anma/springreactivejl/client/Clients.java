package com.anma.springreactivejl.client;

import com.anma.springreactivejl.model.Cat;
import com.anma.springreactivejl.srv.CatRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Configuration
public class Clients {

    private static final String DATA_URL = "http://localhost:8088";

    @Bean
    WebClient webClient() {
        return WebClient.builder().baseUrl(DATA_URL).defaultHeader("H1", "H1")
                .build();
    }

    public Mono<Cat> getSingleCat(String name) {
        return webClient().get()
                .uri("/cats", Map.of("name", "Murko"))
                .retrieve()
                .bodyToMono(Cat.class);
    }

    public Flux<Cat> allCats() {
        return webClient().get().uri("/cats/all").retrieve()
                .bodyToFlux(Cat.class)
                .doFinally(el -> System.out.println(">> All Cats received!"));
    }
}
