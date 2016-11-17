package com.example.arabellaprivat.tanzderfunktionen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Anleitung extends AppCompatActivity {

    /** Text für die Anleitung */
    private TextView t_anleitung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anleitung);

        // Variablen belegen
        t_anleitung = (TextView) findViewById(R.id.anleitung);

        // TODO richtigen Text ausgeben
        // t_anleitung.setText(...);
    }
}
