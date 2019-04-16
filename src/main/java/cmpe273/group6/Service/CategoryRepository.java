package cmpe273.group6.Service;

import cmpe273.group6.DAL.CategoryDAL;
import cmpe273.group6.Entity.Category;
import cmpe273.group6.Service.CategoryRepositoryCustom.CategoryRepositoryCustom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category,Long>, CategoryDAL {
    Category findCategoryById(long id);
    Category findCategoryByNameIs(String name);
}


