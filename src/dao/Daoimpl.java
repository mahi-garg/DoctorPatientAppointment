package dao;

import db.InMemoryDb;
import models.*;

import java.awt.print.Book;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Daoimpl implements Dao {

    public InMemoryDb db = InMemoryDb.getInstance();

    private static  int id = 1;
    private static  int waitingId = 1;
    @Override
    public void registerDoctor(Doctor doctor) {

        db.getDoctorDB().put(doctor.getName(), doctor);
        if(db.getSpecializationDB().keySet().contains(doctor.getSpecialization())){
            db.getSpecializationDB().get(doctor.getSpecialization()).add(doctor);
        }
        else{
            List<Doctor> list = new ArrayList<>();
            list.add(doctor);
            db.getSpecializationDB().put(doctor.getSpecialization(), list);
        }
    }
    @Override
    public void addAvailableSlot(String name, AvailableSlot validAvailableSlot) {

        db.getDoctorDB().get(name).getAvailableSlots().add(validAvailableSlot);
    }

    @Override
    public void registerPatient(Patient patient) {
        db.getPatientDB().put(patient.getName(), patient);
    }

    @Override
    public List<Doctor> showDoctorAvailabilityBy(Specialization specialization) {

        List<Doctor> allDoctor = db.getSpecializationDB().get(specialization);

        return allDoctor;
    }

    @Override
    public Integer bookAppointment(String patientName, String doctorName, Time startTime) {

            BookedSlot SlotToBeBooked = new BookedSlot(id, startTime, patientName, doctorName);
            db.getBookings().put(SlotToBeBooked.getBookingId(), SlotToBeBooked);
            id++;

            List<AvailableSlot> slots =  db.getDoctorDB().get(doctorName).getAvailableSlots().stream().filter(slot-> !slot.getStartTime().equals(startTime)).collect(Collectors.toList());
            db.getDoctorDB().get(doctorName).setAvailableSlots(slots);
            db.getDoctorDB().get(doctorName).getBookedSlots().add(SlotToBeBooked);
            db.getPatientDB().get(patientName).getBookedAppointments().add(SlotToBeBooked);
            return SlotToBeBooked.getBookingId();

    }

    @Override
    public int addToWaitingList(Time startTime, String patientName, String doctorName){
        db.getWaitListDb().put(waitingId, new WaitingList(waitingId, startTime, patientName, doctorName ));
        waitingId++;
        return waitingId-1;
    }

    @Override
    public Boolean checkBookingId(int bookingId){
        return db.getBookings().keySet().contains(bookingId);
    }
    @Override
    public void cancelBooking(int bookingId) {

        BookedSlot bookedSlot = db.getBookings().get(bookingId);
        String patientName = bookedSlot.getPatient();
        String doctorName = bookedSlot.getDoctor();

        db.getPatientDB().get(patientName).getBookedAppointments().remove(bookedSlot);
        db.getDoctorDB().get(doctorName).getBookedSlots().remove(bookedSlot);
        db.getDoctorDB().get(doctorName).getAvailableSlots().add(new AvailableSlot(bookedSlot.getStartTime(), new Time(bookedSlot.getStartTime().getTime()+ 60*60*1000)));

        clearWaitingList(doctorName,  bookedSlot.getStartTime());
    }

    @Override
    public List<BookedSlot> showBookedAppointmentForP(String patientName) {
        return db.getPatientDB().get(patientName).getBookedAppointments();
    }

    @Override
    public List<BookedSlot> showBookedAppointmentForD(String doctorName) {
        return db.getDoctorDB().get(doctorName).getBookedSlots();
    }

    @Override
    public Boolean isDoctorExist(String name){
        return db.getDoctorDB().keySet().contains(name);
    }

    @Override
    public Boolean isDoctorAvailable(String doctorName, Time startTime){

        List<AvailableSlot> list =  db.getDoctorDB().get(doctorName).getAvailableSlots();

        for(AvailableSlot slot: list){

            if(slot.getStartTime().equals(startTime)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean isPatientAvailable(String patientName, Time startTime){

        List<BookedSlot> appointments =  db.getPatientDB().get(patientName).getBookedAppointments();

        for(BookedSlot slot: appointments){

            if(slot.getStartTime().equals(startTime)){
                return false;
            }
        }
        return true;
    }

    private void clearWaitingList(String doctorName, Time startTime){

        Collection<WaitingList> waitingList = db.getWaitListDb().values();
        String patientName = null;
        int waitingIdToBeRemoved = 0;
        for (WaitingList waiting: waitingList){


            if(waiting.getDoctor().equals(doctorName) && waiting.getStartTime().equals(startTime)){

                patientName = waiting.getPatient();
                waitingIdToBeRemoved = waiting.getWaitingId();
            }
        }

        if(waitingIdToBeRemoved!=0) {
            db.getWaitListDb().remove(waitingIdToBeRemoved);
            if(bookAppointment(patientName, doctorName, startTime)!=0){
                System.out.println("waiting list with waitingId " +  waitingIdToBeRemoved + " got confirmed for " + patientName + " - " + doctorName + " - at time " + startTime);
            }
        }
    }
    @Override

    public Boolean doctorAlreadyRegistered(String name){
        return db.getDoctorDB().keySet().contains(name);
    }

    @Override
    public Boolean patientAlreadyRegistered(String name){
        return db.getPatientDB().keySet().contains(name);
    }
}
