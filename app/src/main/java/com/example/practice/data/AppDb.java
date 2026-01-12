package com.example.practice.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppDb extends SQLiteOpenHelper {

    private static final String DB_NAME = "medease.db";
    private static final int DB_VERSION = 1;

    public AppDb(Context c) {
        super(c, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "CREATE TABLE doctors (" +
                        "id TEXT PRIMARY KEY," +
                        "name TEXT NOT NULL," +
                        "specialist TEXT NOT NULL," +
                        "hospital TEXT NOT NULL" +
                        ")"
        );

        db.execSQL(
                "CREATE TABLE reviews (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "doctor_id TEXT NOT NULL," +
                        "review_text TEXT NOT NULL," +
                        "FOREIGN KEY(doctor_id) REFERENCES doctors(id) ON DELETE CASCADE" +
                        ")"
        );

        db.execSQL(
                "CREATE TABLE appointments (" +
                        "id TEXT PRIMARY KEY," +
                        "patient_email TEXT NOT NULL," +
                        "doctor_id TEXT NOT NULL," +
                        "doctor_name TEXT NOT NULL," +
                        "slot TEXT NOT NULL," +
                        "status TEXT NOT NULL," +
                        "created_at INTEGER NOT NULL," +
                        "FOREIGN KEY(doctor_id) REFERENCES doctors(id) ON DELETE CASCADE" +
                        ")"
        );

        db.execSQL(
                "CREATE TABLE schedules (" +
                        "doctor_id TEXT PRIMARY KEY," +
                        "available INTEGER NOT NULL" +
                        ")"
        );
        db.execSQL(
                "CREATE TABLE users (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "email TEXT UNIQUE NOT NULL," +
                        "password TEXT NOT NULL," +
                        "role TEXT NOT NULL" +
                        ")"
        );



        seed(db);
    }

    private void seed(SQLiteDatabase db) {

        db.execSQL("INSERT INTO users(email,password,role) VALUES('admin@g.com','123','ADMIN')");
        db.execSQL("INSERT INTO users(email,password,role) VALUES('admin2@gmail.com','admin123','ADMIN')");
        db.execSQL("INSERT INTO users(email,password,role) VALUES('superadmin@medease.com','admin123','ADMIN')");

        db.execSQL("INSERT INTO users(email,password,role) VALUES('patient@medease.com','patient123','PATIENT')");
        db.execSQL("INSERT INTO users(email,password,role) VALUES('rahim@gmail.com','patient123','PATIENT')");
        db.execSQL("INSERT INTO users(email,password,role) VALUES('karim@gmail.com','patient123','PATIENT')");
        db.execSQL("INSERT INTO users(email,password,role) VALUES('ayesha@gmail.com','patient123','PATIENT')");
        db.execSQL("INSERT INTO users(email,password,role) VALUES('fatema@gmail.com','patient123','PATIENT')");


        db.execSQL("INSERT INTO doctors(id,name,specialist,hospital) VALUES('D1','Dr. Rahman','Cardiologist','City Hospital')");
        db.execSQL("INSERT INTO doctors(id,name,specialist,hospital) VALUES('D2','Dr. Sultana','Dermatologist','Medinova')");
        db.execSQL("INSERT INTO doctors(id,name,specialist,hospital) VALUES('D3','Dr. Karim','Neurologist','Square Hospital')");
        db.execSQL("INSERT INTO doctors(id,name,specialist,hospital) VALUES('D4','Dr. Ayesha','ENT Specialist','Popular Diagnostic')");
        db.execSQL("INSERT INTO doctors(id,name,specialist,hospital) VALUES('D5','Dr. Hasan','Orthopedic','Ibn Sina Hospital')");
        db.execSQL("INSERT INTO doctors(id,name,specialist,hospital) VALUES('D6','Dr. Nabila','Gynecologist','Labaid Hospital')");


        db.execSQL("INSERT INTO reviews(doctor_id,review_text) VALUES('D1','Very professional and caring')");
        db.execSQL("INSERT INTO reviews(doctor_id,review_text) VALUES('D1','Explained everything clearly')");
        db.execSQL("INSERT INTO reviews(doctor_id,review_text) VALUES('D2','Skin problem solved quickly')");
        db.execSQL("INSERT INTO reviews(doctor_id,review_text) VALUES('D3','Highly recommended neurologist')");
        db.execSQL("INSERT INTO reviews(doctor_id,review_text) VALUES('D4','Friendly and helpful doctor')");
        db.execSQL("INSERT INTO reviews(doctor_id,review_text) VALUES('D5','Excellent orthopedic surgeon')");

        db.execSQL("INSERT INTO appointments VALUES('A1','rahim@gmail.com','D1','Dr. Rahman','10:00 AM','PENDING', strftime('%s','now'))");
        db.execSQL("INSERT INTO appointments VALUES('A2','karim@gmail.com','D2','Dr. Sultana','11:30 AM','PENDING', strftime('%s','now'))");
        db.execSQL("INSERT INTO appointments VALUES('A3','ayesha@gmail.com','D3','Dr. Karim','02:00 PM','PENDING', strftime('%s','now'))");
        db.execSQL("INSERT INTO appointments VALUES('A4','fatema@gmail.com','D4','Dr. Ayesha','04:00 PM','PENDING', strftime('%s','now'))");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS reviews");
        db.execSQL("DROP TABLE IF EXISTS appointments");
        db.execSQL("DROP TABLE IF EXISTS doctors");
        onCreate(db);
    }
}
