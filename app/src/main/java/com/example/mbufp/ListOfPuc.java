package com.example.mbufp;

public class ListOfPuc {
    private String puc_name;
    private String puc_discription;

    public ListOfPuc(String puc_name, String puc_discription) {
        this.puc_name = puc_name;
        this.puc_discription = puc_discription;
    }

    public String getPuc_name() {
        return puc_name;
    }

    public String getPuc_description() {
        return puc_discription;
    }
}
