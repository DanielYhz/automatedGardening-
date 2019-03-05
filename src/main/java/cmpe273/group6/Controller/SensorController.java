package cmpe273.group6.Controller;

import cmpe273.group6.Entity.Sensor;
import cmpe273.group6.Service.SensorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private SensorRepository sensorRepository;

    public SensorController(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @GetMapping("/all")
    public List<Sensor> getAll() {
        List<Sensor> sensors = this.sensorRepository.findAll();
        return sensors;
    }

    @PutMapping
    public void insert(@RequestBody Sensor sensor) {
        this.sensorRepository.insert(sensor);
    }

    @PostMapping
    public void update(@RequestBody Sensor sensor) {
        this.sensorRepository.save(sensor);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        this.sensorRepository.delete(sensorRepository.findSensorById(id));
    }

    @GetMapping("/{id}")
    public Sensor getById(@PathVariable("id") String id) {
        Sensor sensor = this.sensorRepository.findSensorById(id);
        return sensor;
    }

}



