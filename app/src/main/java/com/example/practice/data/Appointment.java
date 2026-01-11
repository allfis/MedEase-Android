package com.example.practice.data;

public class Appointment {

    public enum Status { PENDING, APPROVED, REJECTED }

    public String id;
    public String patientEmail;
    public String doctorId;
    public String doctorName;
    public String slot;
    public Status status;

    public Appointment(String id, String patientEmail, String doctorId, String doctorName, String slot, Status status) {
        this.id = id;
        this.patientEmail = patientEmail;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.slot = slot;
        this.status = status;
    }
}
