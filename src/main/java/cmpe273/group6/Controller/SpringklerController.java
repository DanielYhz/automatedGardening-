package cmpe273.group6.Controller;

import cmpe273.group6.Entity.Springkler;
import cmpe273.group6.Service.SpringklerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/springklers")
public class SpringklerController {
    private SpringklerRepository springklerRepository;

    public SpringklerController(SpringklerRepository springklerRepository) {
        this.springklerRepository = springklerRepository;
    }

    @GetMapping("/all")
    public List<Springkler> getAll() {
        List<Springkler> sensors = this.springklerRepository.findAll();
        return sensors;
    }

    @PutMapping
    public void insert(@RequestBody Springkler springkler) {
        this.springklerRepository.insert(springkler);
    }

    @PostMapping
    public void update(@RequestBody Springkler springkler) {
        this.springklerRepository.save(springkler);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        this.springklerRepository.delete(springklerRepository.findSpringklerById(id));
    }

    @GetMapping("/{id}")
    public Springkler getById(@PathVariable("id") String id) {
        Springkler springkler = this.springklerRepository.findSpringklerById(id);
        return springkler;
    }
}
