package com.example.useraccount;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import java.util.Locale;

public class TextToSpeechActivity extends AppCompatActivity {
    private TextToSpeech mTTS;
    private EditText met;
    private SeekBar mseekbar1,mseekbar2;
    private Button button2;

    @Override
    protected void onDestroy() {
        if (mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }
        super.onDestroy();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        getSupportActionBar().hide();
        button2 = findViewById(R.id.Button2);
        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(Locale.ENGLISH);

                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        button2.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });
        mseekbar1 = findViewById(R.id.seekbar1);
        mseekbar2 = findViewById(R.id.seekbar2);
        met = findViewById(R.id.edittext1);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });
    }

        private void speak () {
            String text = met.getText().toString();
            float pitch = (float) mseekbar1.getProgress() / 50;
            if (pitch < 0.1) pitch = 0.1f;
            mTTS.setPitch(pitch);
            float speed = (float) mseekbar2.getProgress() / 50;
            if (speed < 0.1) speed = 0.1f;
            mTTS.setSpeechRate(speed);
            mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }


}