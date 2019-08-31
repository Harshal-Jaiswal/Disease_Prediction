package com.example.mysymptomanalyzer.Retrofit;

import android.widget.Toast;

import com.example.mysymptomanalyzer.MainActivity;
import com.example.mysymptomanalyzer.Model.DiseaseModel;
import com.example.mysymptomanalyzer.Model.SymptomModel;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestDataService {

    public static  String simplecall()
    {
        final String[] res = new String[1];
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        Call<String> call = service.sendSimple();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                res[0] = response.body();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
        return res[0];
    }

    public  static  DiseaseModel sendSymptom(SymptomModel symptom) {
        final DiseaseModel[] diseaseModel = new DiseaseModel[1];
        Gson gson = new Gson();
        String symptomString = gson.toJson(symptom);
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        Call<DiseaseModel> call = service.sendSymptoms(symptomString);
        call.enqueue(new Callback<DiseaseModel>() {
            @Override
            public void onResponse(Call<DiseaseModel> call, Response<DiseaseModel> response) {
                if (response.body() != null) {
                    diseaseModel[0] =  response.body();
                }
                else {

                }
            }

            @Override
            public void onFailure(Call<DiseaseModel> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
        return diseaseModel[0];
    }
}
