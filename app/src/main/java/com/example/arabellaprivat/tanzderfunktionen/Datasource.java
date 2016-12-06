package com.example.arabellaprivat.tanzderfunktionen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Vicky on 06.12.2016.
 */


/**Klasse Datasource
 *->Datenquelle
 *hält die Verbindung zur Datenbank aufrecht
 *fragt Referenz zu dem Datenbankobjekt an
 *startet Erstellungsprozess der Tabelle
 */
public class Datasource {

    private static final String LOG_TAG = Datasource.class.getSimpleName();

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    /*private String[] spalten_1 = {
            DatabaseHelper.LEVEL_LEVEL,
            DatabaseHelper.FUNKTION,
            DatabaseHelper.PARAMETER_1,
            DatabaseHelper.PARAMETER_2,
            DatabaseHelper.PARAMETER_3,
            DatabaseHelper.TIPP,
            DatabaseHelper.BEWERTUNG
    };
    private String[] spalten_2 = {
            DatabaseHelper.NAME_NUTZER,
            DatabaseHelper.LEVEL_NUTZER,
            DatabaseHelper.PUNKTE,
    };
    private String[] spalten_3 = {
            DatabaseHelper.PUNKTE_GESAMT,
            DatabaseHelper.AUSGABE_TEXT,
    };*/

    public Datasource(Context context) {
        Log.d(LOG_TAG, "Unsere DataSource erzeugt jetzt den DatabaseHelper.");
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        Log.d(LOG_TAG, "Eine Referenz auf die Datenbank wird jetzt angefragt.");
        //Verbindung zur DB herstellen
        database = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, "Datenbank-Referenz erhalten. Pfad zur Datenbank: " + database.getPath());
    }

    public void close() {
        //Verbindung zur DB schließen
        dbHelper.close();
        Log.d(LOG_TAG, "Datenbank mit Hilfe des DbHelpers geschlossen.");
    }
}