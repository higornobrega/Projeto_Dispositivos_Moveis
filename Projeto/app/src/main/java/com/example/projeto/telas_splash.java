package com.example.projeto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class telas_splash extends Activity {
    private static int TEMPO_SPLASH = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telas_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Abrir();
            }
        },TEMPO_SPLASH);
    }

    private void Abrir() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
