package cmpe273.group6.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Document(collection = "Area")
public class Area {
    @Id
    private long id;

    private Map<String, Integer> plant_num;
    private long sprinkler;
    private long camera;
    private long sensor;

    protected Area() {
        this.plant_num = new HashMap<>();
    }

    public Area(long id) {
        this.id = id;
        this.plant_num = new HashMap<>();
    }

    public long getId() {
        return id;
    }

    public Map<String, Integer> getPlant_num() {
        return plant_num;
    }

    public void setPlant_num(Map<String, Integer> plant_num) {
        this.plant_num = plant_num;
    }

    public long getSprinkler() {
        return sprinkler;
    }

    public void setSprinkler(long sprinkler) {
        this.sprinkler = sprinkler;
    }

    public long getCamera() {
        return camera;
    }

    public void setCamera(long camera) {
        this.camera = camera;
    }

    public long getSensor() {
        return sensor;
    }

    public void setSensor(long sensor) {
        this.sensor = sensor;
    }
}
