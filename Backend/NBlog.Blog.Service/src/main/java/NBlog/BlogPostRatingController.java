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

import ch.qos.logback.core.joran.spi.ActionException;

import org.springframework.web.bind.annotation.RequestBody;

import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/blogpost/rating")
public class BlogPostRatingController {

	@Autowired
	BlogPostRepository blogPostRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	BlogPostRatingRepository ratingRepository;
	@Autowired
	BlogPostRatingOptionRepository ratingOptionRepository;

	@GetMapping(value = "/{id}")
	BlogPostRatingPublic getBlogPostRating(@PathVariable(value = "id") Long id) {

		BlogPostRatingPublic ratingFinal = new BlogPostRatingPublic();

		ratingFinal.blogPostId = id;
		ratingFinal.upvotes = (int) ratingRepository.findAll().stream().filter((x) -> {
			return x.blogPost.blogPostId == id && x.ratingOption.ratingCode == "UP";
		}).count();
		ratingFinal.downvotes = (int) ratingRepository.findAll().stream().filter((x) -> {
			return x.blogPost.blogPostId == id && x.ratingOption.ratingCode == "DWN";
		}).count();

		return ratingFinal;

	}

	@PostMapping(value = "")
	BlogPostRating addBlogPostRating(@RequestBody BlogPostRating rating) throws Exception {

		rating.user = userRepository.findById(rating.user.userId)
				.orElseThrow(() -> new NotFoundException("User with provided id not found"));
		rating.blogPost = blogPostRepository.findById(rating.blogPost.blogPostId)
				.orElseThrow(() -> new NotFoundException("Blog post with provided id not found"));
		rating.ratingOption = ratingOptionRepository.findAll().stream().filter((x) -> {
			return x.ratingCode == rating.ratingOption.ratingCode;
		}).findFirst().orElse(null);

		if (rating.ratingOption == null)
			throw new NotFoundException("Invalid rating option");

		// only insert new option if the user/post is a unique combination
		if(ratingRepository.findAll().stream().filter(x -> {
			return x.user.userId == rating.user.userId && x.blogPost.blogPostId == rating.blogPost.blogPostId;
		}).count() != 0) {
			
			throw new Exception("Rating already submitted");
			
		}

		return ratingRepository.save(rating);
	}
	
	@DeleteMapping(value = "")
	public ResponseEntity<?> deleteBlogPostRating(@RequestBody BlogPostRating rating) throws Exception {
		
		BlogPostRating deleteRating = ratingRepository.findById(rating.blogPostRatingId).orElseThrow(() -> new NotFoundException("Rating not found"));

		ratingRepository.delete(deleteRating);
		
		return ResponseEntity.ok().build();
		
	}
}
