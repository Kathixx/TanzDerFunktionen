package com.example.arabellaprivat.tanzderfunktionen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Startbildschirm
 * wird beim Start der App automatisch aufgerufen
 */
public class MainActivity extends AppCompatActivity {

    // IV
    /** Begrüßungstext beim Start der App */
    private TextView t_willkommen;
    /** Button, mit dem das Spiel gestartet wird */
    private Button b_start;
    /** Button navigiert zur Anleitung */
    private Button b_anleitung;

    /**
     * wird beim Start der App aufgerufen
     * setzt Layout und Interaktionen fest
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Variablen belegen
        t_willkommen = (TextView) findViewById(R.id.willkommen);
        b_start = (Button) findViewById(R.id.start);
        b_anleitung = (Button) findViewById(R.id.anleitung);


        // Begrüßungstext anzeigen
        t_willkommen.setText(R.string.willkommenText);

        // Buttonfunktion die das Spiel startet erstellen
        b_start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // in sendMessage wird die neue Activity gestartet
                sendMessage(v);
            }
        });

        // Buttonfunktion die zur Anleitung führt erstellen
        b_anleitung.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // in sendMessage wird die neue Activity gestartet
                sendMessage(v);
            }
        });
    }

    /**
     * wird beim Click auf einen Button aufgerufen
     * und startet abhängig vom Button die passende Activity
     * @param view  View, die geklickt wurde
     */
    public void sendMessage(View view){
        // Spiel starten
        if(view.getId() == R.id.start){
            Intent i = new Intent(this, Spiel.class);
            startActivity(i);
        }
        // Anleitung anzeigen lassen
        if(view.getId() == R.id.anleitung) {
            Intent i = new Intent(this, Anleitung.class);
            startActivity(i);
        }

    }
}