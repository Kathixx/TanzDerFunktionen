package com.example.arabellaprivat.tanzderfunktionen;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * zur Bewertung des Spiels
 */
public class Bewertung extends AppCompatActivity {

    /** Bewertungssatz */
    private TextView t_bewertung;
    /** visualisiert, wie viele Punkte erreicht wurden */
    private ImageView i_punkteanzeige;
    /** gibt an, wie viele Punkte erreicht wurden */
    private TextView t_erreichte_punkte;
    /** Button startet das Spiel von vorne */
    private Button b_nochmal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bewertung);

        // Variablen belegen
        t_bewertung = (TextView) findViewById(R.id.bewertung);
        i_punkteanzeige = (ImageView) findViewById(R.id.punkteanzeige);
        t_erreichte_punkte = (TextView) findViewById(R.id.erreichte_punkte);
        b_nochmal = (Button) findViewById(R.id.nochmal);

        // TODO Bewertungstext schreiben ("Du bist der Mathe King")
        // sowas wie
        // t_bewertung.setText(SELECT text FROM Endbewertung WHERE Punkte = punktezahl);

        // Visualisierung der Punkte
        punkteanzeigeZeichnen();

        // TODO Wie viele Sterne wurden erreicht?
        // t_erreichte_punkte.setText("Du erhälst " + (SELECT...) + " von 5 Punkten.");

        b_nochmal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Datenbank zurücksetzen
                sendMessage(v);
            }
        });


    }
    // Visualisierung der Punkte wie in der Activity Spiel
    /**
     * koordiniert das Zeichnen der Punkteanzeige
     * hier werden Zeichen-Eigenschaften gesetzt
     */
    private void punkteanzeigeZeichnen(){
        Bitmap bitmap = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888);

        // Start bei 1/16 der Bitmap
        float linkerAbstand = bitmap.getWidth()/16;
        // Style und Farbe
        Paint paint = new Paint();
        // insgesamt werden 5 Punkte gezeichnet
        // je nach dem wie hoch die Anzahl der erreichten Punkte sind, werden verschiedene Farben verwendet
        int erreichte_punkte = 2;
        // TODO erreichte_punkte = (SELECT Punkte FROM Nutzer);
        for(int i = 1; i<=erreichte_punkte; i++){
            // diese Punkte werden hellblau gezeichnet
            paint.setColor(Color.rgb(158, 174, 202));
            paint.setStyle(Paint.Style.FILL);

            // der Abstand zwischen den Kreisen beträgt 1/8 der gesamten Breite der Bitmap
            linkerAbstand += bitmap.getWidth()/8;

            // mit diesen Einstellungen den Kreis zeichnen
            this.einenKreisZeichnen(bitmap, paint, linkerAbstand);
        }
        // restliche nicht erreichte Punkte zeichnen
        for(int i = erreichte_punkte; i<=5; i++){
            // diese Punkte werden schwarz umrandet
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);

            // der Abstand zwischen den Kreisen beträgt 1/8 der gesamten Breite der Bitmap
            linkerAbstand += bitmap.getWidth()/8;

            // mit diesen Einstellungen den Kreis zeichnen
            this.einenKreisZeichnen(bitmap, paint, linkerAbstand);
        }
    }

    /**
     * zeichnet einen Punkt
     * @param bitmap        Hier wird gezeichnet
     * @param paint         Zeichen-Eigenschaften
     * @param linkerAbstand     Abstand zum linken Bildschirmrand, bzw. zum linken Nachbar-Kreis
     */
    private void einenKreisZeichnen(Bitmap bitmap, Paint paint, float linkerAbstand){
        Canvas canvas = new Canvas(bitmap);
        // Kreis zeichnen
        // linker Abstand vergrößert sich nach jedem Kreis
        // auf Höhe der Hälfte der Bitmap
        // Radius 50 px
        // mit den "Stift"-Eigenschaften, die je nach Level verändert wurden
        canvas.drawCircle(linkerAbstand, bitmap.getHeight()/2, 45, paint);

        // in die ImageView einfügen
        i_punkteanzeige.setImageBitmap(bitmap);
    }

    public void sendMessage(View view){
        if(view.getId() == R.id.nochmal) {
            Intent i = new Intent(this, Spiel.class);
            startActivity(i);
        }
}
}
