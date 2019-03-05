package cmpe273.group6.Service;

import cmpe273.group6.Entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category,String> {
    Category findCameraById(String id);
}
