package NBlog;

import java.util.Date;
import java.util.List;
import java.util.function.Supplier;

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
@RequestMapping(value = "/blogpost")
public class BlogPostController {

	@Autowired
	BlogPostRepository blogPostRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CommentRepository commentRepository;

	@GetMapping(value = "")
	public List<BlogPost> getAll() {
		return blogPostRepository.findAll();
	}

	@GetMapping(value = "/{id}")
	public BlogPost getBlogPostById(@PathVariable(value = "id") Long id) throws NotFoundException {
		return blogPostRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Blog post with given id not found"));
	}

	@PostMapping(value = "")
	public BlogPost createBlogPost(@RequestBody BlogPost blogPost) throws NotFoundException {

		blogPost.author = userRepository.findById(blogPost.author.userId)
				.orElseThrow(() -> new NotFoundException("User with given id not found"));

		return blogPostRepository.save(blogPost);

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteBlogPost(@PathVariable(value = "id") Long id) throws NotFoundException {

		BlogPost blogPost = blogPostRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Blog post with given id not found"));

		blogPostRepository.delete(blogPost);

		return ResponseEntity.ok().build();

	}

	@PutMapping(value = "/{id}")
	public BlogPost updateBlogPost(@PathVariable(value = "id") Long id, @RequestBody BlogPost blogPost)
			throws NotFoundException {

		BlogPost existingBlogPost = blogPostRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Blog post with given id not found"));

		existingBlogPost.author = userRepository.findById(blogPost.author.userId)
				.orElseThrow(() -> new NotFoundException("User with given id not found"));

		// check if new comments need to be added
		blogPost.comments.forEach((comment) -> {

			try {

				if (existingBlogPost.comments.stream().filter(x -> x.commentId == comment.commentId).findFirst()
						.orElse(null) == null) {
					existingBlogPost.comments.add(commentRepository.findById(comment.commentId)
							.orElseThrow(() -> new NotFoundException("Comment with given id not found")));
				}

			} catch (NotFoundException e) {
				// feeling cute, might add validation later idk
			}

		});

		// check if existing comments need to be removed
//		List<Long> commentsToRemove = null;
//		existingBlogPost.comments.forEach((comment) -> {
//
//			if (blogPost.comments.stream().filter(x -> x.commentId == comment.commentId).findFirst().orElse(null) == null &&
//					commentRepository.findById(comment.commentId).orElse(null) != null) {
//				commentsToRemove.add(comment.commentId);
//			}
//
//		});

		// now remove
//		commentsToRemove.forEach((x) -> {
//			existingBlogPost.comments.removeIf((y) -> y.commentId == x);
//		});

		existingBlogPost.content = blogPost.content;
		existingBlogPost.imageContent = blogPost.imageContent;
		existingBlogPost.updateTimestamp = new Date();

		return blogPostRepository.save(existingBlogPost);
	}

}
