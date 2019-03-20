package cmpe273.group6.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Sensor")
public class Sensor {
    @Id
    private long id;
    private int access_mode;
    private boolean state;

    protected Sensor() {

    }

    public Sensor(long id, int access_mode) {
        this.id = id;
        this.access_mode = access_mode;
        this.state = true;
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
}
