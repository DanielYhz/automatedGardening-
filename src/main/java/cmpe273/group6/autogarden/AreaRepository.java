package cmpe273.group6.autogarden;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends MongoRepository<Area,String> {
     Area findAreaById(String id);

}
