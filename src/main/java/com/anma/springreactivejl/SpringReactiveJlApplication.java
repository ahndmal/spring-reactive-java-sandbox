package com.anma.springreactivejl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

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
