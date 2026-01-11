package com.example.practice.data;

import java.util.ArrayList;

public class Doctor {
    public String id;
    public String name;
    public String specialist;
    public String hospital;
    public ArrayList<String> reviews = new ArrayList<>();

    public Doctor(String id, String name, String specialist, String hospital) {
        this.id = id;
        this.name = name;
        this.specialist = specialist;
        this.hospital = hospital;
    }
}
