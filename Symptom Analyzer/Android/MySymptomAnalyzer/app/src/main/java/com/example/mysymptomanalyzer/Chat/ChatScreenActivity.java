package com.example.mysymptomanalyzer.Chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.mysymptomanalyzer.Config.Constants;
import com.example.mysymptomanalyzer.Model.ChatModel;
import com.example.mysymptomanalyzer.Model.SymptomModel;
import com.example.mysymptomanalyzer.R;
import com.example.mysymptomanalyzer.Retrofit.GetDataService;
import com.example.mysymptomanalyzer.Retrofit.RestDataService;
import com.example.mysymptomanalyzer.Retrofit.RetrofitClientInstance;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ChatScreenActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImageViewSend;
    private ImageView mImageViewVoiceInput;
    private EditText mEditTextChatInput;
    private RecyclerView mRecyclerView;
    private ChatAdapter mChatAdapter;
    private List<ChatModel> chatModelList ;
    private DatabaseReference reference;

    @Override
    protected void onStart() {
        chatModelList =  new ArrayList<>();
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);


        mImageViewSend = findViewById(R.id.img_send);
        mImageViewVoiceInput = findViewById(R.id.img_voice_input);
        mEditTextChatInput = findViewById(R.id.edt_chat);
        mRecyclerView = findViewById(R.id.recycler_view);

        mImageViewSend.setOnClickListener(this);
        mImageViewVoiceInput.setOnClickListener(this);

        checkPermissions();

        initRecyclerView();

        initFireBaseDataBase();

        final SpeechRecognizer speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);

        final Intent mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault());

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle bundle) {
                ArrayList<String> matches = bundle
                        .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                //displaying the first match
//                    mEditTextChatInput.setText(matches.get(0));
                if (matches != null)
                {
                    ChatModel chat = new ChatModel(matches.get(0),Constants.TYPE_USER);
                    chatModelList.add(chat);
                    mChatAdapter.setChatList(chatModelList);
                    mChatAdapter.notifyDataSetChanged();
                    reference.child("message").push().setValue(chat);
                }
            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });

        findViewById(R.id.img_voice_input).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        speechRecognizer.stopListening();
                        mEditTextChatInput.setHint("You will see input here");
                        break;

                    case MotionEvent.ACTION_DOWN:
                        speechRecognizer.startListening(mSpeechRecognizerIntent);
                        mEditTextChatInput.setText("");
                        mEditTextChatInput.setHint("Listening...");
                        break;
                }
                return false;
            }
        });

    }

    private void initFireBaseDataBase() {
        reference = FirebaseDatabase.getInstance().getReference("Chat");
        reference.keepSynced(true);
    }

    private void checkPermissions() {
        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {
                Manifest.permission.INTERNET,
                Manifest.permission.RECORD_AUDIO
        };

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }

    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private void initRecyclerView() {
        mChatAdapter = new ChatAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mChatAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_send:

                break;

            case R.id.img_voice_input:

                break;
        }
    }
}
