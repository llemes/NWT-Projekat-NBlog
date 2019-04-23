package NBlog;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class BlogPostRatingOption {

    @Id
    public String ratingCode;
    public String ratingFullName;

    protected BlogPostRatingOption() {}
    
    public BlogPostRatingOption(String ratingCode, String ratingFullName) {
    	this.ratingCode = ratingCode;
    	this.ratingFullName = ratingFullName;
    }

}
