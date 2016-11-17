package com.example.arabellaprivat.tanzderfunktionen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * zur Bewertung des Spiels
 */
public class Bewertung extends AppCompatActivity {

    /** Bewertungssatz */
    private TextView t_bewertung;
    /** Sterne zur Visualisierung der erreichten Punkte */
    private RatingBar v_sterne;
    /** gibt an, wie viele Punkte erreicht wurden */
    private TextView t_erreichte_punkte;
    /** Variable für die Anzahl der Punkte */
    // TODO private int punktezahl = SELECT Punkte From Nutzer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bewertung);

        // Variablen belegen
        t_bewertung = (TextView) findViewById(R.id.bewertung);
        v_sterne = (RatingBar) findViewById(R.id.sterne);
        t_erreichte_punkte = (TextView) findViewById(R.id.erreichte_punkte);

        // TODO Bewertungstext schreiben ("Du bist der Mathe King")
        // sowas wie
        // t_bewertung.setText(SELECT text FROM Endbewertung WHERE Punkte = punktezahl);

        // Sterne zur Visualisierung der erreichten Punktezahl ausfüllen
        // TODO Rating Bar ändern, sodass der User die Anzahl der ausgefüllten Sterne nicht ändern kann
        // v_sterne.setRating(punktezahl);

        // TODO Wie viele Sterne wurden erreicht?
        // Variable für die Punktezahl verwenden
        // t_erreichte_punkte.setText("Du erhälst " + punktezahl + " von 5 Punkten.");
    }
}
