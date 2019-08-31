package com.example.mysymptomanalyzer.Model;

public class SymptomModel {
    String Symptoms;
    int change ;
    String disease;

    public SymptomModel() {
    }

    public SymptomModel(String symptoms, int change, String disease) {
        Symptoms = symptoms;
        this.change = change;
        this.disease = disease;
    }

    public String getSymptoms() {
        return Symptoms;
    }

    public void setSymptoms(String symptoms) {
        Symptoms = symptoms;
    }

    public int getChange() {
        return change;
    }

    public void setChange(int change) {
        this.change = change;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }
}
