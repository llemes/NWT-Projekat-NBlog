package NBlog.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import NBlog.DataModel.User;
import NBlog.Repository.UserRepository;

@RestController
// @RequestMapping(path="/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository; 
	
	@RequestMapping("/add") // Map ONLY GET Requests
	public @ResponseBody String addNewUser(@RequestParam String username
			, @RequestParam String email) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		userRepository.save(user);
		return "Saved new user: " + username;
	}

	@RequestMapping("/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}
	
	@RequestMapping("/find")
	public @ResponseBody User findUserByName(@RequestParam String username) {
		return userRepository.findByUsername(username);
	}
	
	@RequestMapping("/delete")
	public @ResponseBody User deleteUser(@RequestParam String username) {
		return userRepository.delete(username);	
	}
}
