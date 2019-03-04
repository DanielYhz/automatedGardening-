package cmpe273.group6.autogarden;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Sensor")
public class Sensor {
    @Id
    private String id;
    private int access_mode;
    private int sunlight;
    private int water_received;
    private boolean state;

    protected Sensor() {

    }

    public Sensor(String id, int access_mode, int sunlight, int water_received) {
        this.id = id;
        this.access_mode = access_mode;
        this.sunlight = sunlight;
        this.water_received = water_received;
        this.state = true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAccess_mode() {
        return access_mode;
    }

    public void setAccess_mode(int access_mode) {
        this.access_mode = access_mode;
    }

    public int getSunlight() {
        return sunlight;
    }

    public void setSunlight(int sunlight) {
        this.sunlight = sunlight;
    }

    public int getWater_received() {
        return water_received;
    }

    public void setWater_received(int water_received) {
        this.water_received = water_received;
    }

    public boolean isState() {
        return this.state;
    }
}
