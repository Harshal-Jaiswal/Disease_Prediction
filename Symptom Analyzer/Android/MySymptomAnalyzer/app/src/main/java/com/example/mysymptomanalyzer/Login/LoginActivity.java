package com.example.mysymptomanalyzer.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mysymptomanalyzer.Chat.ChatScreenActivity;
import com.example.mysymptomanalyzer.Config.Constants;
import com.example.mysymptomanalyzer.R;
import com.example.mysymptomanalyzer.SymptomAnalyzer.SymptomAnalyzerActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEditTextUserName;
    private EditText mEditTextPassWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        mEditTextUserName = findViewById(R.id.edt_user_name);
        mEditTextPassWord = findViewById(R.id.edt_password);
        Button mButtonLogin = findViewById(R.id.btn_login);

        mButtonLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_login) {
            String userName = mEditTextUserName.getText().toString();
            String passWord = mEditTextPassWord.getText().toString();
            if (userName.equals(Constants.USERNAME) && passWord.equals(Constants.PASSWORD)) {
                Intent intent = new Intent(this, SymptomAnalyzerActivity.class);
                startActivity(intent);
            }
        }
    }
}
