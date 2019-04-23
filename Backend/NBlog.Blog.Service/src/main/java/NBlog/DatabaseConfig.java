package NBlog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

	@Bean
	CommandLineRunner initDatabase(UserRepository repository, 
			BlogPostRatingOptionRepository bproRepository) {

		return args -> {

			bproRepository.save(new BlogPostRatingOption("UP", "Upvote"));
			bproRepository.save(new BlogPostRatingOption("DWN", "Downvote"));

			repository.save(new User("user1", "pass", "neki@email.ba"));
			repository.save(new User("user2", "pass", "ohoho@email.ba"));
			repository.save(new User("user3", "pass", "jajaj@email.ba"));
			repository.save(new User("user4", "pass", "trolol@email.ba"));
			repository.save(new User("user5", "pass", "hnj@email.ba"));

		};
	}
}
