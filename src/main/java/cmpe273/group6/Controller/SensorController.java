package cmpe273.group6.Controller;

import cmpe273.group6.Entity.Sensor;
import cmpe273.group6.Service.SensorRepository;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

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
    public String delete(@PathVariable("id") long id) {
        if (sensorRepository.findSensorById(id) == null) {
            return "The device is not registered.";
        }

        this.sensorRepository.delete(sensorRepository.findSensorById(id));
        return "Delete Succeed";
    }

    @GetMapping("/{id}")
    public Sensor getById(@PathVariable("id") long id) {
        Sensor sensor = this.sensorRepository.findSensorById(id);
        return sensor;
    }

    @PostMapping("/register/{id}")
    public String registerSensor(@PathVariable("id") long id) {
        // 0 Stands for R, 1 Stands for W, 2 Stands for RW
        Sensor sensor = new Sensor(id);

        sensorRepository.save(sensor);
        return "Registration Complete " + id;
    }

    @PostMapping("/deregistration/{id}")
    public String deRegisterSensor(@PathVariable("id") long id) {

        this.sensorRepository.delete(sensorRepository.findSensorById(id));
        return "Deregister successfully";
    }

    @PostMapping("/update/{id}")
    public String updateSensor(@PathVariable("id") long id, @RequestBody Map<String, String> map) {
        if (sensorRepository.findSensorById(id) == null) {
            return "The device is not being registered, please check!";
        }

        Sensor sensor = sensorRepository.findSensorById(id);
        if (map.containsKey("sensor_id")) {
            sensor.setId(Long.parseLong(map.get("sensor_id")));
        }

        if (map.containsKey("state")) {
            sensor.setState(Integer.parseInt(map.get("state")));
        }

        if (map.containsKey("access_mode")) {
            sensor.setAccess_mode(Integer.parseInt(map.get("access_mode")));
        }

        this.sensorRepository.save(sensor);
        return "Update succeed!";
    }

    // observation mode on/off
    @PostMapping("/observe/{id}")
    public String observe(@PathVariable(value = "id") long sensorId, @RequestBody Map<String, String> map) {
        if (sensorRepository.findSensorById(sensorId) == null) {
            return "The device is not being registered, please check!";
        }

        Sensor sensor = sensorRepository.findSensorById(sensorId);
        if (map.containsKey("observe")) {
            sensor.setObserve(Integer.parseInt(map.get("observe")));
        }
        this.sensorRepository.save(sensor);
        if (Boolean.parseBoolean(map.get("observe"))) {
            return "Observe on";
        } else {
            return "Observe off";
        }
    }

    // get the notification from the device
    @PostMapping("/notify/{id}")
    public String notify(@PathVariable(value = "id") long sensorId, @RequestBody Map<String, String> map) {
        if (sensorRepository.findSensorById(sensorId) == null) {
            return "The device is not being registered, please check!";
        }

        Sensor sensor = sensorRepository.findSensorById(sensorId);
        if (map.containsKey("message")) {
            System.out.println(map.get("message"));
        }
        return "Notified server";
    }
}



