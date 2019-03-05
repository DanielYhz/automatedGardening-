package cmpe273.group6.Entity;

import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private int room_num;
    private Boolean admin; 

    protected User() {
    }

    public User(String username, String password, int room_num, Boolean admin) {
        this.username = username; 
        this.password = password;
        this.room_num = room_num;
        this.admin = admin;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoom_num() {
        return room_num;
    }

    public void setRoom_num(int room_num) {
        this.room_num = room_num;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
