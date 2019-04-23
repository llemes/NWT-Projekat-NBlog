package NBlog;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Date;

@Entity
public class BlogPostRating implements Serializable {

	@Id
	public Long blogPostRatingId;
	
    @ManyToOne
    @JoinColumn
    public BlogPost blogPost;
    
    @ManyToOne
    @JoinColumn
    public User user;
    
    @ManyToOne
    @JoinColumn
    public BlogPostRatingOption ratingOption;

    protected BlogPostRating() {}
}
