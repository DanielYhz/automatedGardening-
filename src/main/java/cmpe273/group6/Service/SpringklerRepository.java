package cmpe273.group6.Service;

import cmpe273.group6.Entity.Springkler;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringklerRepository extends MongoRepository<Springkler, String> {
    Springkler findSpringklerById(String id);
}

