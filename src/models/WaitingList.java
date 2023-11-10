package models;

import java.sql.Time;

public class WaitingList {

    int waitingId;

    Time startTime;

    String patient;
    String doctor;

    public WaitingList(int waitingId, Time startTime, String patient, String doctor) {
        this.waitingId = waitingId;
        this.startTime = startTime;
        this.patient = patient;
        this.doctor = doctor;
    }

    public int getWaitingId() {
        return waitingId;
    }

    public void setWaitingId(int waitingId) {
        this.waitingId = waitingId;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

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
}
