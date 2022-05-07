package com.anma.springreactivejl.ctr;

import com.anma.springreactivejl.model.Cat;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Component
public class MyFuncs {

    @Bean
    RouterFunction<ServerResponse> simple(CatHandlerFunction handler) {

        return RouterFunctions.route()
                .GET("/cats/{name}", request -> {
                    var pathVar = request.pathVariable("name");
                    var msg = String.format("Hello %s", pathVar);
                    return ServerResponse.ok().bodyValue(msg);
                })
                .GET("/cats", req -> ServerResponse.ok().bodyValue(handler.cats(req).map(Cat::getName)))
                .build();
    }
}
