package com.example.speed_helper;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Complain {
    public String date;
    public String gps;
    public String name;
    public String sms;

    public Complain(String date, String gps, String name, String sms){
        this.date = date;
        this.gps = gps;
        this.name = name;
        this.sms = sms;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("date", date);
        result.put("gps", gps);
        result.put("name", name);
        result.put("sms", sms);
        return result;
    }



}
