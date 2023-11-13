package models;

import java.util.ArrayList;
import java.util.List;

public class Patient {

    String name;
    List<BookedSlot> bookedAppointments;

    public Patient(String name) {
        this.name = name;
        bookedAppointments = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookedSlot> getBookedAppointments() {
        return bookedAppointments;
    }

    public void setBookedAppointments(List<BookedSlot> bookedAppointments) {
        this.bookedAppointments = bookedAppointments;
    }

}
