package com.anma.springreactivejl.wsock.echo;

import com.anma.springreactivejl.utils.IntervalMessageProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;

import java.util.Map;

@Configuration
class EchoWebsocketConfiguration {
    private final Logger log = LoggerFactory.getLogger(EchoWebsocketConfiguration.class);

    @Bean
    HandlerMapping echoHm() {
        return new SimpleUrlHandlerMapping(Map.of("/ws/echo", echoWsh()), 10);
    }

    @Bean
    WebSocketHandler echoWsh() {
        return session -> {

            Flux<WebSocketMessage> out = IntervalMessageProducer
                    .produce() //
                    .doOnNext(log::info) //
                    .map(session::textMessage) // <4>
                    .doFinally(signalType -> log.info("outbound connection: " + signalType)); // <5>

            Flux<String> in = session //
                    .receive() //
                    .map(WebSocketMessage::getPayloadAsText) // <6>
                    .doFinally(signalType -> {
                        log.info("inbound connection: " + signalType);
                        if (signalType.equals(SignalType.ON_COMPLETE)) {
                            session.close().subscribe();
                        }
                    }).doOnNext(log::info);
            return session.send(out).and(in);// <7>
        };
    }

}
