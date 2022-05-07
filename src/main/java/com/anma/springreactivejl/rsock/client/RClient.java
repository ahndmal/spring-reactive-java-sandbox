package com.anma.springreactivejl.rsock.client;

import io.rsocket.core.RSocketClient;
import io.rsocket.core.RSocketConnector;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Component
public record RClient() {

    @EventListener(ApplicationReadyEvent.class)
    public void ready() {
        var source = RSocketConnector.create()
                .reconnect(Retry.backoff(50, Duration.ofMillis(500)))//
                .connect(TcpClientTransport.create("localhost", 7766));

        RSocketClient.from(source).fireAndForget(Mono.just(DefaultPayload.create("Reactive Spring!"))).block();
    }
}
