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


        seed(db);
    }

    private void seed(SQLiteDatabase db) {

        db.execSQL("INSERT INTO doctors(id,name,specialist,hospital) VALUES('D1','Dr. Rahman','Cardiologist','City Hospital')");
        db.execSQL("INSERT INTO doctors(id,name,specialist,hospital) VALUES('D2','Dr. Sultana','Dermatologist','Medinova')");
        db.execSQL("INSERT INTO doctors(id,name,specialist,hospital) VALUES('D3','Dr. Karim','Neurologist','Square Hospital')");
        db.execSQL("INSERT INTO doctors(id,name,specialist,hospital) VALUES('D4','Dr. Ayesha','ENT','Popular Diagnostic')");

        db.execSQL("INSERT INTO reviews(doctor_id,review_text) VALUES('D1','Very friendly and professional')");
        db.execSQL("INSERT INTO reviews(doctor_id,review_text) VALUES('D1','Helped me a lot')");
        db.execSQL("INSERT INTO reviews(doctor_id,review_text) VALUES('D2','Good service')");
        db.execSQL("INSERT INTO reviews(doctor_id,review_text) VALUES('D3','Highly recommended')");
        db.execSQL("INSERT INTO schedules(doctor_id, available) VALUES('D1',1)");
        db.execSQL("INSERT INTO schedules(doctor_id, available) VALUES('D2',1)");
        db.execSQL("INSERT INTO schedules(doctor_id, available) VALUES('D3',0)");
        db.execSQL("INSERT INTO schedules(doctor_id, available) VALUES('D4',1)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS reviews");
        db.execSQL("DROP TABLE IF EXISTS appointments");
        db.execSQL("DROP TABLE IF EXISTS doctors");
        onCreate(db);
    }
}
