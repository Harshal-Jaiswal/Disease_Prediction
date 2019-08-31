package com.example.mysymptomanalyzer.ResultPAge;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mysymptomanalyzer.Config.Constants;
import com.example.mysymptomanalyzer.R;

public class ResultActivity extends AppCompatActivity {

    private TextView tv_disease;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tv_disease = findViewById(R.id.tv_disease);

        if (getIntent() != null) { tv_disease.setText("Disease found : "+getIntent()
                .getStringExtra(Constants.INTENT_RESULT_ACTIVITY));
        }
    }
}
