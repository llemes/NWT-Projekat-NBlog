package NBlog;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.util.Date;
import java.util.List;

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

    @ManyToOne
	@JoinColumn
	public User author;
    
    @OneToMany
    @JoinColumn
    public List<Comment> comments;
    
    protected BlogPost() {}
    
    public BlogPost(String title, String content, String imageContent) {
    	this.title = title;
    	this.content = content;
    	this.imageContent = imageContent;
    	this.creationTimestamp = new Date();
    	this.updateTimestamp = null;
    	this.isDeleted = 0;
    }
}
