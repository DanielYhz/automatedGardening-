package cmpe273.group6.Service.CategoryRepositoryCustom;

public class CategoryDynamicQuery {

    private String name;
    private int sunlight;
    private int water_amount;

    public CategoryDynamicQuery(String name, int sunlight, int water_amount) {
        this.name = name;
        this.sunlight = sunlight;
        this.water_amount = water_amount;
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
