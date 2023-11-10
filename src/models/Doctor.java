package models;

import java.util.ArrayList;
import java.util.List;

public class Doctor {

    String name;
    Specialization specialization;
    List< BookedSlot> bookedSlots;
    List<AvailableSlot> availableSlots;

    public String getName() {
        return name;
    }

    public Doctor(String name, Specialization specialization) {
        this.name = name;
        this.specialization = specialization;
        this.bookedSlots = new ArrayList<>();
        this.availableSlots = new ArrayList<>();
    }

    public Doctor(String name, Specialization specialization, List<BookedSlot> bookedSlots, List<AvailableSlot> availableSlots) {
        this.name = name;
        this.specialization = specialization;
        this.bookedSlots = bookedSlots;
        this.availableSlots = availableSlots;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public List<BookedSlot> getBookedSlots() {
        return bookedSlots;
    }

    public void setBookedSlots(List<BookedSlot> bookedSlots) {
        this.bookedSlots = bookedSlots;
    }

    public List<AvailableSlot> getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(List<AvailableSlot> availableSlots) {
        this.availableSlots = availableSlots;
    }
}
