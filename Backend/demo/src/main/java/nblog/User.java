package nblog;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;
    
    private String username;
    private String password;
    private String email;
    private int emailOptOut;
    private Date creationTimestamp;
    private Date updateTimestamp;
    private int isDeleted;

    protected User() {}
    
    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%d, username='%s', email='%s']",
                userId, username, email);
    }
    
    public void setId(Long id) {
    	this.userId = id;
    }
    
    public void setUsername(String username) {
		this.username = username;
	}
    
    public String getUsername() {
		return username;
	}
    
    public void setEmail(String email) {
		this.email = email;
	}
    
    public String getEmail() {
		return email;
	}
}

