package NBlog;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long userId;
    public String username;
    public String password;
    public String email;
    public int emailOptOut;
    public Date creationTimestamp;
    public Date updateTimestamp;
    public int isDeleted;

    protected User() {}

    public User(String username, String password, String email) {
    	this.username = username;
    	this.password = password;
    	this.email = email;
    	this.emailOptOut = 0;
    	this.creationTimestamp = new Date();
    	this.updateTimestamp = null;
    	this.isDeleted = 0;    	
    }
    
}
