package com.example.arabellaprivat.tanzderfunktionen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * hier fidet das Spiel / das Zeichnen statt
 */
public class Spiel extends AppCompatActivity {

    // IV
    /** Info-Button */
    private Button b_info;
    /** Button zum Löschen der View */
    private Button b_loeschen;
    /** Menü Button */
    private Button b_menu;
    /** Button zum prüfen der gemalten Funktion */
    private Button b_pruefen;
    /** führt zum nächsten Level oder zur Endbewertung */
    private Button b_weiter;
    /** sagt, ob richtig oder falsch gezeichnet wurde */
    private TextView t_bewertung;
    /** zeigt die zu malende Funktion an */
    private TextView t_funktion;
    /** diese TextView zeigt das aktuelle Level an */
    private TextView t_level;
    /** View, in die die 5 Punkte mit der Levelbewertung gezeichnet werden sollen */
    private LinearLayout v_punkte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spiel);

        // Variablen belegen
        b_info = (Button) findViewById(R.id.info);
        b_loeschen = (Button) findViewById(R.id.loeschen);
        b_menu = (Button) findViewById(R.id.menu);
        b_pruefen = (Button) findViewById(R.id.pruefen);
        b_weiter = (Button) findViewById(R.id.weiter);
        t_bewertung = (TextView) findViewById(R.id.bewertung);
        t_funktion = (TextView) findViewById(R.id.funktion);
        t_level = (TextView) findViewById(R.id.level);
        v_punkte = (LinearLayout) findViewById(R.id.punkte_anzeige);

        // Bewertungs TextView und Weiter Button sind erstmal unsichtbar
        b_weiter.setVisibility(View.INVISIBLE);
        t_bewertung.setVisibility(View.INVISIBLE);

        // TODO zeigen, in welchem Level wir sind
        // t_level.setText("Level " + (SELECT Level FROM Nutzer));

        // TODO Name der Funktion anzeigen
        // indem die Funktion aus der Datenbank geholt wird
        // t_funktion.setText(SELECT funktion FROM Level WHERE Level = (SELECT Level FROM Nutzer));

        // Buttons mit Funktion belegen
        b_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(v);
            }
        });

        b_loeschen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Funktion, die die Zeichen View leert
            }
        });

        b_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(v);
            }
        });

        b_pruefen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO prüfe die Funktion
                // TODO trage das Ergebnis in die Datenbank ein

                // ändere die Anzeige des Buttons
                // Löschen und Prüfen Button verschwinden
                b_loeschen.setVisibility(View.INVISIBLE);
                b_pruefen.setVisibility(View.INVISIBLE);

                // RICHITG oder FALSCH soll angezeigt werden
                t_bewertung.setVisibility(View.VISIBLE);
                // TODO wenn richtig gezeichnet wurde (wenn in der DB eine 1 steht?)
                    t_bewertung.setText("Richtig!");
                // } else
                    // t_bewertung.setText("Falsch!");

                // Button, der zum nächsten Level führt wird sichtbar
                b_weiter.setVisibility(View.VISIBLE);

                // TODO Das Korrekturbild soll über die Zeichnung gelegt werden
            }
        });

        b_weiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(v);
            }
        });

    }

    /**
     * wird beim Click auf einen Button aufgerufen
     * und startet abhängig vom Button die passende Activity
     * @param view  Button, der geklickt wurde
     */
    public void sendMessage(View view){
        if(view.getId() == R.id.info) {
            Intent i = new Intent(this, Info.class);
            startActivity(i);
        } else if(view.getId() == R.id.menu) {
            Intent i = new Intent(this, Menu.class);
            startActivity(i);
        } else if(view.getId() == R.id.weiter) {
            // TODO wenn wir nicht im 5. Level sind, sondern davor
                // gehe ins nächste Level
                // in der Datenbank das nächste Level eintragen
                // bleibe also im Spiel
                // Intent i = new Intent(this, Spiel.class);
                // startActivity(i);
            // wenn wir in Level 5, also am Ende sind
                // gehe zur Endbewertung
                Intent i = new Intent(this, Bewertung.class);
                startActivity(i);
        }

    }

}