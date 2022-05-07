package com.anma.springreactivejl.rsock.service;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.SocketAcceptor;
import io.rsocket.core.RSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.netty.tcp.TcpServer;
import io.rsocket.transport.netty.server.TcpServerTransport;

import java.net.InetSocketAddress;

@Component
public record RService() {

    @EventListener(ApplicationReadyEvent.class)
    public void ready() {

        final Logger log = LoggerFactory.getLogger(RService.class);

        var transport = TcpServerTransport.create(InetSocketAddress.createUnresolved("localhost", 7766));
        var socket = new RSocket() {

            @Override
            public Mono<Void> fireAndForget(Payload payload) {
                var request = payload.getDataUtf8();
                log.info("received " + request);
                return Mono.empty();
            }
        };

        var socketAcceptor = SocketAcceptor.with(socket);
        RSocketServer.create(socketAcceptor)
                .bind(transport)
                .doOnNext(cc -> log.info("server started on " + cc.address()))
                .block();
    }
}
