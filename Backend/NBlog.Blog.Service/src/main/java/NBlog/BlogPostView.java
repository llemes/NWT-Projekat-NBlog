package NBlog;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class BlogPostView {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long blogPostViewId;
    private Date timestamp;
}
