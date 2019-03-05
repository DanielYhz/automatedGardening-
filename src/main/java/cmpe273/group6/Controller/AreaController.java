package cmpe273.group6.Controller;

import cmpe273.group6.Entity.Area;
import cmpe273.group6.Service.AreaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/areas")
public class AreaController {

    private AreaRepository areaRepository;

    public AreaController(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    @GetMapping("/all")
    public List<Area> getAll() {
        List<Area> areas = this.areaRepository.findAll();
        return areas;
    }

    @PutMapping
    public void insert(@RequestBody Area area) {
        this.areaRepository.insert(area);
    }

    @PostMapping
    public void update(@RequestBody Area area) {
        this.areaRepository.save(area);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        this.areaRepository.delete(areaRepository.findAreaById(id));
    }

    @GetMapping("/{id}")
    public Area getById(@PathVariable("id") String id) {
        Area area = this.areaRepository.findAreaById(id);
        return area;
    }
}