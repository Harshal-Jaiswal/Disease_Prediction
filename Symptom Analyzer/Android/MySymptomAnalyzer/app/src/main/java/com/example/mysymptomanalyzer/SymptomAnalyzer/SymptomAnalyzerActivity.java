package com.example.mysymptomanalyzer.SymptomAnalyzer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mysymptomanalyzer.Config.Constants;
import com.example.mysymptomanalyzer.Config.SymptomList;
import com.example.mysymptomanalyzer.Model.SymptomModel;
import com.example.mysymptomanalyzer.Model.SymptomResponseModel;
import com.example.mysymptomanalyzer.R;
import com.example.mysymptomanalyzer.ResultPAge.ResultActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.List;

import javax.sql.StatementEvent;

public class SymptomAnalyzerActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerViewSyptoms;
    private Button mButtonFindDisease;
    private SymptomAdapter mSymptomAdapter;
    private DatabaseReference mReference;
    private ProgressBar mProgressBarPb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_analyzer);

        mRecyclerViewSyptoms = findViewById(R.id.recycler_view_symptoms);
        mButtonFindDisease = findViewById(R.id.btn_find_disease);
        mProgressBarPb = findViewById(R.id.pb);
        mProgressBarPb.setVisibility(View.GONE);

        mButtonFindDisease.setOnClickListener(this);

        initRecyclerView();

        initFireBaseDataBase();
    }

    private void initFireBaseDataBase() {
        mReference = FirebaseDatabase.getInstance().getReference("name");
        mReference.keepSynced(true);
    }

    private void initRecyclerView() {
        mSymptomAdapter = new SymptomAdapter(SymptomList.getNext20Symptoms());
        mRecyclerViewSyptoms.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewSyptoms.setAdapter(mSymptomAdapter);
        mRecyclerViewSyptoms.setItemViewCacheSize(SymptomList.getNext20Symptoms().size());
        mSymptomAdapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_find_disease) {
            final List<String> selectedSymptom = mSymptomAdapter.getSelectedList();
            if (selectedSymptom.size() > 10) {
                Gson gson = new Gson();
                String symptomString = gson.toJson(selectedSymptom);
                String symptoms = symptomString.replaceAll("/\"", "/'");
                mReference.setValue(new SymptomModel(symptoms, 1, ""));
                mProgressBarPb.setVisibility(View.VISIBLE);
                mRecyclerViewSyptoms.setVisibility(View.GONE);
                mButtonFindDisease.setVisibility(View.GONE);
                mReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        SymptomModel symptomModel = dataSnapshot.getValue(SymptomModel.class);
//                    String result = dataSnapshot.getValue();
                        if (symptomModel.getChange() == 0) {
                            Intent intent = new Intent(SymptomAnalyzerActivity.this,
                                    ResultActivity.class);
                            mProgressBarPb.setVisibility(View.GONE);
                            mRecyclerViewSyptoms.setVisibility(View.VISIBLE);
                            mButtonFindDisease.setVisibility(View.VISIBLE);
                            intent.putExtra(Constants.INTENT_RESULT_ACTIVITY, symptomModel.getDisease());
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        mRecyclerViewSyptoms.setVisibility(View.VISIBLE);
                        mButtonFindDisease.setVisibility(View.VISIBLE);

                        Toast.makeText(SymptomAnalyzerActivity.this, "Failed to find disease. Please try again...!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }
        else {
            Toast.makeText(this, "Select atleast 10 Symptoms ", Toast.LENGTH_SHORT).show();
        }
    }
}
