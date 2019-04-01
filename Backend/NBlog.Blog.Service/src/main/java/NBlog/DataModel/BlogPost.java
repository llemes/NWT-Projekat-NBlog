package NBlog.DataModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long blogPostId;
    private String title;
    private String content;
    private String imageContent;
    private Date creationTimestamp;
    private Date updateTimestamp;
    private int isDeleted;

    protected BlogPost() {}
}
