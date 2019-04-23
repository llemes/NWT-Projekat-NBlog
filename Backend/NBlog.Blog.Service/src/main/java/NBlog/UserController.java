package NBlog;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping(value = "")
	public List<User> getAll() {
		return userRepository.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public User getUserById(@PathVariable(value = "id") Long id) throws NotFoundException {
		return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with given id not found"));
	}
	
	@PostMapping(value = "")
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@DeleteMapping(value = "/{id}") 
	public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long id) {
		
		try {
			
			User user = userRepository
					.findById(id)
					.orElseThrow(() -> new NotFoundException("User with given id not found"));
			
			userRepository.delete(user);		

		
		} catch (NotFoundException e) {

			e.printStackTrace();
			return ResponseEntity.status(503).build();

		}
		
		return ResponseEntity.ok().build();

	}
	
	@PutMapping(value = "/{id}")
	public User updateUser(@PathVariable(value = "id") Long id, @RequestBody User user) {
		
		User existingUser = new User();
		
		try {
			
			existingUser = userRepository
					.findById(id)
					.orElseThrow(() -> new NotFoundException("User with given id not found"));
			
			existingUser.username = user.username;
			existingUser.password = user.password;
			existingUser.email = user.email;
			existingUser.emailOptOut = user.emailOptOut;
			existingUser.updateTimestamp = new Date();
			
			user = userRepository.save(existingUser);
			
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		
		return existingUser;
		
	}
	
}
