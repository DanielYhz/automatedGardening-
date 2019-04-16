package cmpe273.group6.Service.CategoryRepositoryCustom;

import cmpe273.group6.Entity.Category;
import cmpe273.group6.Service.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.*;

public class CategoryRepositoryCustomImpl implements CategoryRepositoryCustom{

    private final MongoTemplate mongoTemplate;

    @Autowired
    public CategoryRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Category> query(CategoryDynamicQuery categoryDynamicQuery) {
        final Query query = new Query();
        Criteria criteria = new Criteria();
        if (categoryDynamicQuery.getName() != null) {
            criteria = Criteria.where("name").is(categoryDynamicQuery.getName());
        }
        query.addCriteria(criteria);
        return mongoTemplate.find(query, Category.class);
    }
}
