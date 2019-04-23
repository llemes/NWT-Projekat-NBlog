package NBlog;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class BlogPostRating {

    @Id
    private long blogPostId;
    @Id
    private long userId;
    private String ratingCode;

    protected BlogPostRating() {}
}
