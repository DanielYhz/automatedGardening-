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
        List<Camera> cameras = this.cameraRepository.findAll();
        return cameras;
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
    public void delete(@PathVariable("id") long id) {
        this.cameraRepository.delete(cameraRepository.findCameraById(id));
    }

    @GetMapping("/{id}")
    public Camera getById(@PathVariable("id") long id) {
        Camera camera = this.cameraRepository.findCameraById(id);
        return camera;
    }

    @PostMapping("/register/{id}")
    public String registerCamera(@PathVariable("id") long id) {
        // 0 Stands for R, 1 Stands for W, 2 Stands for RW
        Camera camera = new Camera(id);

        cameraRepository.save(camera);
        return "Registration Complete";
    }
}
