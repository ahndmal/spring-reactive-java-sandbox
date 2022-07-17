package com.anma.springreactivejl.sse;

import com.anma.springreactivejl.utils.IntervalMessageProducer;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class SseConfiguration {
    private final Logger log = LoggerFactory.getLogger(SseConfiguration.class);
    private final String countPathVariable = "count";

    @Bean
    RouterFunction<ServerResponse> routes() {
        return route()
                .GET("/sse/{" + this.countPathVariable + "}", this::handleSse) //
                .build();
    }

    Mono<ServerResponse> handleSse(ServerRequest req) {
        int countPathVariable = Integer.parseInt(req.pathVariable(this.countPathVariable));
        var publisher = IntervalMessageProducer.produce(countPathVariable).doOnComplete(() -> log.info("completed"));

        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(publisher, String.class);

    }
}
