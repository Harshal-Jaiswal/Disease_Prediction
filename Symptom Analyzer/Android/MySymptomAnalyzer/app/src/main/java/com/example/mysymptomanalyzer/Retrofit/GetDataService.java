package com.example.mysymptomanalyzer.Retrofit;

import com.example.mysymptomanalyzer.Model.DiseaseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GetDataService {

    @POST("/")
    Call<DiseaseModel> sendSymptoms(@Body String symptom);

    @GET("HTTP/1.1")
    Call<String> sendSimple();
}