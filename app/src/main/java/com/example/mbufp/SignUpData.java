package com.example.mbufp;

import android.widget.EditText;

public class SignUpData {
    String name,address,contact,adhaar,email,driving_licence,vehicle_number,password;

    public SignUpData(String name, String address, String contact, String adhaar, String email, String driving_licence, String vehicle_number, String password) {
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.adhaar = adhaar;
        this.email = email;
        this.driving_licence = driving_licence;
        this.vehicle_number = vehicle_number;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAdhaar() {
        return adhaar;
    }

    public void setAdhaar(String adhaar) {
        this.adhaar = adhaar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDriving_licence() {
        return driving_licence;
    }

    public void setDriving_licence(String driving_licence) {
        this.driving_licence = driving_licence;
    }

    public String getVehicle_number() {
        return vehicle_number;
    }

    public void setVehicle_number(String vehicle_number) {
        this.vehicle_number = vehicle_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public  SignUpData(String id, EditText name, EditText address, EditText aadhaar, EditText contact_number, EditText email, EditText driving_licence, EditText vehicle_number, EditText PASSWORD)
{

}
}