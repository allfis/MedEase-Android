package com.example.practice.data;

import java.io.Serializable;
import java.util.UUID;

public class Appointment implements Serializable {
    public enum Status { PENDING, APPROVED, REJECTED }

    public final String id;
    public final String patientEmail;
    public final String doctorId;
    public final String doctorName;
    public final String timeText;
    public Status status;

    public Appointment(String patientEmail, String doctorId, String doctorName, String timeText) {
        this.id = UUID.randomUUID().toString();
        this.patientEmail = patientEmail;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.timeText = timeText;
        this.status = Status.PENDING;
    }
}
