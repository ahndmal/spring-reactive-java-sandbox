package com.anma.springreactivejl;

import com.anma.springreactivejl.srv.CatRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
//import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class Boot implements CommandLineRunner {

    Logger log = LoggerFactory.getLogger(Boot.class);
    private final DatabaseClient databaseClient;
    private final CatRepo catRepo;

    public Boot(DatabaseClient databaseClient, CatRepo catRepo) {
        this.databaseClient = databaseClient;
        this.catRepo = catRepo;
    }

//    @Bean
//    DatabaseClient databaseClient() {
//        ConnectionFactoryOptions options = ConnectionFactoryOptions.builder()
//                .option(Option.valueOf("DATABASE"), "cats")
//                .option(Option.valueOf("DRIVER"), "postgresql")
//                .option(Option.valueOf("HOST"), "172.17.0.2")
//                .option(Option.valueOf("URL"), "r2dbc:postgresql://172.17.0.2/cats")
//                .option(Option.valueOf("PORT"), "5432")
//                .option(Option.valueOf("USER"), "dev")
//                .option(Option.valueOf("PASSWORD"), System.getenv("DB_PASS")).build();
//        return DatabaseClient.builder().connectionFactory(ConnectionFactories.get(options)).build();
//    }

    @Override
    public void run(String... args) throws Exception {
        catRepo.findAll().doOnNext(System.out::println).subscribe();
//        loadData();
    }

    private void loadData() {
//        databaseClient.insert().into(MyCat.class).table("cats").using(new MyCat(5, "")).then().subscribe();
        Flux.create(sink -> {
            for (int i = 0; i < 500; i++) {
                var name = "Cat " +i;
                databaseClient.sql("insert into cats (age, name) values (" +
                        ThreadLocalRandom.current().nextInt(1,10) + ",'" + name + "');")
                        .then().subscribe();
                log.info("Cat created!");
                sink.next(i);
            }
            sink.complete();
        }).doOnNext(System.out::println).subscribe();


    }
}

class MyCat {
    int age;
    String name;

    public MyCat(int age, String name) {
        this.age = age;
        this.name = name;
    }
}
