import java.security.SecureRandom;

public class CatFact {
    private String id;
    private String text;
    private String type;
    private String user;
    private Integer upvotes;


    public Integer getUpvotes() {
        return upvotes;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }

    public String getUser() {
        return user;
    }

    public void setId() {
        this.id = id;
    }

    public void setText() {
        this.text = text;
    }

    public void setType() {
        this.type = type;
    }

    public void setUser() {
        this.user = user;
    }

    public void setUpvotes() {
        this.upvotes = upvotes;
    }
}
