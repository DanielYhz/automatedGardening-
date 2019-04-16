package cmpe273.group6.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Camera")
public class Camera {
    @Id
    private long id;
    private int access_mode;
    private int state;

    protected Camera() {

    }

    public Camera(long id) {
        this.id = id;
        this.access_mode = 2;
        this.state = 1;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAccess_mode() {
        return access_mode;
    }

    public void setAccess_mode(int access_mode) {
        this.access_mode = access_mode;
    }

    public int isState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
