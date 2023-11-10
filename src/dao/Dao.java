package dao;

import db.InMemoryDb;
import models.*;

import java.sql.Time;
import java.util.List;

public interface Dao {

    void registerDoctor(Doctor doctor);

    void addAvailableSlots(String name, List<AvailableSlot> availableSlotsList);

    void registerPatient(Patient patient);

    List<Doctor> showDoctorAvailabilityBy(Specialization specialization);

    Integer bookAppointment(String patientName , String doctorName, Time startTime);

    void cancelBooking(int bookingId);

    List<BookedSlot> showBookedAppointmentForP(String patientName);
    List<BookedSlot> showBookedAppointmentForD(String doctorName);
    public Boolean isDoctorExist(String name);
    public Boolean isDoctorAvailable(String doctorName, Time startTime);
}
