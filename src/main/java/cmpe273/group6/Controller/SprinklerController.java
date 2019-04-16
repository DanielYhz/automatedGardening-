package cmpe273.group6.Controller;

import cmpe273.group6.Entity.Sprinkler;
import cmpe273.group6.Service.SprinklerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sprinklers")
public class SprinklerController {
    private SprinklerRepository sprinklerRepository;

    public SprinklerController(SprinklerRepository sprinklerRepository) {
        this.sprinklerRepository = sprinklerRepository;
    }

    @GetMapping("/all")
    public List<Sprinkler> getAll() {
        List<Sprinkler> sprinklers = this.sprinklerRepository.findAll();
        return sprinklers;
    }

    @PutMapping
    public void insert(@RequestBody Sprinkler sprinkler) {
        this.sprinklerRepository.insert(sprinkler);
    }

    @PostMapping
    public void update(@RequestBody Sprinkler sprinkler) {
        this.sprinklerRepository.save(sprinkler);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        this.sprinklerRepository.delete(sprinklerRepository.findSprinklerById(id));
    }

    @GetMapping("/{id}")
    public Sprinkler getById(@PathVariable("id") long id) {
        Sprinkler sprinkler = this.sprinklerRepository.findSprinklerById(id);
        return sprinkler;
    }

    @PostMapping("/register/{id}")
    public String registerSprinkler(@PathVariable("id") long id) {
        // 0 Stands for R, 1 Stands for W, 2 Stands for RW
        Sprinkler sprinkler = new Sprinkler(id);

        sprinklerRepository.save(sprinkler);
        return "Registration Complete";
    }

    @PostMapping("/deregistration/{id}")
    public String deRegisterSprinkler(@PathVariable("id") long id) {

        this.sprinklerRepository.delete(sprinklerRepository.findSprinklerById(id));
        return "Deregister successfully";
    }

    @PostMapping("/update/{id}")
    public String updateSprinkler(@PathVariable("id") long id, @RequestBody Map<String, String> map) {
        if (sprinklerRepository.findSprinklerById(id) == null) {
            return "The device is not being registered, please check!";
        }

        Sprinkler sprinkler = sprinklerRepository.findSprinklerById(id);
        if (map.containsKey("sprinkler_id")) {
            sprinkler.setId(Long.parseLong(map.get("sprinkler_id")));
        }

        if (map.containsKey("state")) {
            sprinkler.setState(Integer.parseInt(map.get("state")));
        }

        if (map.containsKey("access_mode")) {
            sprinkler.setAccess_mode(Integer.parseInt(map.get("access_mode")));
        }

        this.sprinklerRepository.save(sprinkler);
        return "Update succeed!";
    }

    // observation mode on/off
    @PostMapping("/observe/{id}")
    public String observe(@PathVariable(value = "id") long sprinklerId, @RequestBody Map<String, String> map) {
        if (sprinklerRepository.findSprinklerById(sprinklerId) == null) {
            return "The device is not being registered, please check!";
        }

        Sprinkler sprinkler = sprinklerRepository.findSprinklerById(sprinklerId);
        if (map.containsKey("observe")) {
            sprinkler.setObeserve(Integer.parseInt(map.get("observe")));
        }
        this.sprinklerRepository.save(sprinkler);
        if (Integer.parseInt(map.get("observe")) == 1) {
            return "Observe on";
        } else {
            return "Observe off";
        }
    }

    // get the notification from the device
    @PostMapping("/notify/{id}")
    public String notify(@PathVariable(value = "id") long sprinklerId, @RequestBody Map<String, String> map) {
        if (sprinklerRepository.findSprinklerById(sprinklerId) == null) {
            return "The device is not being registered, please check!";
        }

        Sprinkler sprinkler = sprinklerRepository.findSprinklerById(sprinklerId);
        if (map.containsKey("message")) {
            System.out.println(map.get("message"));
        }
        return "Notified server";
    }

}
