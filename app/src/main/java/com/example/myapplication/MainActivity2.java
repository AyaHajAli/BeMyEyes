package com.example.myapplication;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2); // Vérifie que le bon layout est chargé

        // Initialisation du Vibrator
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        // Récupération de l'ImageView (Assurez-vous qu'il existe dans le XML)
        ImageView imageView = findViewById(R.id.view5);

        // Ajout du listener pour le clic
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSoundWithVibration();
            }
        });
    }

    private void playSoundWithVibration() {
        // Vérifie si MediaPlayer est déjà en cours
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }

        // Lecture du son (assurez-vous que le fichier est dans res/raw/)
        mediaPlayer = MediaPlayer.create(this, R.drawable.sound);
        mediaPlayer.start();

        // Ajout de la vibration (500ms)
        if (vibrator != null) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                vibrator.vibrate(500);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
