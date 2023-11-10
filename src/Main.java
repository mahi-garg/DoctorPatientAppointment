import models.AvailableSlot;
import service.BookingService;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static models.Specialization.Cardiologist;
import static models.Specialization.Dermatologist;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome! Patients and Doctors");

        BookingService bookingService = new BookingService();

        bookingService.registerDoctor("Curious", Cardiologist);

        List<AvailableSlot> availableSlots = new ArrayList<>();
        Time a1 = new Time(9, 0, 0);
        Time a2 = new Time(10, 30, 0);
        AvailableSlot a = new AvailableSlot(a1, a2);
        availableSlots.add(a);

        bookingService.markAvailabilitySlots("Curious", availableSlots);
        availableSlots.clear();

        Time b1 = new Time(11, 0, 0);
        Time b2 = new Time(12, 0, 0);
        a = new AvailableSlot(b1, b2);
        availableSlots.add(a);

        Time c1 = new Time(12, 0, 0);
        Time c2 = new Time(13, 0, 0);
        a = new AvailableSlot(c1, c2);
        availableSlots.add(a);

        Time d1 = new Time(16, 0, 0);
        Time d2 = new Time(17, 0, 0);
        a = new AvailableSlot(d1, d2);
        availableSlots.add(a);
        bookingService.markAvailabilitySlots("Curious", availableSlots);
        availableSlots.clear();


        bookingService.registerDoctor("Dreadful", Dermatologist);

        b1 = new Time(9, 0, 0);
        b2 = new Time(10, 0, 0);
        a = new AvailableSlot(b1, b2);
        availableSlots.add(a);

        c1 = new Time(11, 0, 0);
        c2 = new Time(12, 0, 0);
        a = new AvailableSlot(c1, c2);
        availableSlots.add(a);

        d1 = new Time(13, 0, 0);
        d2 = new Time(14, 0, 0);
        a = new AvailableSlot(d1, d2);
        availableSlots.add(a);
        bookingService.markAvailabilitySlots("Dreadful", availableSlots);
        availableSlots.clear();


        bookingService.showDoctorAvailabilityBy(Dermatologist);
        bookingService.showDoctorAvailabilityBy(Cardiologist);

        //registerPatient ->PatientA

        bookingService.registerPatient("PatientA");

        bookingService.bookAppointment("PatientA", "Curious", new Time(9, 0, 0));
        bookingService.bookAppointment("PatientA", "Dreadful", new Time(9, 0, 0));

        bookingService.showDoctorAvailabilityBy(Dermatologist);
        bookingService.showDoctorAvailabilityBy(Cardiologist);

        bookingService.cancelBooking(1);

        bookingService.showDoctorAvailabilityBy(Dermatologist);

        bookingService.registerPatient("PatientB");
        bookingService.bookAppointment("PatientB", "Curious", new Time(12, 0, 0));

        bookingService.showDoctorAvailabilityBy(Cardiologist);
        bookingService.registerDoctor("Daring", Dermatologist);

        availableSlots.clear();
        c1 = new Time(11, 0, 0);
        c2 = new Time(12, 0, 0);
        a = new AvailableSlot(c1, c2);
        availableSlots.add(a);

        d1 = new Time(14, 0, 0);
        d2 = new Time(15, 0, 0);
        a = new AvailableSlot(d1, d2);
        availableSlots.add(a);
        bookingService.markAvailabilitySlots("Daring", availableSlots);
        availableSlots.clear();

        bookingService.showDoctorAvailabilityBy(Dermatologist);

        bookingService.registerPatient("PatientF");
        bookingService.registerPatient("PatientC");
        bookingService.showDoctorAvailabilityBy(Cardiologist);
        bookingService.bookAppointment("PatientF", "Daring", new Time(11, 0, 0));

        bookingService.bookAppointment("PatientA", "Curious", new Time(12, 0, 0));

        bookingService.bookAppointment("PatientF", "Curious", new Time(9, 0, 0));


        bookingService.bookAppointment("PatientC", "Curious", new Time(16, 0, 0));

        bookingService.showDoctorAvailabilityBy(Cardiologist);
        bookingService.showDoctorAvailabilityBy(Dermatologist);

        bookingService.registerPatient("PatientD");
        bookingService.bookAppointment("PatientC", "Curious", new Time(16,0,0), true);
        bookingService.cancelBooking(4);
//        bookingService.showBookedAppointmentForP("PatientF");
    }
}