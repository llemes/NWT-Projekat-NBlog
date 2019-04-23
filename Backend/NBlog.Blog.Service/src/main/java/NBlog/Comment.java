package NBlog;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
    
    @ManyToOne
	@JoinColumn
	public User author;

    protected Comment() {}
    
    public Comment(String content) {
    	this.content = content;
    	this.creationTimestamp = new Date();
    	this.updateTimestamp = null;
    	this.isDeleted = 0;
    }
}
