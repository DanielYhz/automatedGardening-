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
    private int state;
    private int access_mode;
    private int observe;

    protected Sprinkler() {

    }

    public Sprinkler(long id) {
        this.id = id;
        this.state = 1;
        this.access_mode = 2;
        this.observe = 0;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getAccess_mode() {
        return access_mode;
    }

    public void setAccess_mode(int access_mode) {
        this.access_mode = access_mode;
    }

    public int getObeserve() {
        return observe;
    }

    public void setObeserve(int obeserve) {
        this.observe = obeserve;
    }
}
