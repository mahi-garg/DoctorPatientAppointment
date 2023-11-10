package db;

import models.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryDb {

    Map<String, Doctor> doctorDB;
    Map<Specialization, List<Doctor>> specializationDB;
    Map<String, Patient> patientDB;

    Map<Integer, BookedSlot> bookings;

    Map<Integer, WaitingList> waitListDb;

    public Map<Integer, WaitingList> getWaitListDb() {
        return waitListDb;
    }

    private static final InMemoryDb instance = new InMemoryDb();

    public Map<Integer, BookedSlot> getBookings() {
        return bookings;
    }

    public void setBookings(Map<Integer, BookedSlot> bookings) {
        this.bookings = bookings;
    }

    private InMemoryDb(){
        doctorDB = new HashMap<>();
        specializationDB = new HashMap<>();
        patientDB = new HashMap<>();
        bookings = new HashMap<>();
        waitListDb = new HashMap<>();
    }

    public static InMemoryDb getInstance(){
        return instance;
    }


    public Map<String, Doctor> getDoctorDB() {
        return doctorDB;
    }

    public Map<Specialization, List<Doctor>> getSpecializationDB() {
        return specializationDB;
    }

    public Map<String, Patient> getPatientDB() {
        return patientDB;
    }
}
