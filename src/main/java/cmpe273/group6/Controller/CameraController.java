package cmpe273.group6.Controller;

import cmpe273.group6.Entity.Camera;
import cmpe273.group6.Service.CameraRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cameras")
public class CameraController {
    private CameraRepository cameraRepository;

    public CameraController(CameraRepository cameraRepository) {
        this.cameraRepository = cameraRepository;
    }

    @GetMapping("/all")
    public List<Camera> getAll() {
        List<Camera> sensors = this.cameraRepository.findAll();
        return sensors;
    }

    @PutMapping
    public void insert(@RequestBody Camera camera) {
        this.cameraRepository.insert(camera);
    }

    @PostMapping
    public void update(@RequestBody Camera camera) {
        this.cameraRepository.save(camera);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        this.cameraRepository.delete(cameraRepository.findCameraById(id));
    }

    @GetMapping("/{id}")
    public Camera getById(@PathVariable("id") String id) {
        Camera camera = this.cameraRepository.findCameraById(id);
        return camera;
    }
}
