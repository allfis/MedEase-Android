package com.example.practice.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MedEaseRepo {

    private static MedEaseRepo instance;
    private final AppDb db;

    private MedEaseRepo(Context c) {
        db = new AppDb(c.getApplicationContext());
    }

    public static void init(Context c) {
        if (instance == null) instance = new MedEaseRepo(c);
    }

    public static MedEaseRepo get() {
        if (instance == null) throw new IllegalStateException("MedEaseRepo.init(context) not called");
        return instance;
    }

    public List<Doctor> allDoctors() {
        SQLiteDatabase r = db.getReadableDatabase();
        Cursor cur = r.rawQuery("SELECT id,name,specialist,hospital FROM doctors ORDER BY name", null);
        ArrayList<Doctor> list = new ArrayList<>();
        while (cur.moveToNext()) {
            Doctor d = new Doctor(
                    cur.getString(0),
                    cur.getString(1),
                    cur.getString(2),
                    cur.getString(3)
            );
            d.reviews.addAll(getReviews(d.id));
            list.add(d);
        }
        cur.close();
        return list;
    }

    public List<Doctor> searchDoctors(String name, String specialist, String hospital) {
        String q = (name == null ? "" : name.trim()).toLowerCase();
        SQLiteDatabase r = db.getReadableDatabase();

        String sql =
                "SELECT id,name,specialist,hospital FROM doctors " +
                        "WHERE lower(name) LIKE ? OR lower(specialist) LIKE ? OR lower(hospital) LIKE ? " +
                        "ORDER BY name";

        String like = "%" + q + "%";
        Cursor cur = r.rawQuery(sql, new String[]{ like, like, like });

        ArrayList<Doctor> list = new ArrayList<>();
        while (cur.moveToNext()) {
            Doctor d = new Doctor(
                    cur.getString(0),
                    cur.getString(1),
                    cur.getString(2),
                    cur.getString(3)
            );
            d.reviews.addAll(getReviews(d.id));
            list.add(d);
        }
        cur.close();
        return list;
    }

    public Doctor findDoctorById(String doctorId) {
        SQLiteDatabase r = db.getReadableDatabase();
        Cursor cur = r.rawQuery(
                "SELECT id,name,specialist,hospital FROM doctors WHERE id=?",
                new String[]{ doctorId }
        );
        Doctor d = null;
        if (cur.moveToFirst()) {
            d = new Doctor(
                    cur.getString(0),
                    cur.getString(1),
                    cur.getString(2),
                    cur.getString(3)
            );
            d.reviews.addAll(getReviews(d.id));
        }
        cur.close();
        return d;
    }

    public List<String> getReviews(String doctorId) {
        SQLiteDatabase r = db.getReadableDatabase();
        Cursor cur = r.rawQuery(
                "SELECT review_text FROM reviews WHERE doctor_id=? ORDER BY id DESC",
                new String[]{ doctorId }
        );
        ArrayList<String> list = new ArrayList<>();
        while (cur.moveToNext()) list.add(cur.getString(0));
        cur.close();
        return list;
    }

    public void createAppointment(String patientEmail, Doctor doctor, String slot) {
        SQLiteDatabase w = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", UUID.randomUUID().toString());
        cv.put("patient_email", patientEmail);
        cv.put("doctor_id", doctor.id);
        cv.put("doctor_name", doctor.name);
        cv.put("slot", slot);
        cv.put("status", Appointment.Status.PENDING.name());
        cv.put("created_at", System.currentTimeMillis());
        w.insert("appointments", null, cv);
    }

    public List<Appointment> getAppointmentsForPatient(String email) {
        SQLiteDatabase r = db.getReadableDatabase();
        Cursor cur = r.rawQuery(
                "SELECT id,patient_email,doctor_id,doctor_name,slot,status FROM appointments " +
                        "WHERE patient_email=? ORDER BY created_at DESC",
                new String[]{ email }
        );
        ArrayList<Appointment> list = new ArrayList<>();
        while (cur.moveToNext()) {
            list.add(new Appointment(
                    cur.getString(0),
                    cur.getString(1),
                    cur.getString(2),
                    cur.getString(3),
                    cur.getString(4),
                    Appointment.Status.valueOf(cur.getString(5))
            ));
        }
        cur.close();
        return list;
    }
    public boolean isDoctorAvailable(String doctorId) {
        SQLiteDatabase r = db.getReadableDatabase();
        Cursor c = r.rawQuery(
                "SELECT available FROM schedules WHERE doctor_id=?",
                new String[]{doctorId}
        );
        boolean available = false;
        if (c.moveToFirst()) available = c.getInt(0) == 1;
        c.close();
        return available;
    }

    public void setDoctorAvailability(String doctorId, boolean available) {
        SQLiteDatabase w = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("available", available ? 1 : 0);
        w.update("schedules", cv, "doctor_id=?", new String[]{doctorId});
    }


    public List<Appointment> getAllAppointments() {
        SQLiteDatabase r = db.getReadableDatabase();
        Cursor cur = r.rawQuery(
                "SELECT id,patient_email,doctor_id,doctor_name,slot,status FROM appointments ORDER BY created_at DESC",
                null
        );
        ArrayList<Appointment> list = new ArrayList<>();
        while (cur.moveToNext()) {
            list.add(new Appointment(
                    cur.getString(0),
                    cur.getString(1),
                    cur.getString(2),
                    cur.getString(3),
                    cur.getString(4),
                    Appointment.Status.valueOf(cur.getString(5))
            ));
        }
        cur.close();
        return list;
    }

    public void setAppointmentStatus(String appointmentId, Appointment.Status status) {
        SQLiteDatabase w = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("status", status.name());
        w.update("appointments", cv, "id=?", new String[]{ appointmentId });
    }
}
