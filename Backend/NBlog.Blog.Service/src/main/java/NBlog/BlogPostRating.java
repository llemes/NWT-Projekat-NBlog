package NBlog;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.io.Serializable;
import java.util.Date;

@Entity
public class BlogPostRating implements Serializable {

    @Id
    public long blogPostId;
    @Id
    public long userId;
    public String ratingCode;

    protected BlogPostRating() {}
}
