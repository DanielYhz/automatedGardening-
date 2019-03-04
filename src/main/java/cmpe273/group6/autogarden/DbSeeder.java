package cmpe273.group6.autogarden;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
// when the spring starts, this line is executed by spring.
public class DbSeeder implements CommandLineRunner {
    // if we want to start the database clean each time,
    // add a reference
    private  AreaRepository areaRepository;

    public DbSeeder(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    @Override
    public void run (String... strings) throws Exception {
        Area area_south = new Area(1, 1, 1);
        Area area_north = new Area(2,2,3);
        Area area_east = new Area(3,4,5);
        Area area_west = new Area(5,6,7);

        // drop all areas.
        this.areaRepository.deleteAll();

        // add our areas to database.
        List<Area> areas = Arrays.asList(area_east, area_north, area_south, area_west);
        this.areaRepository.saveAll(areas);
    }
}
