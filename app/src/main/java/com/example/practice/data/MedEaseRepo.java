package com.example.practice.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MedEaseRepo {
    private static MedEaseRepo instance;

    private final ArrayList<Doctor> doctors = new ArrayList<>();
    private final ArrayList<Appointment> appointments = new ArrayList<>();

    private MedEaseRepo() {
        doctors.add(new Doctor("D1","Dr. Ayesha Rahman","Cardiologist","City Heart Hospital",
                Arrays.asList("Very professional","Explained clearly")));
        doctors.add(new Doctor("D2","Dr. Tanvir Ahmed","Dermatologist","Green Life Hospital",
                Arrays.asList("Friendly doctor","Good treatment")));
        doctors.add(new Doctor("D3","Dr. Nabila Sultana","Neurologist","Central Medical College Hospital",
                Arrays.asList("Highly experienced","Great consultation")));
    }

    public static MedEaseRepo get() {
        if (instance == null) instance = new MedEaseRepo();
        return instance;
    }

    public List<Doctor> allDoctors() { return doctors; }

    public Doctor findDoctorById(String id) {
        for (Doctor d : doctors) if (d.id.equals(id)) return d;
        return null;
    }

    public List<Doctor> searchDoctors(String name, String specialist, String hospital) {
        String n = name == null ? "" : name.trim().toLowerCase();
        String s = specialist == null ? "" : specialist.trim().toLowerCase();
        String h = hospital == null ? "" : hospital.trim().toLowerCase();

        ArrayList<Doctor> out = new ArrayList<>();
        for (Doctor d : doctors) {
            boolean ok = true;
            if (!n.isEmpty() && !d.name.toLowerCase().contains(n)) ok = false;
            if (!s.isEmpty() && !d.specialist.toLowerCase().contains(s)) ok = false;
            if (!h.isEmpty() && !d.hospital.toLowerCase().contains(h)) ok = false;
            if (ok) out.add(d);
        }
        return out;
    }

    public void createAppointment(String patientEmail, Doctor doctor, String timeText) {
        appointments.add(0, new Appointment(patientEmail, doctor.id, doctor.name, timeText));
    }

    public List<Appointment> getAppointmentsForPatient(String patientEmail) {
        ArrayList<Appointment> out = new ArrayList<>();
        for (Appointment a : appointments)
            if (a.patientEmail.equalsIgnoreCase(patientEmail)) out.add(a);
        return out;
    }

    public List<Appointment> getAllAppointments() { return appointments; }

    public void setAppointmentStatus(String id, Appointment.Status status) {
        for (Appointment a : appointments) {
            if (a.id.equals(id)) { a.status = status; return; }
        }
    }
}
