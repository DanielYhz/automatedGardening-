package cmpe273.group6.Service;

import cmpe273.group6.Entity.Sensor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends MongoRepository <Sensor, Long> {
        Sensor findSensorById(long id);
}




