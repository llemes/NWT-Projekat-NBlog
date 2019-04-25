package nblog;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Autowired
	private RestTemplate restTemplate;
	
	private final UserRepository repository;
	
	UserController(UserRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/users")
	List<User> all() {
		return repository.findAll();
	}

	@PostMapping("/user")
	User addNewUser(@RequestBody User user) {
		
		restTemplate.postForObject("http://localhost:9090/user", user, User.class);
		
		return repository.save(user);
	}
	
	@PutMapping("/users/{id}")
	User editUserById(@RequestBody User newUser, 
			@PathVariable Long id) {

		return repository.findById(id)
				.map(user -> {
					user.setUsername(newUser.getUsername());
					user.setEmail(newUser.getEmail());
					return repository.save(user);
				})
				.orElseGet(() -> {
					return repository.save(newUser);
				});
	}

	// Single item
	@GetMapping("/users/{id}")
	User getUserById(@PathVariable Long id) {
		
		try {
			return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		}
		catch(Exception e) {
			throw new UserNotFoundException(id);
		}	
	}

	@DeleteMapping("/users/{id}")
	void deleteUserById(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
