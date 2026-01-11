package com.example.practice.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Doctor implements Serializable {
    public final String id;
    public final String name;
    public final String specialist;
    public final String hospital;
    public final List<String> reviews;

    public Doctor(String id, String name, String specialist, String hospital, List<String> reviews) {
        this.id = id;
        this.name = name;
        this.specialist = specialist;
        this.hospital = hospital;
        this.reviews = reviews == null ? new ArrayList<>() : reviews;
    }
}
