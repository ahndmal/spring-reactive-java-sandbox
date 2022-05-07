package com.anma.springreactivejl.rsock.pp;

import io.rsocket.Payload;
import io.rsocket.SocketAcceptor;
import io.rsocket.core.RSocketServer;
import io.rsocket.transport.netty.server.TcpServerTransport;
import io.rsocket.util.DefaultPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public record Pong(BootifulProperties properties) {

    @EventListener(ApplicationReadyEvent.class)
    public void ready() {

        final Logger log = LoggerFactory.getLogger(Pong.class);

        var socketAcceptor = SocketAcceptor
                .forRequestChannel(payloads -> Flux.from(payloads)
                        .map(Payload::getDataUtf8).map(s -> "Echo: " + s)
                        .map(DefaultPayload::create)//
                );

        RSocketServer.create(socketAcceptor)
                .bind(TcpServerTransport.create(this.properties.getHost(),
                        this.properties.getPort()))
                .doOnNext(cc -> log.info("server started on the address " + cc.address())) //
                .block();
    }

//    @Override
//    public Mono<RSocket> accept(ConnectionSetupPayload payload, RSocket rSocket) {
//        var rs = new AbstrRSock
//        return null;
//    }
}
