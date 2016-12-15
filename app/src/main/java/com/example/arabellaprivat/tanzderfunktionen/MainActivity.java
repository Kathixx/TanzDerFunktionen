package com.example.arabellaprivat.tanzderfunktionen;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Startbildschirm
 * wird beim Start der App automatisch aufgerufen
 */
public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    // IV
    /**
     * Begrüßungstext beim Start der App
     */
    private TextView t_willkommen;
    /**
     * Button, mit dem das Spiel gestartet wird
     */
    private Button b_start;
    /**
     * Button navigiert zur Anleitung
     */
    private Button b_anleitung;
    /**
     * setzt das Spiel an dem Punkt fort, wo man aufgehört hat
     */
    private Button b_weiterspielen;
    /**
     * legt ein Datenquellen-Objekt an
     */
    private Datasource dataSource;

    /**
     * Liste mit den Parametern der Funktion
     */

    private List l;
    private Liste parameterList;
    /**
     * Liste mit den TExten der Funkiton
     */
    private StringListe textList= new StringListe();

    /**
     * wird beim Start der App aufgerufen
     * setzt Layout und Interaktionen fest
     * legt Datenquellenobjekt an
     * öffnen und schließen der DBverbindung in lifecyle-callbacks ausgelagert
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(LOG_TAG, "Das Datenquellen-Objekt wird angelegt.");
        dataSource = new Datasource(this);

        l= dataSource.getAllEntries();

        // Variablen belegen
        t_willkommen = (TextView) findViewById(R.id.willkommen);
        b_start = (Button) findViewById(R.id.start);
        b_anleitung = (Button) findViewById(R.id.anleitung);
        b_weiterspielen = (Button) findViewById(R.id.weiterspielen);

        parameterList= new Liste ();

        // man kann nur weiter spielen, wenn schonmal gespielt wurde
        // d. h. wenn in der Datenbank keine Bewertungen stehen kann man nur neu starten
        // TODO Abfrage: stehen in der Datenbank irgendwo Werte?
        // if(...){
        b_weiterspielen.setVisibility(View.INVISIBLE);
        // b_start.setText("Neustart");


        // Begrüßungstext anzeigen
        t_willkommen.setText(R.string.willkommenText);

        // Buttonfunktion die das Spiel neu startet erstellen
        b_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Datenbank zurücksetzen
                // in sendMessage wird die neue Activity gestartet
                sendMessage(v);
            }
        });

        b_weiterspielen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Datenbank nicht zurücksetzen??
                // in sendMessage wird die neue Activity gestartet
                sendMessage(v);
            }
        });

        // Buttonfunktion die zur Anleitung führt erstellen
        b_anleitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // in sendMessage wird die neue Activity gestartet
                sendMessage(v);
            }
        });
    }

    /**
     * wird beim Click auf einen Button aufgerufen
     * und startet abhängig vom Button die passende Activity
     *
     * @param view View, die geklickt wurde
     */
    public void sendMessage(View view) {
        // Spiel neu starten
        if (view.getId() == R.id.start) {
            Bundle b = new Bundle();

            // starte in Level 1
            b.putInt("Level", 1);

            // erstelle eine neue Liste für die Punkte
            ArrayList<Integer> levelpoints = new ArrayList<>(5);
            // levelpoints.trimToSize();
            // Liste mit null-Werten füllen
            for (int i = 0; i <= 5; i++) {
                levelpoints.add(i, null);
            }
            b.putIntegerArrayList("Punkte", levelpoints);
            Intent i = new Intent(this, Spiel.class);
            i.putExtras(b);
            startActivity(i);
        }
        // Spiel weiter spielen
        if (view.getId() == R.id.weiterspielen) {
            Bundle b = new Bundle();
            // TODO letzten Stand aus der Datenbank holen
            // Level und die ArrayList mit den Punkten
            // b.putInt("Level", ...);
            // b.putIntegerArrayList("Punkte pro Level", ...);
            Intent i = new Intent(this, Spiel.class);
            i.putExtras(b);
            startActivity(i);
        }
        // Anleitung anzeigen lassen
        if (view.getId() == R.id.anleitung) {
            Intent i = new Intent(this, Anleitung.class);
            startActivity(i);
        }

    }

    /**
     * Callback Methode
     * stellt die Verbindung zur DB her
     */
    @Override
    protected void onResume() {
        super.onResume();

        Log.d(LOG_TAG, "Die Datenquelle wird geöffnet.");
        dataSource.open();
    }

    /**
     * Callback Methode
     * schließt die Verbindung zur Datenbank
     */
    @Override
    protected void onPause() {
        super.onPause();

        Log.d(LOG_TAG, "Die Datenquelle wird geschlossen.");
        dataSource.close();
    }


    /*
     * Methode schreibt das aktuelle Level in die zwei vordefinierten Listen parameterList/TextList ein
     * sodass hinterher mit den Listen gearbeitet werden kann
     *
     * @param level

    public void insertLevelInList(int level) {
        List l = dataSource.getAllEntries();
        // Level von 1-5 aber Listeneintrag beginnt bei 0 deshalb -1!
        String s = l.get(level - 1).toString();
        String[] elementArray = s.split(",");
        // TODO hier müssen wir uns noch eine bessere Methode überlegen

        //  Strings also Funktion und Tip in TExtliste einfügen
        //  STelle 1+2 im Arrayy
        for (int i = 1; i <= 2; i++) {
            textList.add(elementArray[i]);
        }
        // Float also Parameter, Nullstellen, Achsenabschnitt und Intervall in ParameterList
        // ab Stelle 3 im Array
        for (int i = 3; i < elementArray.length; i++) {
            parameterList.add(Float.valueOf(elementArray[i]));
        }
    }*/

    public Liste getParameterList(int level) {
       /* List l = new StringListe();
        l=dataSource.getAllEntries();*/
        // Level von 1-5 aber Listeneintrag beginnt bei 0 deshalb -1!
        String s = l.get(level - 1).toString();
        String[] elementArray = s.split(",");
        // Float also Parameter, Nullstellen, Achsenabschnitt und Intervall in ParameterList
        // ab Stelle 3 im Array
        for (int i = 3; i < elementArray.length; i++) {
            parameterList.add(Float.valueOf(elementArray[i]));
        }
        return parameterList;
    }

    public StringListe getTextList(int level) {
        /*    List l = new StringListe();
        l= dataSource.getAllEntries(); */
        // Level von 1-5 aber Listeneintrag beginnt bei 0 deshalb -1!
        String s = l.get(level - 1).toString();
        String[] elementArray = s.split(",");
        //  Strings also Funktion und Tip in TExtliste einfügen
        //  STelle 1+2 im Arrayy
        for (int i = 1; i <= 2; i++) {
            textList.add(elementArray[i]);
        }
        return textList;
    }
}