package com.example.arabellaprivat.tanzderfunktionen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * in dieser Klasse wird das Level ausgweählt
 * Anmerkungen / zu erledigen:
 * evtl. lieber die Funktionen als die Level auswählen
 * oder zu Anleitung / Start / Tipps navigieren
 * da es sonst Probleme mit der Endbewertung geben könnte
 * onClick()-Methoden ggf. zusammenfassen, wg. Code-Duplikation
 */
public class Menu extends AppCompatActivity {

    // IV
    /** diese Buttons wählen aus, welches Level gespielt werden soll */
    private Button b_level_1;
    private Button b_level_2;
    private Button b_level_3;
    private Button b_level_4;
    private Button b_level_5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Variablen belegen
        b_level_1 = (Button) findViewById(R.id.level_1);
        b_level_2 = (Button) findViewById(R.id.level_2);
        b_level_3 = (Button) findViewById(R.id.level_3);
        b_level_4 = (Button) findViewById(R.id.level_4);
        b_level_5 = (Button) findViewById(R.id.level_5);

        b_level_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Level 1 auswählen und in die Datenbank als Status schreiben
                // sowas wie
                // UPDATE Nutzer SET level=1;
                // Activity Spiel erneut starten
                sendMessage(v);
            }
        }); // Ende onClickListener

        b_level_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Level 2 auswählen und in ie Datenbank als Status schreiben
                // Activity Spiel erneut starten
                sendMessage(v);
            }
        });

        b_level_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Level 3 auswählen und in ie Datenbank als Status schreiben
                // Activity Spiel erneut starten
                sendMessage(v);
            }
        });

        b_level_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Level 4 auswählen und in ie Datenbank als Status schreiben
                // Activity Spiel erneut starten
                sendMessage(v);
            }
        });

        b_level_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Level 5 auswählen und in ie Datenbank als Status schreiben
                // Activity Spiel erneut starten
                sendMessage(v);
            }
        });
    }

    public void sendMessage(View view) {
        // neues Intent
        Intent i = new Intent(this, Spiel.class);
        // Activity starten
        startActivity(i);
    }

}
