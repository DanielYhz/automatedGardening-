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
    public String insert(@RequestBody Category category) {

        this.categoryRepository.insert(category);
        return "Insert category " + category.getName() + " complete.";
    }

    @PostMapping
    public void update(@RequestBody Category category) {
        this.categoryRepository.save(category);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        this.categoryRepository.delete(categoryRepository.findCategoryById(id));
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable("id") long id) {
        Category category = this.categoryRepository.findCategoryById(id);
        return category;
    }
}
