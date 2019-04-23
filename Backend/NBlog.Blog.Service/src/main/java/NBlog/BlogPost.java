package NBlog;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long blogPostId;
    public String title;
    public String content;
    public String imageContent;
    public Date creationTimestamp;
    public Date updateTimestamp;
    public int isDeleted;

    protected BlogPost() {}
}
