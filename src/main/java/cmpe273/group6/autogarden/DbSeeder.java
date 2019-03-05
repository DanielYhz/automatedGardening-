package cmpe273.group6.autogarden;

import cmpe273.group6.Entity.Area;
import cmpe273.group6.Entity.User;
import cmpe273.group6.Service.AreaRepository;
import cmpe273.group6.Service.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
// when the spring starts, this line is executed by spring.
public class DbSeeder implements CommandLineRunner {
    // if we want to start the database clean each time,
    // add a reference
    private AreaRepository areaRepository;
    private UserRepository userRepository;

    public DbSeeder(AreaRepository areaRepository, UserRepository userRepository) {
        this.areaRepository = areaRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run (String... strings) throws Exception {
        Area area_south = new Area(1, 1, 1);
        Area area_north = new Area(2,2,3);
        Area area_east = new Area(3,4,5);
        Area area_west = new Area(5,6,7);
        User user1 = new User("user", "1234", 1, true);
        // drop all areas.
        this.areaRepository.deleteAll();

        // add our areas to database.
        List<Area> areas = Arrays.asList(area_east, area_north, area_south, area_west);
        this.areaRepository.saveAll(areas);

        // add users to database
        List<User> users = Arrays.asList(user1);
        this.userRepository.saveAll(users);
    }
}
