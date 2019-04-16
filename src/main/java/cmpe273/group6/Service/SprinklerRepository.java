package cmpe273.group6.Service;

import cmpe273.group6.Entity.Sprinkler;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprinklerRepository extends MongoRepository<Sprinkler, Long> {
    Sprinkler findSprinklerById(long id);
}

