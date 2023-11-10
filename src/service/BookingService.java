package service;

import dao.Dao;
import dao.Daoimpl;
import models.*;

import java.util.ArrayList;
import java.util.List;
import java.sql.Time;

public class BookingService {

    Dao dao;

    public BookingService(){
        dao = new Daoimpl();
    }

    public void registerDoctor(String name,  Specialization specialization) {
        Doctor doctor = new Doctor(name, specialization);
        dao.registerDoctor(doctor);
        System.out.println("Hey Dr "+ name + " !! Welcome");
    }

    public void registerPatient(String name) {
        Patient patient = new Patient(name);
        dao.registerPatient(patient);
        System.out.println("Hey "+ name + " !! Welcome");
    }

    public void markAvailabilitySlots(String name, List<AvailableSlot> availableSlotsList){

        Boolean DocExist= dao.isDoctorExist(name);

        if(DocExist){
            List<AvailableSlot> validSlots =new ArrayList<>();
            for(AvailableSlot slot: availableSlotsList){

                if(slot.getEndTime().getTime()-slot.getStartTime().getTime() == 60*60*1000){
                    validSlots.add(slot);
                    System.out.println(slot.getStartTime().toString() + " - " + slot.getEndTime().toString() + " is added" );
                }
                else{
                    System.out.println("Hey Dr " + slot.getStartTime().toString() + " - " + slot.getEndTime().toString() + " is not a valid slot" );
                }
            }
           // List<AvailableSlot> validSlots =  availableSlotsList.stream().filter(slot-> slot.getEndTime().getTime()-slot.getStartTime().getTime() == 60*60).collect(Collectors.toList());
            dao.addAvailableSlots(name, validSlots);
        }
        else{
            System.out.println("Hey Doctor " + name + "!! Please register yourself first");
        }

    }

    public void showDoctorAvailabilityBy(Specialization specialization){
        List<Doctor> doctors = dao.showDoctorAvailabilityBy(specialization);

        for(Doctor doctor: doctors){

            doctor.getAvailableSlots().forEach(slot -> {
                System.out.println("Mr " + doctor.getName() + " - " + slot.getStartTime() + " - " + slot.getEndTime());
            });
        }
        int cnt=0;
        for(Doctor doctor: doctors){

            cnt+=doctor.getAvailableSlots().size();
        }

        if(cnt==0){System.out.println("empty");
        }
    }

    public void bookAppointment(String patientName, String doctorName, Time startTime, Boolean... wait){
        int bookingId = dao.bookAppointment(patientName, doctorName, startTime);
       if(bookingId==0){
           System.out.print("can'nt book");
       }
       else{
           System.out.print("your bookng id is " + bookingId);
       }
        System.out.print("\n");
    }

    public void cancelBooking( int bookingId){
        dao.cancelBooking(bookingId);
        System.out.print("Booking Cancelled\n");
    }

    public void showBookedAppointmentForP(String patientName) {
        List<BookedSlot> bookedAppointment= dao.showBookedAppointmentForP(patientName);

        bookedAppointment.forEach(booking-> System.out.println(booking.getStartTime()));
    }

    public void showBookedAppointmentForD(String doctoName) {
        List<BookedSlot> bookedAppointment= dao.showBookedAppointmentForP(doctoName);
        bookedAppointment.forEach(booking-> System.out.println("\n " + booking.getStartTime()));
    }

}

// functionality ( missing )
// exception
// strategy
// tests
