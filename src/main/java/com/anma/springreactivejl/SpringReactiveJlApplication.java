package com.anma.springreactivejl;

import com.anma.springreactivejl.ctr.CatHandlerFunction;
import com.anma.springreactivejl.rsock.pp.BootifulProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.function.server.support.HandlerFunctionAdapter;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@EnableConfigurationProperties(BootifulProperties.class)
@SpringBootApplication
public class SpringReactiveJlApplication {

    private final Logger logger = LoggerFactory.getLogger(SpringReactiveJlApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringReactiveJlApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void listen() {
        logger.info(">>> App started");
    }



}
