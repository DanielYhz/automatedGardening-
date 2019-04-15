package cmpe273.group6.Entity;

import com.mongodb.internal.connection.Time;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "Sprinkler")
public class Sprinkler {
    @Id
    private long id;
    private Time schedule_start;
    private Time schedule_end;
    private boolean state;

    protected Sprinkler() {

    }

    public Sprinkler(long id, Time schedule_start, Time schedule_end) {
        this.id = id;
        this.schedule_end = schedule_end;
        this.schedule_start = schedule_start;
        this.state = true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Time getSchedule_start() {
        return schedule_start;
    }

    public void setSchedule_start(Time schedule_start) {
        this.schedule_start = schedule_start;
    }

    public Time getSchedule_end() {
        return schedule_end;
    }

    public void setSchedule_end(Time schedule_end) {
        this.schedule_end = schedule_end;
    }

    public boolean isState() {
        return this.state;
    }
}
