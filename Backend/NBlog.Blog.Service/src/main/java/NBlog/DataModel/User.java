package NBlog.DataModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

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

}
