package com.example.useraccount;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class IP_act1 extends AppCompatActivity {
    PDFView ip;
    private TextToSpeech tts;
    private Button Btn_read, Btn_pause;
    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_p_act1);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        getSupportActionBar().hide();


        ip=(PDFView)findViewById(R.id.pdfview1);
        ip.fromAsset("imperative_programming.pdf")
                .enableSwipe(true)
                .enableAnnotationRendering(true)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    result = tts.setLanguage(Locale.US);
                } else {
                    Toast.makeText(getApplicationContext(), "Feature not supported in your device", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void dosomething(View view) {
        switch (view.getId()) {
            case R.id.Btn_read:
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(getApplicationContext(), "Feature not supported by your device", Toast.LENGTH_SHORT).show();
                } else {
                    File file = new File("/assets/imperative_programming.pdf");

                    try {

                        PdfReader pdfReader = new PdfReader(file.getPath());
                        int pages = pdfReader.getNumberOfPages();
                        String stringParser = "";

                        for (int ctr = 3; ctr < pages + 1; ctr++) {
                            stringParser += PdfTextExtractor.getTextFromPage(pdfReader, ctr).trim();
                            tts.speak(stringParser, TextToSpeech.QUEUE_FLUSH, null, null);

                        }
                        pdfReader.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.btn_pause:
                if (tts != null) {
                    tts.stop();
                }
                break;
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }
}




















