package com.packag.yvyor.projecttouristhelper;

public class CallItem {
    int id, secondKey;
    String callName, callDescription, callNumber;

    public CallItem(int id, int secondKey, String callName, String callDescription, String callNumber) {
        this.id = id;
        this.secondKey = secondKey;
        this.callName = callName;
        this.callDescription = callDescription;
        this.callNumber = callNumber;
    }

    public int getId() {
        return id;
    }

    public int getForeignKey() {
        return secondKey;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public String getCallName() {
        return callName;
    }

    public String getCallDescription() {
        return callDescription;
    }
}
