package cmpe273.group6.DALImpl;

import cmpe273.group6.DAL.CategoryDAL;
import cmpe273.group6.Entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class CategoryDALImpl implements CategoryDAL {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public CategoryDALImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Category findCategoryByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        List<Category> categories = mongoTemplate.find(query, Category.class);
        if (categories.size() == 0) {
            return null;
        }
        return categories.get(0);
    }
}
