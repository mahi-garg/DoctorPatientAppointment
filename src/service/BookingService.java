package service;

import dao.Dao;
import dao.Daoimpl;
import models.*;
import java.util.List;
import java.sql.Time;

public class BookingService {

    Dao dao;

    public BookingService(){
        dao = new Daoimpl();
    }

    public void registerDoctor(String name,  Specialization specialization) throws DoctorPatientException {

        if(dao.doctorAlreadyRegistered(name)){

            throw new DoctorPatientException("doctor already exist");

//            System.out.println("Hey Dr "+ name + " !! you are already registered");
//            return;
        }
        Doctor doctor = new Doctor(name, specialization);
        dao.registerDoctor(doctor);
        System.out.println("Hey Dr "+ name + " !! Welcome");
    }

    public void registerPatient(String name) {
        if(dao.patientAlreadyRegistered(name)){
            System.out.println("Hey Patient "+ name + " !! you are already registered");
            return;
        }
        Patient patient = new Patient(name);
        dao.registerPatient(patient);
        System.out.println("Hey "+ name + " !! Welcome");
    }

    public void markAvailabilitySlots(String name, List<AvailableSlot> availableSlotsList){

        Boolean DocExist= dao.isDoctorExist(name);

        if(DocExist){
            for(AvailableSlot slot: availableSlotsList){

                if(slot.getEndTime().getTime()-slot.getStartTime().getTime() == 60*60*1000 ){

                    if(dao.isDoctorAvailable(name, slot.getStartTime())) {
                        System.out.println(slot.getStartTime().toString() + " - " + slot.getEndTime().toString() + " is already added");
                    }
                    else{

                        dao.addAvailableSlot(name, slot);
                        System.out.println(slot.getStartTime().toString() + " - " + slot.getEndTime().toString() + " is added");
                    }
                }
                else{
                    System.out.println("Hey Dr " + slot.getStartTime().toString() + " - " + slot.getEndTime().toString() + " is not a valid slot" );
                }
            }
        }
        else{
            System.out.println("Hey Doctor " + name + "!! Please register yourself first");
        }

    }

    public void showDoctorAvailabilityBy(Specialization specialization){
        List<Doctor> doctors = dao.showDoctorAvailabilityBy(specialization);

        if(doctors == null){
            System.out.println(" Dr is not registered");
            return;
        }
        int cnt=0;
        for(Doctor doctor: doctors){
            cnt+=doctor.getAvailableSlots().size();
            doctor.getAvailableSlots().forEach(slot -> {
                System.out.println("Mr " + doctor.getName() + " - " + slot.getStartTime() + " - " + slot.getEndTime());
            });
        }

        if(cnt==0){System.out.println(" No empty slot found!! All slots are already booked");
        }
    }

    public void bookAppointment(String patientName, String doctorName, Time startTime, Boolean wait){

        // is patient free
        // is doctorfree
        int bookingId = 0;
        if (dao.patientAlreadyRegistered(patientName) && dao.isPatientAvailable(patientName, startTime)){
            if(dao.doctorAlreadyRegistered(doctorName) && dao.isDoctorAvailable(doctorName, startTime)) {
                bookingId = dao.bookAppointment(patientName, doctorName, startTime);
                System.out.print("your booking id is " + bookingId);
            }
            else if(wait){
                int waitingId = dao.addToWaitingList(startTime, patientName, doctorName);
                System.out.print("your waiting id is " + waitingId);
            }
            else{
                System.out.print("Doctor is not registered or available ");
            }
        }
        else{
            System.out.print("can'nt book patient is not available");
        }

        System.out.print("\n");
    }

    public void cancelBooking( int bookingId){
        if(dao.checkBookingId(bookingId)){
            dao.cancelBooking(bookingId);
            System.out.print("Booking Cancelled\n");
            return;
        }

        System.out.print("give correct booking id\n");
    }

    public void showBookedAppointmentForP(String patientName) {
        List<BookedSlot> bookedAppointment= dao.showBookedAppointmentForP(patientName);

        bookedAppointment.forEach(booking-> System.out.println(booking.getStartTime()));
    }

    public void showBookedAppointmentForD(String doctoName) {
        List<BookedSlot> bookedAppointment= dao.showBookedAppointmentForD(doctoName);
        bookedAppointment.forEach(booking-> System.out.println(booking.getStartTime()));
    }
}
// exception
// strategy
// tests
