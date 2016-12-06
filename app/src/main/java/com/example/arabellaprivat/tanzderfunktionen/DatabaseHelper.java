package com.example.arabellaprivat.tanzderfunktionen;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
/**
 * Created by Vicky on 06.12.2016.
 */

/**Klasse DatabaseHelper
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = DatabaseHelper.class.getSimpleName();

    //Konstanten
    //Dateiname und Version
    private static final String DB_NAME = "matheapp.db";
    private static final int DB_VERSION = 1; //muss bei Änderung incrementiert werden
    //Tabellennamen
    public static final String DB_TABLE_1 = "Level";
    public static final String DB_TABLE_2 = "Nutzer";
    public static final String DB_TABLE_3 = "Endbewertung";

    //Tabellenspalten_LEVEL
    public static final String LEVEL_LEVEL = "level_level";
    public static final String FUNKTION = "funktion";
    public static final String PARAMETER_1 = "a";
    public static final String PARAMETER_2 = "b";
    public static final String PARAMETER_3 = "c";
    public static final String TIPP = "tipp";
    public static final String BEWERTUNG = "bewertung";

    //Tabellenspalten_NUTZER
    public static final String NAME_NUTZER = "name_nutzer";
    public static final String LEVEL_NUTZER = "level_nutzer";
    public static final String PUNKTE = "punkte";

    //Tabellenspalten_ENDBEWERTUNG
    public static final String PUNKTE_GESAMT = "punkte_gesamt";
    public static final String AUSGABE_TEXT = "text";

    //String der die Tabelle 1: LEVEL erstellt
    public static final String SQL_CREATEDB_1 =
            "CREATE TABLE " + DB_TABLE_1 +
                    " (" + LEVEL_LEVEL + " INTEGER PRIMARY KEY, " +
                    FUNKTION + " TEXT NOT NULL, " +
                    PARAMETER_1 + " REAL, " +
                    PARAMETER_2 + " REAL, " +
                    PARAMETER_3 + " REAL, " +
                    TIPP + " TEXT, " +
                    BEWERTUNG + " INTEGER);";

    //String der die Tabelle 2: NUTZER erstellt
    public static final String SQL_CREATEDB_2 = "CREATE TABLE " + DB_TABLE_2 +" ("+
            NAME_NUTZER + " TEXT PRIMARY KEY, "+
            LEVEL_NUTZER + " REFERENCES Level(level_level), "+
            PUNKTE + " REFERENCES Endbewertung(punkte_gesamt));";

    //String der die Tabelle 3: ENDBEWERTUNG erstellt
    public static final String SQL_CREATEDB_3 = "CREATE TABLE " +DB_TABLE_3+" ("+
            PUNKTE_GESAMT + " INTEGER PRIMARY KEY, "+
            AUSGABE_TEXT + " TEXT);";

    //Werte in die Tabelle schreiben
    //TABELLE 1
    public static final String SQL_INSERTDB1_1 =
            "INSERT INTO Level"+
                    "(level_level,funktion,a,b,c,tipp, bewertung) " +
                    "VALUES " +
                    "(1, '0.5x+2', 0.5, 2, NULL, 'Achte auf die Steigung und den y-Achsenabschnitt', 1);";
    public static final String SQL_INSERTDB1_2 =
            "INSERT INTO Level"+
                    "(level_level,funktion,a,b,c,tipp, bewertung) " +
                    "VALUES " +
                    "(2, '0.25x^2+1x-4', 0.25, 1, 4, 'Denke an die Stauchung und den y-Achsenabschnitt', 1);";
    public static final String SQL_INSERTDB1_3 =
            "INSERT INTO Level"+
                    "(level_level,funktion,a,b,c,tipp, bewertung) " +
                    "VALUES " +
                    "(3, '0.5x^2/x-2', 0.5, 2, NULL, 'Achte auf den Definitionsbereich', 1);";
    public static final String SQL_INSERTDB1_4 =
            "INSERT INTO Level"+
                    "(level_level,funktion,a,b,c,tipp, bewertung) " +
                    "VALUES " +
                    "(4, '3cos(x+1)', 3, 1, 1, 'Achte auf die Steigung, die Verschiebung und wende deine Kenntnisse über trigonometrische Funktionen an', 1);";
    public static final String SQL_INSERTDB1_5 =
            "INSERT INTO Level"+
                    "(level_level,funktion,a,b,c,tipp, bewertung) " +
                    "VALUES " +
                    "(5, 'ln(x-1)', 1, 1, NULL, 'Wo wird die x-Achse geschnitten ? Denke daran, dass die logarithmische Funktion die Umkehrfunktion von der e-Funktion ist', 1);";
    //TABELLE 2
    public static final String SQL_INSERTDB2_1 =
            "INSERT INTO Nutzer"+
                    "(name_nutzer,level_nutzer,punkte) " +
                    "VALUES " +
                    "('User_1', 1, 1);";
    //TABELLE 3
    public static final String SQL_INSERTDB3_1 =
            "INSERT INTO Endbewertung"+
                    "(punkte_gesamt,text) " +
                    "VALUES " +
                    "(1,'AMATEUR! DU MUSST NOCH ÜBEN');";

    public static final String SQL_INSERTDB3_2 =
            "INSERT INTO Endbewertung"+
                    "(punkte_gesamt,text) " +
                    "VALUES " +
                    "(2,'DU BRAUCHST NOCH ÜBUNG');";

    public static final String SQL_INSERTDB3_3 =
            "INSERT INTO Endbewertung"+
                    "(punkte_gesamt,text) " +
                    "VALUES " +
                    "(3,'FORTGESCHRITTEN! BLEIB DRAN, DANN WIRD ES NÄCHSTES MAL NOCH BESSER');";

    public static final String SQL_INSERTDB3_4 =
            "INSERT INTO Endbewertung"+
                    "(punkte_gesamt,text) " +
                    "VALUES " +
                    "(4,'TOLL! FAST ALLES RICHTIG. BLEIB DABEI!');";

    public static final String SQL_INSERTDB3_5 =
            "INSERT INTO Endbewertung"+
                    "(punkte_gesamt,text) " +
                    "VALUES " +
                    "(5,'DU BIST DER MATHE-KING! SUPER GEMACHT');";



    //Constructor
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.d(LOG_TAG, "DatabaseHelper hat die Datenbank: " + getDatabaseName() + " erzeugt.");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATEDB_1 + " angelegt.");
            //Tabelle in DB erstellen
            db.execSQL(SQL_CREATEDB_1);
            Log.d(LOG_TAG, "Die Daten werden mit SQL-Befehl: " + SQL_INSERTDB1_1 + " in die Tabelle eingefuegt.");
            //Daten einfügen
            db.execSQL(SQL_INSERTDB1_1);
            Log.d(LOG_TAG, "Die Daten werden mit SQL-Befehl: " + SQL_INSERTDB1_2 + " in die Tabelle eingefuegt.");
            db.execSQL(SQL_INSERTDB1_2);
            Log.d(LOG_TAG, "Die Daten werden mit SQL-Befehl: " + SQL_INSERTDB1_3 + " in die Tabelle eingefuegt.");
            db.execSQL(SQL_INSERTDB1_3);
            Log.d(LOG_TAG, "Die Daten werden mit SQL-Befehl: " + SQL_INSERTDB1_4 + " in die Tabelle eingefuegt.");
            db.execSQL(SQL_INSERTDB1_4);
            Log.d(LOG_TAG, "Die Daten werden mit SQL-Befehl: " + SQL_INSERTDB1_5 + " in die Tabelle eingefuegt.");
            db.execSQL(SQL_INSERTDB1_5);
            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATEDB_3 + " angelegt.");
            //Tabelle in DB erstellen
            db.execSQL(SQL_CREATEDB_3);
            Log.d(LOG_TAG, "Die Daten werden mit SQL-Befehl: " + SQL_INSERTDB3_1 + " in die Tabelle eingefuegt.");
            //Daten einfügen
            db.execSQL(SQL_INSERTDB3_1);
            Log.d(LOG_TAG, "Die Daten werden mit SQL-Befehl: " + SQL_INSERTDB3_2 + " in die Tabelle eingefuegt.");
            db.execSQL(SQL_INSERTDB3_2);
            Log.d(LOG_TAG, "Die Daten werden mit SQL-Befehl: " + SQL_INSERTDB3_3 + " in die Tabelle eingefuegt.");
            db.execSQL(SQL_INSERTDB3_3);
            Log.d(LOG_TAG, "Die Daten werden mit SQL-Befehl: " + SQL_INSERTDB3_4 + " in die Tabelle eingefuegt.");
            db.execSQL(SQL_INSERTDB3_4);
            Log.d(LOG_TAG, "Die Daten werden mit SQL-Befehl: " + SQL_INSERTDB3_5 + " in die Tabelle eingefuegt.");
            db.execSQL(SQL_INSERTDB3_5);
            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATEDB_2 + " angelegt.");
            //Tabelle in DB erstellen
            db.execSQL(SQL_CREATEDB_2);
            Log.d(LOG_TAG, "Die Daten werden mit SQL-Befehl: " + SQL_INSERTDB2_1 + " in die Tabelle eingefuegt.");
            //Daten einfügen
            db.execSQL(SQL_INSERTDB2_1);
        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "Fehler beim Anlegen der Tabellen: " + ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_1);
        db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_2);
        db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_3);
        onCreate(db);

    }
}
