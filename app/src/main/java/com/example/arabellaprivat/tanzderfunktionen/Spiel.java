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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
    private ImageView i_punkteanzeige;
    /** Zeichenfläche */
    private Zeichenfläche z;
    /** Touchfläche für Hilfspunkt */
    private Hilfspunkte h;
    /** Button um nach dem einzeichnen der HIlfspunkte die Funktion zu zeichnen*/
    private Button b_zeichnen;



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
        i_punkteanzeige = (ImageView) findViewById(R.id.punkteanzeige);
        z=(Zeichenfläche) findViewById(R.id.zeichenfläche);
        h= (Hilfspunkte) findViewById (R.id.hilfspunkte);
        b_zeichnen=(Button) findViewById(R.id.zeichnen);

        // Text reinschreiben
        t_bewertung.setText("Bitte zeichne deine Hilfspunkte ein");

        //  Weiter Button sind erstmal unsichtbar
        b_weiter.setVisibility(View.INVISIBLE);
        // auch eigentliche View zum Zeichnen der Funktion sowie der Überprüfungsbutton sind unsichtbar
        z.setVisibility(View.INVISIBLE);
        b_pruefen.setVisibility(View.INVISIBLE);

        // TODO zeigen, in welchem Level wir sind
        // t_level.setText("Level " + (SELECT Level FROM Nutzer));

        // Kreise zur Anzeige, wie die Level absolviert wurden
        this.punkteanzeigeZeichnen();

        // TODO Name der Funktion anzeigen
        // indem die Funktion aus der Datenbank geholt wird
        // t_funktion.setText(SELECT funktion FROM Level WHERE Level = (SELECT Level FROM Nutzer));
        t_funktion.setText("f(x)=(0.5*x)+2");

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

                // Je nach dem ob gerade Hilfspunkte eingezeichnet werden oder schon die eigentliche Funktion gezeichnet wird
                // werden unterschiedliche Aktionen von diesem Button hervorgerufen
                // die Zeichenfläche die Sichtbar ist wird geleert
                if (z.getVisibility()==View.INVISIBLE)h.loescheView();
                else z.loescheView();
            }
        });

        b_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(v);
            }
        });

        b_zeichnen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Zeichenfläche zum Zeichnen der Funktion sichtbar machen ("einschalten")
                z.setVisibility(View.VISIBLE);
                // Button für Beenden der Hilfspunkte-setzen ausschalten
                b_zeichnen.setVisibility(View.INVISIBLE);

                // Button zum Überprüfen der Funktion einschalten
                b_pruefen.setVisibility(View.VISIBLE);
                // Text ausblenden
                t_bewertung.setVisibility(View.INVISIBLE);
            }
        });

        b_pruefen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pruefung p= new Pruefung(z, h);
                // TODO trage das Ergebnis in die Datenbank ein
                // ändere die Anzeige des Buttons
                // Löschen und Prüfen Button verschwinden
                b_loeschen.setVisibility(View.INVISIBLE);
                b_pruefen.setVisibility(View.INVISIBLE);
                // RICHITG oder FALSCH soll angezeigt werden
                t_bewertung.setVisibility(View.VISIBLE);
                // TODO Level auslesen? WO??
                // die Funktion zum Prüfen der Funktion wird aufgerufen
                // je nach Ergebnis wird das Ergebnis ausgegeben
                if (p.check(1)){
                    t_bewertung.setText("Richtig!");}
                else
                     t_bewertung.setText("Falsch!");

                // Button, der zum nächsten Level führt wird sichtbar
                b_weiter.setVisibility(View.VISIBLE);

                //  Korrekturbild soll über die Zeichnung gelegt werden
                z.changeBackground(1);
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
     * koordiniert das Zeichnen der Punkteanzeige
     * hier werden Zeichen-Eigenschaften gesetzt
     */
    private void punkteanzeigeZeichnen(){
        Bitmap bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
        // der Abstand vom linken Bildschirmrand wird um jew. 15 px erhöht
        int abstand_x = 0;
        // Style und Farbe hängen von der Bewertung der Level ab
        Paint paint = new Paint();
        // mit einer Schleife gehen wir durch die DB zu den verschiedenen Levels
        for (int i = 1; i <= 5; i++) {
            // grundsätzlich sind alle Kreise leer mit schwarzer Umrandung
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            // wenn das Level bestanden ist
            // TODO if((SELECT Bewertung FROM Level WHERE Level = i) == 1){
            // sei der Kreis grün ausgemalt
            paint.setColor(Color.GREEN);
            paint.setStyle(Paint.Style.FILL);
            // }
            // wenn das 1. Level nicht bestanden ist
            // TODO if((SELECT Bewertung FROM Level WHERE Level = i) == 0){
            // sei der Kreis rot ausgemalt
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.FILL);
            // }
            // wenn das 1. Level noch nicht gespielt wurde
            // also wenn in der DB nichts oder null steht
            // bleiben die Einstellungen wie am Anfang

            // Abstand zum linken Nachbarkreis vergrößern
            abstand_x += 15;

            // mit diesen Einstellungen den Kreis malen
            this.einenKreisZeichnen(bitmap, paint, abstand_x);
        }
    }

    /**
     * zeichnet einen Punkt
     * @param bitmap        Hier wird gezeichnet
     * @param paint         Zeichen-Eigenschaften
     * @param abstand_x     Abstand zum linken Bildschirmrand, bzw. zum linken Nachbar-Kreis
     */
    private void einenKreisZeichnen(Bitmap bitmap, Paint paint, int abstand_x){
        Canvas canvas = new Canvas(bitmap);
        // Kreis zeichnen
        // mit dem sich mit jedem Kreis vergrößernden linken Abstand
        // Abstand nach oben 5 px
        // Radius 5 px
        // mit den "Stift"-Eigenschaften, die je nach Level verändert wurden
        canvas.drawCircle(abstand_x, 5, 5, paint);

        // in die ImageView einfügen
        i_punkteanzeige.setImageBitmap(bitmap);
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
                // TODO in der Datenbank das nächste Level eintragen
                // bleibe also im Spiel
                // Intent i = new Intent(this, Spiel.class);
                // startActivity(i);
            // TODO wenn wir in Level 5, also am Ende sind
                // gehe zur Endbewertung
                Intent i = new Intent(this, Bewertung.class);
                startActivity(i);
        }
    }
}