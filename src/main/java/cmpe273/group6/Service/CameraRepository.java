package cmpe273.group6.Service;

import cmpe273.group6.Entity.Camera;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CameraRepository extends MongoRepository <Camera,String> {
    Camera findCameraById(String id);
}
