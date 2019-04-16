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
    private int  water_threshold;
    private int  sunlight_threshold;
    private int  water_cur;
    private int  sunlight_cur;

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

    public int getWater_threshold() {
        return water_threshold;
    }

    public void setWater_threshold(int water_threshold) {
        this.water_threshold = water_threshold;
    }

    public int getSunlight_threshold() {
        return sunlight_threshold;
    }

    public void setSunlight_threshold(int sunlight_threshold) {
        this.sunlight_threshold = sunlight_threshold;
    }

    public int getWater_cur() {
        return water_cur;
    }

    public void setWater_cur(int water_cur) {
        this.water_cur = water_cur;
    }

    public int getSunlgiht_cur() {
        return sunlight_cur;
    }

    public void setSunlgiht_cur(int sunlgiht_cur) {
        this.sunlight_cur = sunlgiht_cur;
    }
}
