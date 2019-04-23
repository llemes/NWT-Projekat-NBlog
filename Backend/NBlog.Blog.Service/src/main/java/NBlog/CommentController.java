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
@RequestMapping(value = "/comment")
public class CommentController {

	@Autowired
	CommentRepository commentRepository;
	@Autowired
	UserRepository userRepository;
	
	@GetMapping(value = "")
	public List<Comment> getAll() {
		return commentRepository.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public Comment getCommentById(@PathVariable(value = "id") Long id) throws NotFoundException {
		return commentRepository.findById(id).orElseThrow(() -> new NotFoundException("Comment with given id not found"));
	}
	
	@PostMapping(value = "")
	public Comment createComment(@RequestBody Comment comment) throws NotFoundException {
		
		comment.author = userRepository.findById(comment.author.userId).orElseThrow(() -> new NotFoundException("User with given id not found"));
				
		return commentRepository.save(comment);
		
	}
	
	@DeleteMapping(value = "/{id}") 
	public ResponseEntity<?> deleteComment(@PathVariable(value = "id") Long id) {
		
		try {
			
			Comment comment = commentRepository
					.findById(id)
					.orElseThrow(() -> new NotFoundException("Comment with given id not found"));
			
			commentRepository.delete(comment);		

		} catch (NotFoundException e) {

			e.printStackTrace();
			return ResponseEntity.status(503).build();

		}
		
		return ResponseEntity.ok().build();

	}
	
	@PutMapping(value = "/{id}")
	public Comment updateComment(@PathVariable(value = "id") Long id, @RequestBody Comment comment) {
		
		Comment existingComment = new Comment();
		
		try {
			
			existingComment = commentRepository
					.findById(id)
					.orElseThrow(() -> new NotFoundException("Comment with given id not found"));
			
			existingComment.author = userRepository.findById(comment.author.userId).orElseThrow(() -> new NotFoundException("User with given id not found"));
			existingComment.content = comment.content;
			existingComment.updateTimestamp = new Date();
			
			comment = commentRepository.save(existingComment);
			
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		
		return existingComment;
		
	}
	
}
