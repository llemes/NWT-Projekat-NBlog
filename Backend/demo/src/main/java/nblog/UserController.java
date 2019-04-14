package nblog;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	private final UserRepository repository;
	
	UserController(UserRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/users")
	List<User> all() {
		return repository.findAll();
	}

	@PostMapping("/user")
	User addNewUser(@RequestParam String username, @RequestParam String email) {
		return repository.save(new User(username, email));
	}
	
	@PutMapping("/users/{id}")
	User editUserById(@RequestParam String newUsername, @RequestParam String newEmail, 
			@PathVariable Long id) {

		return repository.findById(id)
				.map(user -> {
					user.setUsername(newUsername);
					user.setEmail(newEmail);
					return repository.save(user);
				})
				.orElseGet(() -> {
					User newUser = new User(newUsername, newEmail);
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
