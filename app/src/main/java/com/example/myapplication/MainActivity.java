package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class
MainActivity extends AppCompatActivity {
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialiser Text-to-Speech
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.FRENCH);
                    speak("Appuyez sur le bouton pour continuer");
                }
            }
        });

        // Récupérer l'ImageView par son ID
        ImageView imageView = findViewById(R.id.imageView6);

        // Ajouter un écouteur de clic
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Stopper le texte en cours de lecture
                if (textToSpeech.isSpeaking()) {
                    textToSpeech.stop();
                }

                // Naviguer vers MainActivity2
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Quand l'utilisateur revient, refaire parler l'application
        if (textToSpeech != null) {
            speak("Appuyez sur le bouton pour continuer");
        }
    }

    // Méthode pour faire parler l'application
    private void speak(String text) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    @Override
    protected void onDestroy() {
        // Libérer les ressources de Text-to-Speech
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}
