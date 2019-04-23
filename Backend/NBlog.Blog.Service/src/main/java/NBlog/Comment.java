package NBlog;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long commentId;
    public String content;
    public Date creationTimestamp;
    public Date updateTimestamp;
    public int isDeleted;

    protected Comment() {}
}
