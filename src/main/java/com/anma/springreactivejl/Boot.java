package com.anma.springreactivejl;

import com.anma.springreactivejl.model.Cat;
import com.anma.springreactivejl.srv.CatRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
//import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class Boot implements CommandLineRunner {

    Logger log = LoggerFactory.getLogger(Boot.class);
    private final DatabaseClient databaseClient;
    private final R2dbcEntityTemplate entityTemplate;
    private final CatRepo catRepo;

    public Boot(DatabaseClient databaseClient, R2dbcEntityTemplate entityTemplate, CatRepo catRepo) {
        this.databaseClient = databaseClient;
        this.entityTemplate = entityTemplate;
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
        var start = LocalDateTime.now();
//        loadDataFromModels().subscribe();
//        catRepo.deleteAll().thenMany(catRepo.saveAll(Flux.create(loadDataFromModels())))
//        catRepo.findAll().doOnNext(System.out::println).subscribe();

        // END
        System.out.printf(">> the script took: %d", Duration.between(start, LocalDateTime.now()).toMillis());
    }

    private Flux<Object> loadDataFromModels() {
        return Flux.generate(sink -> {
            for (int i = 0; i < 1000; i++) {
                Cat cat = new Cat();
                cat.setCatId(i);
                cat.setAge(generateRandom(13));
                cat.setAdaptability(generateRandom(1));
                cat.setBreed("test");
                cat.setDogFriendly(generateRandom(1));
                cat.setColor("");
                cat.setCountryCodes("");
                cat.setHairless(generateRandom(1));
                cat.setIndoor(generateRandom(1));
                cat.setIntelligence(generateRandom(10));
                cat.setName("Cat " + i);

//                catRepo.save(cat).subscribe();
                entityTemplate.insert(Cat.class).into("cats2").using(cat).subscribe();

                log.info("Cat created!");
                sink.next(i);
            }

            sink.complete();

        });
    }

    private void createCatsClient() {
//        databaseClient.insert().into(MyCat.class).table("cats").using(new MyCat(5, "")).then().subscribe();
        Flux.create(sink -> {
            for (int i = 0; i < 800; i++) {
                var name = "Cat " + i;
                databaseClient.sql("insert into cats (age, name) values (" +
                                ThreadLocalRandom.current().nextInt(1, 10) + ",'" + name + "');")
                        .then().subscribe();
                log.info("Cat created!");
                sink.next(i);
            }
            sink.complete();
        }).doOnNext(System.out::println).subscribe();

        // END

    }

    private void loadDataWithSql(Cat cat) {
        databaseClient.sql("INSERT INTO public.cats2" +
                String.format("(id, \"name\", color, breed, registry, origin, country_codes, wikipedia_url, " +
                                "age, indoor, adaptability, dog_friendly, intelligence, hairless, person_id)\n" +
                                "VALUES(%s, '', '', '', '', '', '', '', %d, %d, %d, 0, 0, 0, 0);",
                        cat.getCatId(),
                        cat.getAge(),
                        cat.getIndoor(),
                        cat.getAdaptability()
                ));
    }

    private int generateRandom(int length) {
        return ThreadLocalRandom.current().nextInt(0, length);
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
