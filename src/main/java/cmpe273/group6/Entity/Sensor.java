package cmpe273.group6.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Sensor")
public class Sensor {
    @Id
    private long id;
    private int access_mode;
    private boolean state;
    private boolean observe;

    protected Sensor() {

    }

    public Sensor(long id) {
        this.id = id;

        // Access Mode: 0 for read, 1 for write, 2 for read and write.
        // Default is 2;
        this.access_mode = 2;
        this.state = true;
        this.observe = false;
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

    public boolean isState() {
        return this.state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean getState() {
        return this.state;
    }

    public boolean isObserve() {
        return observe;
    }

    public void setObserve(boolean observe) {
        this.observe = observe;
    }
}
