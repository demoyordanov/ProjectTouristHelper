package com.packag.yvyor.projecttouristhelper;

public class CountryItem {

    private String countryName;
    private String countryCurrency;
    private int countryFlag;
    private int policeNumber;
    private int healthNumber;
    private int fireDepartmentNumber;

    public CountryItem(String countryName, String countryCurrency, int countryFlag, int policeNumber, int healthNumber, int fireDepartmentNumber) {
        this.countryName = countryName;
        this.countryCurrency = countryCurrency;
        this.countryFlag = countryFlag;
        this.policeNumber = policeNumber;
        this.healthNumber = healthNumber;
        this.fireDepartmentNumber = fireDepartmentNumber;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryCurrency() {
        return countryCurrency;
    }

    public int getCountryFlag() {
        return countryFlag;
    }

    public int getPoliceNumber() {
        return policeNumber;
    }

    public int getHealthNumber() {
        return healthNumber;
    }

    public int getFireDepartmentNumber() {
        return fireDepartmentNumber;
    }
}
