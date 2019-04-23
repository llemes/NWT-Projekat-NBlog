package NBlog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {

        return args -> {
            //log.info("Preloading " + repository.save(new User("Bilbo Baggins", "fat@gmail.com")));
            //log.info("Preloading " + repository.save(new User("Frodo Baggins", "stupid@gmail.com")));
            //repository.save(new User("Loki Laufeyson", "jebaited@gmail.com"));
            //repository.save(new User("Carol Danvers", "avenger@gmail.com"));
            //repository.save(new User("Peter Parker", "homework@gmail.com"));
            //repository.save(new User("Groot", "iamgroot@gmail.com"));
        };
    }
}

