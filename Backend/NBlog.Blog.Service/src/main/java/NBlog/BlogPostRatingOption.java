package NBlog;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class BlogPostRatingOption {

    @Id
    private String ratingCode;
    private String ratingFullName;

    protected BlogPostRatingOption() {}

}
