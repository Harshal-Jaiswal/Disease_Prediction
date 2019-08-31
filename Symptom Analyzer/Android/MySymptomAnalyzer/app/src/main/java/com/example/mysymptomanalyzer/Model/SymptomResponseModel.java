package com.example.mysymptomanalyzer.Model;

import java.util.List;

public class SymptomResponseModel {
    List<String> Symptoms;
    int change ;
    String disease;

    public SymptomResponseModel(List<String> symptoms, int change, String disease) {
        Symptoms = symptoms;
        this.change = change;
        this.disease = disease;
    }

    public List<String> getSymptoms() {
        return Symptoms;
    }

    public void setSymptoms(List<String> symptoms) {
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
