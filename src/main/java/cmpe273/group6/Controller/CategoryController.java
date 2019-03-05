package cmpe273.group6.Controller;

import cmpe273.group6.Entity.Category;
import cmpe273.group6.Service.CategoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/all")
    public List<Category> getAll() {
        List<Category> category = this.categoryRepository.findAll();
        return category;
    }

    @PutMapping
    public void insert(@RequestBody Category category) {
        this.categoryRepository.insert(category);
    }

    @PostMapping
    public void update(@RequestBody Category category) {
        this.categoryRepository.save(category);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        this.categoryRepository.delete(categoryRepository.findCameraById(id));
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable("id") String id) {
        Category category = this.categoryRepository.findCameraById(id);
        return category;
    }
}
