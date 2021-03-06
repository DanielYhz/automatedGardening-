package cmpe273.group6.Service;

import cmpe273.group6.Entity.Area;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface AreaRepository extends MongoRepository<Area,Long> {
     Area findAreaById(long id);
     Area findAreaBySensorIs(long id);
     Area findAreaByCameraIs(long id);
}
