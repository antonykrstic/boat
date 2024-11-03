package com.example.antony.loader;

import com.example.antony.model.Boat;
import com.example.antony.repository.BoatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    /**
     * This gets run immediately after the spring boot app is loaded.
     * Could have used data.sql in src/main/resources
     */
    @Bean
    CommandLineRunner initDatabase(final BoatRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Boat("Bilboat Baggins")));
            log.info("Preloading " + repository.save(new Boat("Boaty mc boat face")));
            log.info("Preloading " + repository.save(new Boat("Bridge over troubled water")));
            log.info("Preloading " + repository.save(new Boat("smile and wave boys, smile and wave")));
        };
    }
}
