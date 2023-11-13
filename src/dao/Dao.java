package dao;

import models.*;

import java.sql.Time;
import java.util.List;

public interface Dao {

    void registerDoctor(Doctor doctor);

    void addAvailableSlot(String name, AvailableSlot availableSlot);

    void registerPatient(Patient patient);

    List<Doctor> showDoctorAvailabilityBy(Specialization specialization);

    Integer bookAppointment(String patientName , String doctorName, Time startTime);
    int addToWaitingList(Time startTime, String patientName, String doctorName);

    Boolean checkBookingId(int bookingId);
    void cancelBooking(int bookingId);

    List<BookedSlot> showBookedAppointmentForP(String patientName);
    List<BookedSlot> showBookedAppointmentForD(String doctorName);
     Boolean isDoctorExist(String name);
     Boolean isDoctorAvailable(String doctorName, Time startTime);
     Boolean isPatientAvailable(String patientName, Time startTime);

     Boolean doctorAlreadyRegistered(String name);
     Boolean patientAlreadyRegistered(String name);
}
