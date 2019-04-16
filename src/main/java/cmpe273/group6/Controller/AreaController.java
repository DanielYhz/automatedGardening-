package cmpe273.group6.Controller;

import cmpe273.group6.Entity.Area;
import cmpe273.group6.Entity.Category;
import cmpe273.group6.Entity.Sprinkler;
import cmpe273.group6.Service.AreaRepository;
import cmpe273.group6.Service.CategoryRepository;
import cmpe273.group6.Service.SensorRepository;
import cmpe273.group6.Service.SprinklerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/areas")
public class AreaController {

    private AreaRepository areaRepository;

    private CategoryRepository categoryRepository;

    private SensorRepository sensorRepository;

    private SprinklerRepository sprinklerRepository;

    public AreaController(AreaRepository areaRepository, CategoryRepository categoryRepository, SensorRepository sensorRepository, SprinklerRepository sprinklerRepository) {

        this.areaRepository = areaRepository;
        this.categoryRepository = categoryRepository;
        this.sensorRepository = sensorRepository;
        this.sprinklerRepository = sprinklerRepository;
    }


    @GetMapping("/all")
    public List<Area> getAll() {
        List<Area> areas = this.areaRepository.findAll();
        return areas;
    }

    @PutMapping
    public String insert(@RequestBody Area area) {

        this.areaRepository.insert(area);
        return "Insert complete";
    }

    @PostMapping
    public void update(@RequestBody Area area) {
        this.areaRepository.save(area);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        this.areaRepository.delete(areaRepository.findAreaById(id));
    }

    @GetMapping("/{id}")
    public Area getById(@PathVariable("id") long id) {
        Area area = this.areaRepository.findAreaById(id);
        return area;
    }

    @PutMapping("/setup/{id}")
    public String setupArea(@PathVariable(value = "id") long areaId, @RequestBody Map<String, String> map) {
        if (areaRepository.findAreaById(areaId) == null) {
            return "This area does not exist.";
        }

        Area area = areaRepository.findAreaById(areaId);
        if (map.containsKey("sprinkler")) {
            area.setSprinkler(Long.parseLong(map.get("sprinkler")));
        }

        if (map.containsKey("sensor")) {
            area.setSensor(Long.parseLong(map.get("sensor")));
        }

        if (map.containsKey("camera")) {
            area.setCamera(Long.parseLong(map.get("camera")));
        }

        if (map.containsKey("plant_category")) {
            if (!area.getPlant_num().keySet().contains(map.get("plant_category")) && !map.containsKey("plant_num")) {
                area.getPlant_num().put(map.get("plant_category"), 0);
            } else {
                area.getPlant_num().put(map.get("plant_category"), Integer.parseInt(map.get("plant_num")));
            }
        }

        // since we will only update one category at a time. there will be only one type of plant added here.
        if (categoryRepository.findCategoryByNameIs(map.get("plant_category")) == null) {
            return "No such category in the database. Please add first.";
        } else {
            Category category = categoryRepository.findCategoryByNameIs(map.get("plant_category"));
            area.setSunlight_threshold(area.getSunlight_threshold() + category.getSunlight() * Integer.parseInt(map.get("plant_num")));
            area.setWater_threshold(area.getWater_threshold() + category.getWater_amount() * Integer.parseInt(map.get("plant_num")));
        }
        this.areaRepository.save(area);
        return "Information updated on the Area " + areaId + " succeed.";
    }

    @PostMapping("/check/{id}")
    public String checkArea(@PathVariable(value = "id") long sensorId, @RequestBody Map<String, String> map) {

        if (sensorRepository.findSensorById(sensorId) == null) {
            return "The sensor is not being registered.";
        } else if (areaRepository.findAreaBySensorIs(sensorId) == null) {
            return "The sensor is not being added to any area.";
        } else {
            Area area = areaRepository.findAreaBySensorIs(sensorId);
            if (map.containsKey("water_received")) {
                area.setWater_cur(Integer.parseInt(map.get("water_received")));
            }
            if (map.containsKey("sunlight")) {
                area.setSunlgiht_cur(Integer.parseInt(map.get("sunlight")));
            }
            // if water received in the plant is enough, turn off the sprinkler.
            // set the sprinkler to turn off.
            if (area.getWater_cur() >= area.getWater_threshold()) {
                if (sprinklerRepository.findSprinklerById(area.getSprinkler()) == null) {
                    return "There is no sprinkler in this area.";
                }
                Sprinkler sprinkler = sprinklerRepository.findSprinklerById(area.getSprinkler());
                sprinkler.setState(0);
                sprinklerRepository.save(sprinkler);
                return "Sprinkler turned off. " + sprinkler.getId();
            } else {
                return "water still needed";
            }
        }
    }
}
