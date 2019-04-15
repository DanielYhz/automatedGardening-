package cmpe273.group6.Controller;

import cmpe273.group6.Entity.Sprinkler;
import cmpe273.group6.Service.SprinklerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void delete(@PathVariable("id") String id) {
        this.sprinklerRepository.delete(sprinklerRepository.findSprinklerById(id));
    }

    @GetMapping("/{id}")
    public Sprinkler getById(@PathVariable("id") String id) {
        Sprinkler sprinkler = this.sprinklerRepository.findSprinklerById(id);
        return sprinkler;
    }


}
