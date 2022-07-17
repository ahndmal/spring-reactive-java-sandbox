package com.anma.springreactivejl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.test.StepVerifier;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class CatTest1 {

    Logger log = LoggerFactory.getLogger(CatTest1.class);

    private final ExecutorService executorService = Executors.newFixedThreadPool(1);

    @Test
    void test1() {
        Publisher<Integer> publisher = Flux.range(1, 1000);
        StepVerifier.create(publisher).expectNextCount(1000).verifyComplete();
    }

    @Test
    void test2() {

    }

    @Test
    public void async1() {
        // <1>
        var integers = Flux.<Integer>create(emitter -> this.launch(emitter, 5));
        // <2>
        StepVerifier.create(integers.doFinally(signalType -> this.executorService.shutdown())).expectNextCount(5)//
                .verifyComplete();
    }

    private void launch(FluxSink<Integer> integerFluxSink, int count) {
        this.executorService.submit(() -> {
            var integer = new AtomicInteger();
            Assertions.assertNotNull(integerFluxSink);
            while (integer.get() < count) {
                double random = Math.random();
                integerFluxSink.next(integer.incrementAndGet());// <4>
                this.sleep((long) (random * 1_000));
            }
            integerFluxSink.complete(); // <5>
        });
    }

    private void sleep(long s) {
        try {
            Thread.sleep(s);
        } //
        catch (Exception e) {
            log.error("something's wrong!", e);
        }
    }
}
