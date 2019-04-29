package cmpe273.group6.Controller;

import cmpe273.group6.Entity.Area;
import cmpe273.group6.Entity.Category;
import cmpe273.group6.Entity.Sprinkler;
import cmpe273.group6.Service.*;
import cmpe273.group6.Service.CategoryRepositoryCustom.CategoryRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/areas")
public class AreaController {

    private AreaRepository areaRepository;

    private CategoryRepository categoryRepository;

    private SensorRepository sensorRepository;

    private SprinklerRepository sprinklerRepository;

    private CameraRepository cameraRepository;

    Set<Long> sensor_used = new HashSet<>();
    Set<Long> sprinkler_used = new HashSet<>();
    Set<Long> camera_used = new HashSet<>();


    public AreaController(AreaRepository areaRepository, CategoryRepository categoryRepository,
                          SensorRepository sensorRepository, SprinklerRepository sprinklerRepository, CameraRepository cameraRepository) {

        this.areaRepository = areaRepository;
        this.categoryRepository = categoryRepository;
        this.sensorRepository = sensorRepository;
        this.sprinklerRepository = sprinklerRepository;
        this.cameraRepository = cameraRepository;
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
        return this.areaRepository.findAreaById(id);
    }

    @PutMapping("/setup/{id}")
    public String setupArea(@PathVariable(value = "id") long areaId, @RequestBody Map<String, String> map) {
        if (areaRepository.findAreaById(areaId) == null) {
            return "This area does not exist.";
        }

        Area area = areaRepository.findAreaById(areaId);
        if (map.containsKey("sprinkler")) {
            if (sprinklerRepository.findSprinklerById(Long.parseLong(map.get("sprinkler"))) != null && !sprinkler_used.contains(Long.parseLong(map.get("sprinkler")))) {
                area.setSprinkler(Long.parseLong(map.get("sprinkler")));
                // sprinkler_used.add(Long.parseLong(map.get("sprinkler")));
            } else {
                return "Please Enter an valid sprinkler id";
            }
        }

        if (map.containsKey("sensor")) {
            if (sensorRepository.findSensorById(Long.parseLong(map.get("sensor"))) != null && !sensor_used.contains(Long.parseLong(map.get("sensor")))) {
                area.setSensor(Long.parseLong(map.get("sensor")));
                // sensor_used.add(Long.parseLong(map.get("sensor")));
            } else {
                return "Please Enter an valid sensor id";
            }
        }

        if (map.containsKey("camera")) {
            if (cameraRepository.findCameraById(Long.parseLong(map.get("camera"))) != null && !camera_used.contains(Long.parseLong(map.get("camera")))) {
                area.setCamera(Long.parseLong(map.get("camera")));
                // camera_used.add(Long.parseLong(map.get("camera")));
            } else {
                return "Please Enter an valid camera id";
            }
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
            return "No such plant category in the database. Please add first. Ignore if you do not want to add.";
        } else {
            Category category = categoryRepository.findCategoryByNameIs(map.get("plant_category"));
            area.setSunlight_threshold(area.getSunlight_threshold() + category.getSunlight() * Integer.parseInt(map.get("plant_num")));
            area.setWater_threshold(area.getWater_threshold() + category.getWater_amount() * Integer.parseInt(map.get("plant_num")));
        }
        this.areaRepository.save(area);
        sprinkler_used.add(Long.parseLong(map.get("sprinkler")));
        sensor_used.add(Long.parseLong(map.get("sensor")));
        camera_used.add(Long.parseLong(map.get("camera")));
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

    @PostMapping("/detect/{id}")
    public String detectPeople(@PathVariable(value = "id") long cameraId, @RequestBody Map<String, String> map) {
        if (cameraRepository.findCameraById(cameraId) == null) {
            return "The camera is not being registered.";
        } else if (areaRepository.findAreaByCameraIs(cameraId) == null) {
            return "The camera is not being added to any area.";
        } else {
            Area area = areaRepository.findAreaByCameraIs(cameraId);
            if (map.containsKey("detect")) {
                // detect people, turn off
                if (Integer.parseInt(map.get("detect")) == 1) {
                    if (sprinklerRepository.findSprinklerById(area.getSprinkler()) == null) {
                        return "There is no sprinkler in this area";
                    }
                    Sprinkler sprinkler = sprinklerRepository.findSprinklerById(area.getSprinkler());
                    sprinkler.setState(0);
                    sprinklerRepository.save(sprinkler);
                    return "Sprinkler turned off. " + sprinkler.getId();
                } else {
                    if (sprinklerRepository.findSprinklerById(area.getSprinkler()) == null) {
                        return "There is no sprinkler in this area";
                    }
                    Sprinkler sprinkler = sprinklerRepository.findSprinklerById(area.getSprinkler());
                    sprinkler.setState(1);
                    sprinklerRepository.save(sprinkler);
                    return "Sprinkler turned on. " + sprinkler.getId();
                }
            } else {
                return "need more information";
            }
        }
    }
}
