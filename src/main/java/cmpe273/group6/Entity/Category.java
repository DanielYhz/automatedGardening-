package cmpe273.group6.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Category")
public class Category {
    @Id
    private String id;
    private String name;
    private int sunlight;
    private int water_amount;

    protected Category() {

    }

    public Category(String id, String name, int sunlight, int water_amount) {
        this.id = id;
        this.name = name;
        this.sunlight = sunlight;
        this.water_amount = water_amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSunlight() {
        return sunlight;
    }

    public void setSunlight(int sunlight) {
        this.sunlight = sunlight;
    }

    public int getWater_amount() {
        return water_amount;
    }

    public void setWater_amount(int water_amount) {
        this.water_amount = water_amount;
    }
}
