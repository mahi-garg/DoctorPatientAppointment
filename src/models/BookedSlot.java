package models;

import java.sql.Time;

public class BookedSlot {

    int bookingId;  // random uuid

    Time startTime;

    String patient;

    String doctor;

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public BookedSlot(int bookingId, Time startTime,String patient, String doctor) {
        this.bookingId = bookingId;
        this.startTime = startTime;

        this.patient = patient;
        this.doctor = doctor;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }
}
