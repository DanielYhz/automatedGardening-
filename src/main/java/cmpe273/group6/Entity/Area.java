package cmpe273.group6.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Area")
public class Area {
    @Id
    private String id;
    private int[] plant;
    private int sprinkler;
    private int camera;
    private int sensor;

    protected Area() {
        // plant contains: category (int),
        // number of plant in this area.
        this.plant = new int[2];
    }

    public Area(int sprinkler, int camera, int sensor) {
        this.sprinkler = sprinkler;
        this.camera = camera;
        this.sensor = sensor;
    }

    public int[] getPlant() {
        return plant;
    }

    public void setPlant(int[] plant) {
        this.plant = plant;
    }

    public int getSprinkler() {
        return sprinkler;
    }

    public void setSprinkler(int sprinkler) {
        this.sprinkler = sprinkler;
    }

    public int getCamera() {
        return camera;
    }

    public void setCamera(int camera) {
        this.camera = camera;
    }

    public int getSensor() {
        return sensor;
    }

    public void setSensor(int sensor) {
        this.sensor = sensor;
    }
}
