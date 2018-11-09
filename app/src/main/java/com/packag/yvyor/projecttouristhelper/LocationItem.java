package com.packag.yvyor.projecttouristhelper;

public class LocationItem {
    private int id;
    private int secondKey;
    private String name, hour, date;

    public LocationItem(int id, int secondKey, String name, String hour, String date) {
        this.id = id;
        this.secondKey = secondKey;
        this.name = name;
        this.hour = hour;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getSecondKey() {
        return secondKey;
    }

    public String getName() {
        return name;
    }

    public String getHour() {
        return hour;
    }

    public String getDate() {
        return date;
    }
}
