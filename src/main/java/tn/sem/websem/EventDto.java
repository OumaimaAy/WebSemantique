package tn.sem.websem;

import java.time.LocalDateTime;
import java.util.Date;

public class EventDto {
    private String title;
    private String description ;
    private LocalDateTime date;
    private String location;
    private String organizer;
    private int max_participants;
    private String image;
    private String event;
    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public int getMax_participants() {
        return max_participants;
    }

    public void setMax_participants(int max_participants) {
        this.max_participants = max_participants;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
